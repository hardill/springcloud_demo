package test.boot.service;

import test.boot.domain.AppInfo;
import test.boot.domain.ResponseToken;
import test.boot.domain.SignParam;

/**
 * app操作接口
 * 
 * @author Administrator
 *
 */
public interface AppService {

	/**
	 * 根据appid查询AppInfo信息
	 * 
	 * @param appId
	 * @return
	 */
	AppInfo selectByAppId(long appId);

	/**
	 * 校验并生产token
	 * 
	 * @param signParam
	 * @return
	 */
	ResponseToken buildToken(SignParam signParam);

	/**
	 * 创建appInfo
	 * 
	 * @return
	 */
	int createAppInfo(String name);

}
