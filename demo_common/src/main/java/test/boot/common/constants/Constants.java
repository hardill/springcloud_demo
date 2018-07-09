package test.boot.common.constants;

/**
 * 自定义变量
 * 
 * @author Administrator
 *
 */
public class Constants {
	/** feign token存在redis的key */
	public static final String FEIGN_TOKEN_KEY = "inner.feign.token";
	/** feign存储在系统中的key */
	public static final String FEIGN_SYSTEM_KEY = "feign.system.token";
	/** redis缓存token前置key */
	public static final String TOKEN = "redis.token";
	/** 权限不足 */
	public static final String REQUIRE_AUTH = "权限不足";

}
