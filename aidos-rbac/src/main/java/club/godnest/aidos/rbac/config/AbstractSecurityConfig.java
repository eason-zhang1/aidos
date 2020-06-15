package club.godnest.aidos.rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import club.godnest.aidos.rbac.interceptor.XAuthTokenWebInterceptor;

import static java.util.Arrays.*;

/**
 * @author H.J.Zhang
 * @see DefaultSecurityConfig
 * @since 2020-04-28
 */
public abstract class AbstractSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Lazy
  @Autowired
  private SpringSessionBackedSessionRegistry sessionRegistry;

  public static final List<String> defaultUrlIgnored;

  static {
	defaultUrlIgnored = new ArrayList<>(
		asList("/api/pub/**", "/api/user/**")
	);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
	registry.and().csrf().disable().headers().frameOptions().disable();
	registry.antMatchers(defaultUrlIgnored.toArray(new String[0])).permitAll();
	registry.antMatchers(HttpMethod.OPTIONS).permitAll()
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			// maximumSessions 根据SpringSessionBackedSessionRegistry`自定义实现
			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			// todo 无效设置, 如需并发控制请自己实现
			// 参考: org.springframework.security.core.session.SessionRegistryImpl
			.maximumSessions(1).sessionRegistry(sessionRegistry)
			.and()
			.and()
			// 异常处理
			.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
	web.ignoring()
	   //.antMatchers(HttpMethod.OPTIONS, "/**")
	   .antMatchers("/**/*.html")
	   .antMatchers("/i18n/**", "/css/**", "/js/**", "/libs/**")
	   .antMatchers("/img/**", "/**/*.ico")
	   .antMatchers("/test/**");
  }

  /**
   * 跨域设置
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
	// TODO 通过配置文件配置如下常量
	CorsConfiguration configuration = new CorsConfiguration();
	if (CollectionUtils.isEmpty(configuration.getAllowedMethods())) {
	  configuration.setAllowedMethods(asList("GET", "POST", "DELETE", "PUT"));
	}
	if (CollectionUtils.isEmpty(configuration.getAllowedHeaders())) {
	  configuration.setAllowedHeaders(asList("Content-Type", "x-auth-token", "x-xsrf-token", "x-requested-with"));
	}
	if (CollectionUtils.isEmpty(configuration.getAllowedOrigins())) {
	  configuration.setAllowedOrigins(Collections.singletonList("*"));
	}
	if (configuration.getAllowCredentials() == null) {
	  configuration.setAllowCredentials(true);
	}

	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/api/**", configuration);
	return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
  }

  @Bean
  @Resource(type = RedisIndexedSessionRepository.class)
  public SpringSessionBackedSessionRegistry sessionRegistry(RedisIndexedSessionRepository redisIndexedSessionRepository) {
	return new SpringSessionBackedSessionRegistry(redisIndexedSessionRepository);
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~MVC Configurer~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new XAuthTokenWebInterceptor()).addPathPatterns("/**");
  }
}
