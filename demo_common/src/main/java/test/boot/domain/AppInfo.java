package test.boot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Table(name = "test_app_info")
@Data
public class AppInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	/** 主键id,自增 */
	@Null(message = "id不可传")
	private Integer id;
	/** 服务调用方id */
	@Column(name = "app_id")
	private Long appId;
	/** 服务调用方私钥 */
	@Column(name = "app_secret")
	private String appSecret;
	/** 服务调用方名称 */
	@Column(name = "name")
	private String name;
	/** 服务调用方状态 */
	@Column(name = "statue")
	/** -1-不可用,0-待审核,1-可用 */
	private Integer statue;
	/** 渠道来源 0-外部,1-内部 */
	private Integer channel;

	/** 待审核 */
	public static final Integer APPLY = 0;
	/** 可用 */
	public static final Integer USABLE = 1;
	/** 不可用 */
	public static final Integer DISABLE = -1;
	/** 内部渠道 */
	public static final Integer INNERCHANNEL = 1;
	/** 外部渠道 */
	public static final Integer OUTERCHANNEL = 0;

}