package test.boot.feign.service.hystrix;

import org.springframework.stereotype.Component;

import test.boot.common.Result;
import test.boot.feign.service.FeignTest2Service;

/**
 * 熔断时接口返回信息
 * 
 * @author Administrator
 *
 */
@Component
public class HystrixClientFallback2 implements FeignTest2Service {

	private Result buildErrorResult() {
		return new Result(Result.RESPCODE_FAILURE, Result.MSG_FAIL, null);
	}

	@Override
	public Result hi(String msg) {
		return buildErrorResult();
	}

}
