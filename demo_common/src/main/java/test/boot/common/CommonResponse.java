/**
 * 
 */
package test.boot.common;

/**
 * 统一返回方法
 * 
 * @author Administrator
 *
 */
public class CommonResponse {

	protected Result successResult(String msg, Object data) {
		Result result = new Result(Result.RESPCODE_SUCCESS, msg, data);
		return result;
	}

	protected Result successResult(Object data) {
		Result result = new Result(Result.RESPCODE_SUCCESS, "", data);
		return result;
	}

	protected Result successResult(String msg) {
		Result result = new Result(Result.RESPCODE_SUCCESS, msg, null);
		return result;
	}

	protected Result failureResult(String msg, Object data) {
		Result result = new Result(Result.RESPCODE_FAILURE, msg, data);
		return result;
	}

	protected Result failureResult(Object data) {
		Result result = new Result(Result.RESPCODE_FAILURE, "", data);
		return result;
	}

	protected Result failureResult(String msg) {
		return this.failureResult(msg, null);
	}
}
