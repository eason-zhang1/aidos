package club.godnest.aidos.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import club.godnest.aidos.common.web.RestExceptionHandler;

/**
 * @author H.J.Zhang
 */
@Configuration
public class CommonAutoConfiguration {

  @Bean
  public RestExceptionHandler restExceptionHandler() {
	return new RestExceptionHandler();
  }
}
