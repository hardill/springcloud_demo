package test.boot.feign.service.hystrix;

import org.springframework.stereotype.Component;

import test.boot.common.Result;
import test.boot.domain.Member;
import test.boot.feign.service.FeignService;

/**
 * 熔断时接口返回信息
 * 
 * @author Administrator
 *
 */
@Component
public class HystrixClientFallback implements FeignService {

	@Override
	public Result delete(Integer id) {
		return buildErrorResult();
	}

	@Override
	public Result memberList(int pageNum, int pageSize) {
		return buildErrorResult();
	}

	@Override
	public Result save(Member member) {
		return buildErrorResult();
	}

	@Override
	public Result update(Member member) {
		return buildErrorResult();
	}

	private Result buildErrorResult() {
		return new Result(Result.RESPCODE_FAILURE, Result.MSG_FAIL, null);
	}

}
