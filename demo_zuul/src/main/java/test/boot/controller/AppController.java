package test.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.mrdear.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import test.boot.common.CommonResponse;
import test.boot.common.Result;
import test.boot.domain.ResponseToken;
import test.boot.domain.SignParam;
import test.boot.redis.RedisService;
import test.boot.service.AppService;

@RestController
@ConfigurationProperties
@Api("app控制层")
@Slf4j
public class AppController extends CommonResponse {
	@Autowired
	private RedisService redisService;

	@Autowired
	private AppService appService;
	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;
	// private RedisTemplate<String, String> redisTemplate;

	@PostMapping(value = "/redis")
	@ApiOperation("redis测试")
	public Result testRedis() {

		User user = (User) redisService.get("com.neox");
		System.err.println(user);

		// redisService.set("hello", "hello3");
		String string = stringRedisTemplate.opsForValue().get("hello");
		System.err.println(string);
		String string2 = (String) redisService.get("hello");
		System.err.println(string2);

		return successResult("SUCCESS", null);

	}

	@PostMapping(value = "/createAppInfo")
	@ApiOperation("创建appInfo,后续添加权限")
	public Result createAppInfo(@ApiParam("app名称") @RequestParam String name) {
		return successResult("SUCCESS", "生产AppInfoId: " + appService.createAppInfo(name));
	}

	@PostMapping(value = "/hi")
	@ApiOperation("测试")
	public Result hi() {
		return successResult("SUCCESS", "hello zuul");
	}

	@PostMapping(value = "/getToken")
	@ApiOperation("获取token")
	public Result getToken(@RequestBody SignParam signParam) {
		ResponseToken token = appService.buildToken(signParam);
		return successResult("SUCCESS", token);

	}

}
