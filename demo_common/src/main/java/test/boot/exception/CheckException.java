package test.boot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CheckException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/** 异常原因 */
	private String reason;

}
