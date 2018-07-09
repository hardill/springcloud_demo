package test.boot.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import test.boot.common.Result;
import test.boot.domain.Member;
import test.boot.feign.service.hystrix.HystrixClientFallback;

/**
 * TEST服务feign服务端
 * 
 * @author Administrator
 *
 */
@FeignClient(value = "TEST", fallback = HystrixClientFallback.class)
public interface FeignService {
	/** 跨服务删除 */
	@PostMapping("/delete")
	Result delete(@RequestParam("id") Integer id);

	/** 跨服务获取成员信息 */
	@PostMapping(value = "/memberList")
	public Result memberList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

	/** 跨服务保存成员信息 */
	@PostMapping(value = "/save")
	public Result save(@RequestBody Member member);

	/** 跨服务修改 */
	@PostMapping(value = "/update")
	public Result update(@RequestBody Member member);

}
