package test.boot.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.boot.common.Result;
import test.boot.feign.service.hystrix.HystrixClientFallback2;

/**
 * TEST2服务feign服务端
 * 
 * @author Administrator
 *
 */
@FeignClient(value = "TEST2", fallback = HystrixClientFallback2.class)
public interface FeignTest2Service {

	@GetMapping("/feign/hi")
	Result hi(@RequestParam("msg") String msg);

}
