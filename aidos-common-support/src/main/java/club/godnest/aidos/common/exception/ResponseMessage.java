package club.godnest.aidos.common.exception;

import lombok.Data;

/**
 * @author H.J.Zhang
 */
@Data
public class ResponseMessage {

  private String message;

  private String code;

  public ResponseMessage(String message, String code) {
	this.message = message;
	this.code = code;
  }

  public static ResponseMessage danger(String message) {

	return new ResponseMessage(message, "danger");
  }


}
