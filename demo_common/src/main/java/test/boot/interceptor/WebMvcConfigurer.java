package test.boot.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置项目拦截器
 * 
 * @author Administrator
 *
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myTokenInterceptor()).addPathPatterns("/feign/**"); // 拦截所有请求，通过判断是否有决定是否需要登录
		super.addInterceptors(registry);
	}

	@Bean
	public MyTokenInterceptor myTokenInterceptor() {
		return new MyTokenInterceptor();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

}
