package test.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 全局自定义配置类
 * 
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties
@Data
public class AppConfig {

	@Value("${appId:100}")
	private long appId; // 服务appid
	@Value("${appSecret:123456}")
	private String appSecret; // 服务秘钥

}
