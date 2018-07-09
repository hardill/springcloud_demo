package test.boot.redis;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void set(String key, Object value) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);

	}

	public void setWithExpire(String key, Object value, long seconds) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
		this.expire(key, seconds);
	}

	@Override
	public Object get(String key) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	@Override
	public void HMSetWithExpire(String key, String hashKey, Object value, boolean isExpire, long seconds) {
		redisTemplate.opsForHash().put(key, hashKey, value);
		if (isExpire) {
			expire(key, seconds);
		}
	}

	@Override
	public void HMSet(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);

	}

	@Override
	public Object HMGet(String str, String key) {
		return redisTemplate.opsForHash().get(str, key);
	}

	public void expire(String key, long seconds) {
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	@Override
	public boolean existKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void delete(String key) {
		if (existKey(key)) {
			redisTemplate.delete(key);
		}
	}

	public void HMDelete(String str1, String str2) {
		HashOperations<String, Object, Object> operation = redisTemplate.opsForHash();
		if (operation.hasKey(str1, str2)) {
			operation.delete(str1, str2);
		}
	}

	public List<Object> multiGet(String user, Collection<Object> key) {
		return redisTemplate.opsForHash().multiGet(user, key);
	}

	/**
	 * 获取二级key-value中的所有value值
	 */
	public List<Object> getValues(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 模糊查找符合条件的key
	 */
	public Set<String> like(String pattern) {
		return redisTemplate.keys(pattern);
	}

}
