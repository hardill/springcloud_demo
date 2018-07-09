package test.boot.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token返回参数类
 * 
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken {
	/** 状态码200 */
	public static final int RESPCODE_SUCCESS = 0;
	/** 状态码500 */
	public static final int RESPCODE_FAILURE = 1;
	/** 成功信息SUCCESS */
	public static final String MSG_SUCCESS = "OK";
	/** 失败信息FAIL */
	public static final String MSG_FAIL = "ERROR";

	/** 返回状态码 */
	private int respCode;
	/** 返回信息 */
	private String respMsg;
	/** 调用方appId */
	private long appId;
	/** 返回token */
	private String accessToken;
	/** token过期时间 */
	private Date expiresIn;
}
