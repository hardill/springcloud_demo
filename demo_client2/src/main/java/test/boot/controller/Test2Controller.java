package test.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import test.boot.common.CommonResponse;
import test.boot.common.Result;
import test.boot.domain.Member;
import test.boot.feign.service.FeignService;
import test.boot.interceptor.RequiredToken;

@RestController
@ConfigurationProperties
@Api("TEST2测试")
@Slf4j
// @RequestMapping("test2")
public class Test2Controller extends CommonResponse {

	@Value("${server.port}")
	private Integer port;
	@Value("${hello}")
	private String hello;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FeignService feignService;

	@GetMapping(value = "/feign/hi")
	@ApiOperation("hi")
	@RequiredToken
	public Result hi(@RequestParam String msg) {
		return successResult("SUCCESS", "from: " + port + ";msg: " + msg);

	}

	@GetMapping(value = "/memberList")
	@ApiOperation("restTemplate跨服务获取信息")
	public Result getMembers() {
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>(2);
		param.add("pageNum", "1");
		param.add("pageSize", "2");
		ResponseEntity<Result> entity = restTemplate.postForEntity("http://TEST/memberList", param, Result.class);
		return successResult("SUCCESS", entity.getBody().getData());

	}

	@GetMapping(value = "/delete")
	@ApiOperation("跨服务获取信息")
	public Result delete(@RequestParam Integer id) {
		return feignService.delete(id);

	}

	@GetMapping(value = "/getMembers")
	@ApiOperation("跨服务获取成员信息")
	public Result getMembers2(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") int pageNum,
			@ApiParam(value = "每页个数") @RequestParam(defaultValue = "10") int pageSize) {
		return feignService.memberList(pageNum, pageSize);

	}

	@PostMapping(value = "/save")
	@ApiOperation("跨服务新增")
	public Result save(@ApiParam("成员信息,id不传") @RequestBody @Validated Member member, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			// 参数校验未通过
			log.error("参数有误,{}", bindingResult.getFieldError().getDefaultMessage());
			return failureResult("参数有误");
		}

		return successResult("SUCCESS", "添加信息记录条数:" + feignService.save(member));

	}

	@PostMapping(value = "/update")
	@ApiOperation("跨服务修改")
	public Result update(@ApiParam("成员信息") @RequestBody Member member) {
		return successResult("SUCCESS", "修改信息记录条数:" + feignService.update(member));

	}

}
