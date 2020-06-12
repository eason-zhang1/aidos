package club.godnest.aidos.rbac.interceptor;

import org.springframework.util.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author H.J.Zhang
 * @since 2020-05-29
 */
@Slf4j
public class XAuthTokenRequestInterceptor implements RequestInterceptor {

  private final static String X_AUTH_TOKEN = "x-auth-token";

  @Override
  public void apply(RequestTemplate template) {
	String xAuthToken = System.getProperties().getProperty(X_AUTH_TOKEN);
	log.info("{} request interceptor==========> {}", X_AUTH_TOKEN, xAuthToken);
	if (StringUtils.hasText(xAuthToken)) {
	  template.header(X_AUTH_TOKEN, xAuthToken);
	}
  }
}
