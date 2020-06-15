package club.godnest.aidos.rbac.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.godnest.aidos.rbac.config.Constants;
import club.godnest.aidos.rbac.util.XAuthTokenUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author H.J.Zhang
 * @since 2020-06-15
 */
@Slf4j
public class XAuthTokenWebInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	String xAuthToken;
	if (StringUtils.hasText(xAuthToken = request.getHeader(Constants.X_AUTH_TOKEN))) {
	  XAuthTokenUtils.setToken(xAuthToken);
	}
	log.warn("当前请求的X-AUTH-TOKEN: {}", xAuthToken);

	return true;
  }
}
