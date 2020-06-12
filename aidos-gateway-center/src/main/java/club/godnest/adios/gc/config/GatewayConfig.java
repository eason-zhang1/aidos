package club.godnest.adios.gc.config;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * @author H.J.Zhang
 */
@Configuration
public class GatewayConfig {

  @Bean
  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
	return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
		.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()) // 熔断配置
		.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(1000)).build()) // 超时配置
		.build());
  }
}
