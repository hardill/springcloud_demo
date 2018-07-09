package test.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("test.boot.dao") // dao扫描配置
// @EnableEurekaClient // 开启注册
// @EnableFeignClients // 开启 feign
// @EnableScheduling
@EnableAsync // 开启异步方法调用
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

	}

}