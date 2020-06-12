package club.godnest.aidos.rbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import club.godnest.aidos.rbac.interceptor.XAuthTokenRequestInterceptor;

/**
 * @author H.J.Zhang
 * @since 2020-05-29
 */
@Configuration
public class FeignConfig {

  @Bean
  public XAuthTokenRequestInterceptor xAuthTokenRequestInterceptor() {
	return new XAuthTokenRequestInterceptor();
  }
}
