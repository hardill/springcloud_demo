package test.boot.feign.service.hystrix;

import org.springframework.stereotype.Component;

import test.boot.common.Result;
import test.boot.domain.SignParam;
import test.boot.feign.service.FeignZuulService;

/**
 * 熔断时接口返回信息
 * 
 * @author Administrator
 *
 */
@Component
public class ZuulHystrixFallback implements FeignZuulService {

	private Result buildErrorResult() {
		return new Result(Result.RESPCODE_FAILURE, Result.MSG_FAIL, null);
	}

	@Override
	public Result getToken(SignParam signParam) {
		return buildErrorResult();
	}

	@Override
	public Result hi() {
		// TODO Auto-generated method stub
		return null;
	}

}
