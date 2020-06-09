package club.godnest.aidos.rbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * @author H.J.Zhang
 * @since 2020-04-30
 */
@EnableRedisHttpSession
public class RedisHttpSessionConfig {

  @Bean
  public HttpSessionIdResolver httpSessionIdResolver() {
	return HeaderHttpSessionIdResolver.xAuthToken();
  }

}