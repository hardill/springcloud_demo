package test.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@ConfigurationProperties(prefix = "specific")
@Data
public class SpecificConfig {

	@Value("${flag:true}")
	private boolean flag; // 忽略时间
	@Value("${sleepTime:0}")
	private long sleepTime; // 睡眠时间
	@Value("${num:100}")
	private int num; // 测试默认值,配置中无num
	@Value("${msg:123456}")
	private String msg; // 加密盐值

	/** map */
	private Map<String, String> testMap = new HashMap<>();
	/** list */
	private List<String> testList = new ArrayList<String>();

}
