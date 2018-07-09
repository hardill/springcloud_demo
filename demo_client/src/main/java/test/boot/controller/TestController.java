package test.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import test.boot.SpecificConfig;
import test.boot.common.CommonResponse;
import test.boot.common.Result;
import test.boot.domain.Member;
import test.boot.feign.service.FeignTest2Service;
import test.boot.mq.HelloSender;
import test.boot.service.MemberService;

@RestController
@ConfigurationProperties
@Api("TEST测试")
@Slf4j
// @RequestMapping("test1")
public class TestController extends CommonResponse {

	@Value("${server.port}")
	private Integer port;
	@Value("${hello}")
	private String hello;

	@Autowired
	private MemberService memberService;
	@Autowired
	private FeignTest2Service feignTest2Service;
	@Autowired
	private SpecificConfig specificConfig;
	@Autowired
	private HelloSender helloSender;

	@GetMapping(value = "/hi")
	@ApiOperation("hi")
	public Result hi() {
		helloSender.sendExchange2();
		String msg = "from: " + port + ";msg: " + hello;
		return successResult("SUCCESS", msg);

	}

	@GetMapping(value = "/hi2")
	@ApiOperation("hi2")
	public Result hi2() {
		helloSender.sendExchange();
		String msg = "from: " + port + ";msg: " + hello;
		return successResult("SUCCESS", msg);

	}

	@GetMapping(value = "/getConfig")
	@ApiOperation("自定义配置信息")
	public Result getConfig() {
		log.info("num默认值为: {}", specificConfig.getNum());
		return successResult("SUCCESS", specificConfig);

	}

	@GetMapping(value = "/hello")
	@ApiOperation("调用test2服务,msg为hi抛异常")
	public Result hello(@RequestParam String msg) {
		if ("hi".equals(msg)) {
			int i = 1 / 0;
		}
		return feignTest2Service.hi(msg);
	}

	@PostMapping(value = "/save")
	@ApiOperation("新增")
	public Result save(@ApiParam("成员信息,id不传") @RequestBody @Validated Member member, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			// 参数校验未通过
			log.error("参数有误,{}", bindingResult.getFieldError().getDefaultMessage());
			return failureResult("参数有误");
		}

		return successResult("SUCCESS", "添加信息记录条数:" + memberService.save(member));

	}

	@PostMapping(value = "/delete")
	@ApiOperation("删除")
	public Result delete(@ApiParam("被删除id") @RequestParam Integer id) {
		return successResult("SUCCESS", "删除信息记录条数:" + memberService.deleteById(id));

	}

	@PostMapping(value = "/update")
	@ApiOperation("修改")
	public Result update(@ApiParam("成员信息") @RequestBody Member member) {
		return successResult("SUCCESS", "修改信息记录条数:" + memberService.update(member));

	}

	@PostMapping(value = "/memberList")
	@ApiOperation("查询所有成员信息")
	public Result memberList(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") int pageNum,
			@ApiParam(value = "每页个数") @RequestParam(defaultValue = "10") int pageSize) {

		pageNum = pageNum < 1 ? 1 : pageNum;
		pageSize = pageSize < 1 ? 10 : pageSize;

		return successResult("SUCCESS", memberService.selectByPage(pageNum, pageSize));
	}
}
