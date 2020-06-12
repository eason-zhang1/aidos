package club.godnest.aidos.common.lock.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author H.J.Zhang
 * @see RedisClusterLockableAspect
 * @since 2020-04-26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisClusterLockable {

  /**
   * Name for prefix key
   */
  String name() default "";

  /**
   * Key for redis key
   */
  String key() default "";

  /**
   * 重试次数
   */
  int retry() default 2;

  /**
   * 超时时间(毫秒), 超时时取消线程
   */
  int timeout() default 3_000;
}
