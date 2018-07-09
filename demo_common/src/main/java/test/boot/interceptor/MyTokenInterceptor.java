package test.boot.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import test.boot.common.JsonUtils;
import test.boot.common.Result;
import test.boot.common.constants.Constants;
import test.boot.domain.AppInfo;
import test.boot.redis.RedisService;

@Slf4j
public class MyTokenInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisService redisService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		RequiredToken methodAnnotation = method.getAnnotation(RequiredToken.class);
		// 有 @RequiredToken 注解，需要携带token
		if (methodAnnotation != null) {
			String token = request.getHeader("token");
			if (token != null) {
				try {
					if (!redisService.existKey(token)) {
						outWrite(response, 403, Constants.REQUIRE_AUTH);
						return false;
					}
					// TODO 可改为查询数据库
					AppInfo appInfo = (AppInfo) redisService.get(token);
					log.info("收到一条请求,来自:{}", appInfo);
					return true;
				} catch (Exception e) {
					outWrite(response, 401, Constants.REQUIRE_AUTH);
					return false;
				}
			}
			outWrite(response, 401, Constants.REQUIRE_AUTH);
			return false;
		}
		return true;
	}

	public void outWrite(HttpServletResponse response, int code, String msg) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		Result result = new Result(code, msg, "");
		response.getWriter().write(JsonUtils.encode(result));
	}

	private boolean isExist(int[] arr, int ele) {
		for (int i = 0, len = arr.length; i < len; i++) {
			if (arr[i] == ele) {
				return true;
			}
		}
		return false;
	}

}
