package test.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@MapperScan("test.boot.dao") // dao扫描配置
@EnableEurekaClient // 开启注册
@EnableZuulProxy // 开启 路由
public class TestZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestZuulApplication.class, args);

	}

}