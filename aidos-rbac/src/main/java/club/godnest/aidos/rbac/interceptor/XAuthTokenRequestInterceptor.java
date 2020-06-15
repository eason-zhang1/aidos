package club.godnest.aidos.rbac.interceptor;

import org.springframework.util.StringUtils;

import club.godnest.aidos.rbac.util.XAuthTokenUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import static club.godnest.aidos.rbac.config.Constants.*;

/**
 * @author H.J.Zhang
 * @since 2020-05-29
 */
@Slf4j
public class XAuthTokenRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
	String xAuthToken = XAuthTokenUtils.getToken();
	log.warn("Fegin 请求的拦截器: {} ——> {}", X_AUTH_TOKEN, xAuthToken);
	if (StringUtils.hasText(xAuthToken)) {
	  template.header(X_AUTH_TOKEN, xAuthToken);
	}
  }
}
