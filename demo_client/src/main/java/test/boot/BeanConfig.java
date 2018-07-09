package test.boot;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.pagehelper.PageHelper;

/**
 * bean配置
 * 
 * @author Administrator
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		return pageHelper;
	}

	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(60);
		taskExecutor.setMaxPoolSize(200);
		taskExecutor.setQueueCapacity(100);
		taskExecutor.initialize();
		return taskExecutor;
	}

}
