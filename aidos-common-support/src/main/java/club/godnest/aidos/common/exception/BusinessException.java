package club.godnest.aidos.common.exception;

import lombok.Data;

/**
 * 业务异常
 * <p>
 * Created by CJW on 2017/12/10.
 */
@Data
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 6185719182003381394L;
  private ResponseMessage responseMessage;

  /**
   * except Spring HttpStatus name
   */
  private String httpStatus;

  public BusinessException(String message) {
	super(message);
	this.responseMessage = ResponseMessage.danger(message);
  }

  public BusinessException(String code, String message) {
	super(message);
	this.responseMessage = new ResponseMessage(message, code);
  }
}
