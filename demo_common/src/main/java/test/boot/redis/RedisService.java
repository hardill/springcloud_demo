package test.boot.redis;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * redis 操作接口
 * 
 * @author Administrator
 *
 */
public interface RedisService {
	/** 保存key-value */
	public void set(String key, Object value);

	/**
	 * 保存key-value格式,设置过期时间time
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            秒
	 */
	public void setWithExpire(String key, Object value, long seconds);

	/** 根据key获取值 */
	public Object get(String key);

	/**
	 * 存储hash结构参数
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @param isExpire
	 *            是否设置过期时间
	 * @param seconds
	 *            过期时间,单位秒
	 */
	void HMSetWithExpire(String key, String hashKey, Object value, boolean isExpire, long seconds);

	/** 存储hash结构参数 */
	void HMSet(String key, String hashKey, Object value);

	/** 根据key获取hash值 */
	Object HMGet(String key, String hashKey);

	/** 判断redis是否存在该key */
	boolean existKey(String key);

	/** 删除key */
	void delete(String key);

	/** 删除hash中key */
	void HMDelete(String key, String hashKey);

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            秒
	 */
	void expire(String key, long seconds);

	/**
	 * 获取二级key-value中存在于Collection中的key的value
	 * 
	 * @param user
	 * @param key
	 * @return
	 */
	List<Object> multiGet(String user, Collection<Object> key);

	/**
	 * 获取二级key-value中的所有value值
	 */
	List<Object> getValues(String key);

	/**
	 * 模糊查找符合条件的key
	 */
	Set<String> like(String pattern);
}
