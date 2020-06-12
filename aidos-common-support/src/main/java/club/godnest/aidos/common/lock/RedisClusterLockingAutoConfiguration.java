package club.godnest.aidos.common.lock;

import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import club.godnest.aidos.common.lock.aop.RedisClusterLockableAspect;

/**
 * @author H.J.Zhang
 * @since 2020-04-27
 */
@Configuration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@ConditionalOnWebApplication
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnMissingBean(RedisClusterLockingAutoConfiguration.class)
public class RedisClusterLockingAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(RedisClusterLockableAspect.class)
  public RedisClusterLockableAspect redisClusterLockableAspect(RedissonClient redissonClient) {
	return new RedisClusterLockableAspect(redissonClient);
  }
}



