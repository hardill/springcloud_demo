package test.boot.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import test.boot.common.AppUtil;
import test.boot.common.DateUtil;
import test.boot.dao.AppInfoDao;
import test.boot.domain.AppInfo;
import test.boot.domain.ResponseToken;
import test.boot.domain.SignParam;
import test.boot.exception.CheckException;
import test.boot.redis.RedisService;
import test.boot.service.AppService;

@Service
@Slf4j
public class AppServiceImpl implements AppService {
	@Autowired
	private RedisService redisService;

	@Autowired
	private AppInfoDao AppInfoDao;

	@Override
	public AppInfo selectByAppId(long appId) {
		return AppInfoDao.selectByAppId(appId);
	}

	@Override
	public ResponseToken buildToken(SignParam signParam) {
		Long appId = signParam.getAppId();
		AppInfo appInfo = selectByAppId(appId);
		if (appInfo == null || AppInfo.USABLE != appInfo.getStatue()) {
			throw new CheckException("appId不存在或无效");
		}

		String signStr = signParam.buildSortString() + "&secret=" + appInfo.getAppSecret();
		log.info("拼接字符串为为:{}", signStr);
		String sign = AppUtil.string2MD5(signStr);
		log.info("计算生成sign为:{}", sign);
		if (!signParam.getSign().equalsIgnoreCase(sign)) {
			throw new CheckException("签名错误");
		}

		String accessToken = AppUtil.buildAccessToken();
		Date expiresIn = DateUtil.getFutureTime2(24 * 60 * 60 * 1000);

		redisService.setWithExpire(accessToken, appInfo, 24 * 60 * 60);

		return new ResponseToken(ResponseToken.RESPCODE_SUCCESS, ResponseToken.MSG_SUCCESS, appId, accessToken,
				expiresIn);
	}

	@Override
	public int createAppInfo(String name) {
		AppInfo appInfo = new AppInfo();
		appInfo.setAppSecret(AppUtil.buildSecret());
		appInfo.setName(name);
		appInfo.setStatue(AppInfo.APPLY);
		long appId = 0L;
		synchronized (this) {
			do {
				appId = AppUtil.getId();
			} while (null != selectByAppId(appId));

			appInfo.setAppId(appId);
			AppInfoDao.insert(appInfo);
		}

		return appInfo.getId();
	}

}
