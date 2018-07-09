package test.boot.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import test.boot.common.Result;
import test.boot.domain.SignParam;
import test.boot.feign.service.hystrix.ZuulHystrixFallback;

/**
 * ZUUL服务feign服务端
 * 
 * @author Administrator
 *
 */
@FeignClient(value = "ZUUL", fallback = ZuulHystrixFallback.class)
public interface FeignZuulService {
	/** 获取token */
	@PostMapping("/getToken")
	Result getToken(@RequestBody SignParam signParam);

	@PostMapping(value = "/hi")
	Result hi();

}
