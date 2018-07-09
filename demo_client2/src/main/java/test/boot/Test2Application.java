package test.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("test.boot.dao") // dao扫描配置
@EnableEurekaClient // 开启注册
@EnableFeignClients // 开启 feign
@EnableScheduling
public class Test2Application {

	public static void main(String[] args) {
		SpringApplication.run(Test2Application.class, args);

	}

}