package test.boot.feign.config;

import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import test.boot.common.constants.Constants;

/**
 * 为feign请求头添加token
 * 
 * @author Administrator
 *
 */
@Component
@Slf4j
public class FeignInterceptor implements RequestInterceptor {

	public void apply(RequestTemplate template) {
		String token = System.getProperty(Constants.FEIGN_SYSTEM_KEY);
		log.info("内存中token: {}", token);
		template.header("token", token);
	}
}