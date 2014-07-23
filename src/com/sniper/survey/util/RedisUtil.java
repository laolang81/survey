package com.sniper.survey.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisUtil {

	private static Map<String, Object> redisConfig = new HashMap<>();
	{
		redisConfig.put("prefix", "sniper:");
		redisConfig.put("expirTime", 300);

	}

	private static Jedis jedis = null;
	static {
		if (null == jedis) {

			PropertiesUtil propertiesUtil = new PropertiesUtil(
					"properties/redis.properties");

			String host = propertiesUtil.getValue("host");
			Integer port = propertiesUtil.getIntegerValue("port");
			String password = propertiesUtil.getValue("password");

			JedisShardInfo info = new JedisShardInfo(host, port);
			jedis = new Jedis(info);
			if (ValidateUtil.isValid(password)) {
				jedis.auth(password);
			}

		}
	}

	public RedisUtil() {

	}

	private String getKeyName(String key) {
		return (String) redisConfig.get("prefix") + key;
	}

	private String[] getKeyName(String[] key) {

		String[] newName = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			newName[i] = (String) redisConfig.get("prefix") + key[i];
		}
		return newName;
	}

	/**
	 * 设置一个普通字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String setString(String key, String value) {
		System.out.println(getKeyName(key));
		String result = jedis.set(getKeyName(key), value);
		return result;
	}

	/**
	 * 添加一个普通字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long append(String key, String value) {
		return jedis.append(key, value);
	}

	/**
	 * 返回一个结果
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		System.out.println(getKeyName(key));
		return jedis.get(getKeyName(key));
	}

	public void removeString(String key) {
		jedis.del(getKeyName(key));

	}

	public void removeString(String[] key) {
		jedis.del(key);

	}

	public void setMoreString(String[] keys) {
		jedis.mset(keys);
	}

	public List<String> getMoreString(String[] keys) {
		return jedis.mget(keys);
	}

	public void delMoreString(String[] keys) {
		jedis.del(getKeyName(keys));
	}

	public void setMap(String key, Map<String, String> map) {
		jedis.hmset(key, map);
	}

	public Map<String, String> getMap(String key) {
		return jedis.hgetAll(key);
	}

	public Long getMapKeyNum(String key) {
		return jedis.hlen(key);
	}

	public Set<String> getMapKeys(String key) {
		return jedis.hkeys(key);
	}

	public List<String> getMapValues(String key) {
		return jedis.hvals(key);
	}

	public void addList(String key, String value) {
		jedis.lpush(key, value);

	}

	public void addLists(String key, String[] value) {
		jedis.lpush(key, value);
	}

	public List<String> getList(String key, int s, int e) {
		return jedis.lrange(key, s, e);
	}

	public void delList(String key) {
		jedis.lpop(key);
	}

	public void addSet(String key, String members) {
		jedis.sadd(key, members);

	}

	public void delObject(String key, String members) {
		jedis.srem(key, members);
	}

}
