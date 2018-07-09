package test.boot.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import test.BaseMapper;
import test.boot.domain.AppInfo;

public interface AppInfoDao extends BaseMapper<AppInfo> {
	// id,app_id,app_secret,name,statue,channel
	@Select("SELECT * FROM test_app_info WHERE app_id=#{appId}")
	AppInfo selectByAppId(@Param("appId") long appId);

}
