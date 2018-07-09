package test.boot.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 获取token请求参数
 * 
 * @author Administrator
 *
 */
@Data
public class SignParam implements Serializable {
	/** 调用方appid */
	@NotNull(message = "appId不能为空")
	private Long appId;
	/** 随机字符串 */
	@NotEmpty(message = "noncestr不能为空")
	private String noncestr;
	/** 时间戳 */
	@NotEmpty(message = "timestamp不能为空")
	private String timestamp;
	/** 签名 */
	@NotEmpty
	@Length(max = 32, min = 32, message = "签名长度不为32")
	private String sign;

	/**
	 * 将参数按照ASCII编码排序拼接
	 * 
	 * @return
	 */
	public String buildSortString() {
		StringBuffer buf = new StringBuffer();
		buf.append("appId=").append(appId).append("&").append("noncestr=").append(noncestr).append("&")
				.append("timestamp=").append(timestamp);
		return buf.toString();
	}

}
