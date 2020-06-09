package club.godnest.aidos.common.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import club.godnest.aidos.common.exception.BusinessException;
import club.godnest.aidos.common.exception.ResponseMessage;

/**
 * Called when an exception occurs during request processing. Transforms the exception message into JSON format.
 */
@RestControllerAdvice(annotations = {RestController.class})
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
														   HttpHeaders headers, HttpStatus status, WebRequest request) {
	if (request instanceof ServletWebRequest) {
	  ServletWebRequest webRequest = (ServletWebRequest) request;
	  log.error("error request uri:{} queryString:{}", webRequest.getRequest().getRequestURI(),
				webRequest.getRequest().getQueryString());
	}
	log.error("error==>>:", ex);

	return new ResponseEntity<>(ResponseMessage.danger(ex.getMessage()),
								HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<ResponseMessage> handleSysIllegalArgumentException(IllegalArgumentException ex) {
	log.error("handling IllegalArgumentException...", ex);
	return new ResponseEntity<>(ResponseMessage.danger(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /************************************************************************************************************************************
   *                                                                                                                                  *
   *                                                     COMMON EXCEPTION                                                           *
   *                                                                                                                                  *
   ************************************************************************************************************************************/
  @ExceptionHandler(value = {BusinessException.class})
  public ResponseEntity<ResponseMessage> handleBusinessException(BusinessException ex) {
	log.error("response message:{}", ex.getResponseMessage().toString());

	HttpStatus status;
	if (StringUtils.isNotBlank(ex.getHttpStatus())) {
	  status = HttpStatus.valueOf(ex.getHttpStatus());
	} else {
	  status = HttpStatus.UNPROCESSABLE_ENTITY;
	}

	return new ResponseEntity<>(ex.getResponseMessage(), status);
  }

  /**
   * 用户异常
   */
  @ExceptionHandler(value = {BadCredentialsException.class,
							 AuthenticationException.class,
							 AccessDeniedException.class
  })
  public ResponseEntity<ResponseMessage> badCredentialsException(BadCredentialsException ex) {
	logger.warn("user exception: " + ex.getMessage());
	return new ResponseEntity<>(ResponseMessage.danger(ex.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  /**
   * 其他异常处理
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handlerOtherException(Exception ex, WebRequest request) {
	log.error("other exception: ", ex);
	return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
