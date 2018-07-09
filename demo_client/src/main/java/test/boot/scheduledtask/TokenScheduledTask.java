package test.boot.scheduledtask;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import test.boot.AppConfig;
import test.boot.common.AppUtil;
import test.boot.common.Result;
import test.boot.common.constants.Constants;
import test.boot.domain.SignParam;
import test.boot.feign.service.FeignZuulService;

@Component
@Slf4j
public class TokenScheduledTask {
	/** 定时任务刷新时间 */
	public final static long ONE_Minute = 60 * 1000 * 60 * 20;

	@Autowired
	private FeignZuulService feignZuulService;
	@Autowired
	private AppConfig appConfig;

	// @Scheduled(cron = "*/10 * * * * *")
	public void test() {
		System.err.println("111111");
	}

	/**
	 * 刷新Token
	 */
	@Async
	@Scheduled(fixedDelay = ONE_Minute)
	public void reloadApiToken() {
		String token = this.getToken();
		while (StringUtils.isBlank(token)) {
			try {
				Thread.sleep(3000);
				token = this.getToken();
			} catch (InterruptedException e) {
				log.error("", e);
			}
		}

		log.info("获取的token: {}", token);
		System.setProperty(Constants.FEIGN_SYSTEM_KEY, token);
	}

	public String getToken() {
		SignParam signParam = new SignParam();
		signParam.setAppId(appConfig.getAppId());
		signParam.setNoncestr(AppUtil.createNnoncestr());
		signParam.setTimestamp(AppUtil.createTimestamp());
		String signStr = signParam.buildSortString() + "&secret=" + appConfig.getAppSecret();
		String sign = AppUtil.string2MD5(signStr);
		signParam.setSign(sign);
		Result result = feignZuulService.getToken(signParam);
		Map data = (Map) result.getData();
		if (Result.RESPCODE_SUCCESS != result.getRespCode()) {
			return null;
		}
		return (String) data.get("accessToken");
	}
}
