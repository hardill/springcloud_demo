package test.boot.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.boot.domain.AppInfo;
import test.boot.service.AppService;

/**
 * 自定义注解
 * 
 * @author Administrator
 *
 */
@Component
public class AppIdValidator implements ConstraintValidator<AppidCheck, Long> {
	@Autowired
	private AppService appService;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		AppInfo appInfo = appService.selectByAppId(value);
		if (appInfo != null) {
			return true;
		}
		return false;
	}

	@Override
	public void initialize(AppidCheck constraintAnnotation) {

	}

}
