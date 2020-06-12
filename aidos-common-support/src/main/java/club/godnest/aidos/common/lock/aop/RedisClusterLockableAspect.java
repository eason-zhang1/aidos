package club.godnest.aidos.common.lock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author H.J.Zhang
 * @since 2020-04-26
 */
@Aspect
@Slf4j
public class RedisClusterLockableAspect implements Ordered {

  private final RedissonClient redissonClient;
  private final ExpressionParser parser;
  private final StandardEvaluationContext context;
  private final LocalVariableTableParameterNameDiscoverer vpDiscoverer;
  private final ExecutorService executorService;

  @Autowired
  public RedisClusterLockableAspect(RedissonClient redissonClient) {
	this.redissonClient = redissonClient;
	this.vpDiscoverer = new LocalVariableTableParameterNameDiscoverer();
	this.parser = new SpelExpressionParser();
	this.context = new StandardEvaluationContext();
	executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
  }

  @Around("@annotation(redisClusterLockable)")
  public Object lock(ProceedingJoinPoint pjp, RedisClusterLockable redisClusterLockable) throws Throwable {
	Method method = ((MethodSignature) pjp.getSignature()).getMethod();
	if (StringUtils.isEmpty(redisClusterLockable.name()) || StringUtils.isEmpty(redisClusterLockable.key())) {
	  throw new RuntimeException("锁的名称和key不能为空");
	}

	String key = String.format("%s::%s", redisClusterLockable.name(),
							   resolveKey(redisClusterLockable.key(), method, pjp.getArgs()));
	log.debug("锁 key===> {}", key);
	RLock rLock = redissonClient.getLock(key);
	// 重试
	int retry = Math.max(redisClusterLockable.retry(), 0);
	while (!rLock.tryLock()) {
	  if (retry-- <= 0) {
		throw new RuntimeException("尝试" + retry + "次后，获得锁失败");
	  }
	  TimeUnit.MILLISECONDS.sleep(5);
	}

	Future<?> future = executorService.submit(() -> {
	  try {
		return pjp.proceed();
	  } catch (Throwable throwable) {
		throw new RuntimeException(throwable.getMessage());
	  }
	});
	try {
	  return future.get(redisClusterLockable.timeout(), TimeUnit.MILLISECONDS);
	} catch (Exception ex) {
	  future.cancel(true);

	  log.error(ex.getMessage());
	  throw new RuntimeException("执行失败");
	} finally {
	  log.debug("解锁====> {}", key);
	  rLock.unlock();
	}
  }

  private String resolveKey(String key, Method method, Object[] args) {
	String[] paraNameArr = vpDiscoverer.getParameterNames(method);
	if (paraNameArr != null && paraNameArr.length > 0) {
	  for (int i = 0; i < paraNameArr.length; i++) {
		context.setVariable(paraNameArr[i], args[i]);
	  }
	}
	return parser.parseExpression(key).getValue(context, String.class);
  }

  @Override
  public int getOrder() {
	return Ordered.HIGHEST_PRECEDENCE + 10;
  }
}
