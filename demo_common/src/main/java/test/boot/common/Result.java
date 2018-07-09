/**
 * 
 */
package test.boot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 控制层统一返回数据格式
 * 
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
	/** 状态码200 */
	public static final int RESPCODE_SUCCESS = 200;
	/** 状态码500 */
	public static final int RESPCODE_FAILURE = 500;
	/** 成功信息SUCCESS */
	public static final String MSG_SUCCESS = "SUCCESS";
	/** 失败信息FAIL */
	public static final String MSG_FAIL = "FAIL";

	/** 返回码 */
	private int respCode;
	/** 返回信息 */
	private String respMsg;
	/** 返回数据 */
	private Object data;

}
