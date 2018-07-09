package test.boot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * appid校验注解
 * 
 * @author Administrator
 *
 */
@NotEmpty(message = "appId不能为空")
@Constraint(validatedBy = { AppIdValidator.class })
@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AppidCheck {
	String message() default "appId不存在";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
