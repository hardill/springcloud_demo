package test.boot.zuul.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;
import test.boot.common.JsonUtils;
import test.boot.common.Result;
import test.boot.common.constants.Constants;
import test.boot.domain.AppInfo;
import test.boot.redis.RedisService;

/**
 * zuul路由过滤器
 * 
 * @author Administrator
 *
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {

	// private static Logger log = LoggerFactory.getLogger(MyFilter.class);
	@Autowired
	private RedisService redisService;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("请求信息: {} >>> {}", request.getMethod(), request.getRequestURL().toString());
		String accessToken = request.getHeader("token");
		if (accessToken == null || !redisService.existKey(accessToken)) {
			log.warn("token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				outWrite(ctx.getResponse(), 401, Constants.REQUIRE_AUTH);
			} catch (Exception e) {
			}

			return null;
		}
		//
		AppInfo appInfo = (AppInfo) redisService.get(accessToken);

		log.info("ok");
		return null;
	}

	public void outWrite(HttpServletResponse response, int code, String msg) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		Result result = new Result(code, msg, "");
		response.getWriter().write(JsonUtils.encode(result));
	}
}