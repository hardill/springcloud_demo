package test.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import test.boot.common.Result;
import test.boot.exception.CheckException;

/**
 * 自定义aop 捕捉所有controller异常
 * 
 * @author Administrator
 *
 */
@Component
@Aspect
@Slf4j
public class ControllerAOP {

	@Pointcut("execution(* test.boot.controller..*.*(..))")
	public void executeService() {

	}

	@Around(value = "execution(* test.boot.controller..*.*(..))")
	public Object doAroundReturningAdvice1(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();

		Result result;
		try {
			result = (Result) pjp.proceed();
			log.info("方法:{}, 运行时间为: {}ms", pjp.getSignature().getName(), (System.currentTimeMillis() - startTime));
		} catch (Throwable e) {
			result = builtErrResult(pjp, e);

		}

		return result;
	}

	/** 异常处理 */
	private Result builtErrResult(ProceedingJoinPoint pjp, Throwable e) {
		Result result = new Result();

		// 已知异常
		String reason = e.getMessage();
		if (e instanceof CheckException) {
			reason = ((CheckException) e).getReason();
			result = new Result(Result.RESPCODE_FAILURE, reason, "");
		} else {
			// TODO 未知的异常，应该格外注意，可以发送邮件通知等
			result = new Result(Result.RESPCODE_FAILURE, "FAIL", e.getMessage());
		}

		log.error("方法:{}报错,异常名:{},异常原因:{}", pjp.getSignature().getName(), e.getClass().getName(), reason);
		return result;
	}

}