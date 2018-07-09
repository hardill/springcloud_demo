package test.boot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "test_member")
@Data
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	/** 主键id,自增 */
	@Null(message = "id不可传")
	private Integer id;
	/** 姓名 */
	@Column(name = "name")
	@NotEmpty(message = "name不可为空")
	private String name;
	/** 手机 */
	@Column(name = "phone")
	@NotEmpty(message = "phone不可为空")
	@Length(min = 11, max = 11)
	private String phone;
	/** email */
	@Column(name = "email")
	@NotEmpty(message = "email不可为空")
	private String email;
	/** 年龄 */
	@Column(name = "age")
	@Min(value = 0L, message = "年龄需大于0")
	@Max(value = 150L, message = "年龄需小于150")
	private Integer age;
	/** 地址 */
	@Column(name = "address")
	@NotEmpty(message = "address不可为空")
	private String address;

}