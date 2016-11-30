package com.battcn.platform.framework.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Tuple;

/**
 * @author 唐亚峰
 * @ClassName: RedisOperator
 * @date 2016年9月25日
 */
public interface RedisOperator {

	void set(String key, String value, int db);

	String get(String key, int db);

	Long incr(String key, int db);

	Long incr(String key, long integer, int db);

	boolean exists(String key, int db);

	Long del(String key, int db);

	String setex(String key, int seconds, String value, int db);

	String getset(String key, String value, int db);

	Long expire(String key, int seconds, int db);

	void lpush(String key, String value, int db);

	String rpop(String key, int db);

	Long llen(String key, int db);

	List<String> rrange(String key, long start, long end, int db);

	void ltrim(String key, long start, long end, int db);

	String lindex(String key, long index, int db);

	Long rpush(String key, int db, String... strings);

	Long hset(String key, String field, String value, int db);

	Long hlen(String key, int db);

	Long hdel(String key, String field, int db);

	String hget(String key, String field, int db);

	Long hincrBy(String key, String field, long value, int db);

	List<String> hmget(String key, int db, String... fields);

	String hmset(String key, int db, Map<String, String> kvals);

	Map<String, String> hgetAll(String key, int db);

	Set<String> hkeys(String key, int db);

	boolean sismember(String key, String member, int db);

	Long sadd(int db, String key, final String... members);

	Set<String> smembers(int db, String key);

	void zadd(String key, double score, String member, int db);

	Set<String> zrangeByScore(String key, double min, double max, int db);

	Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int db);

	Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset,
			final int count, int db);

	Set<String> zrangeByScore(String key, double min, double max, int offset, int count, int db);

	Double zscore(String key, String member, int db);

	Long zrem(String key, String member, int db);

	Set<String> zrevrange(String key, long start, long end, int db);

	Set<String> zrevrangeByScore(String key, double min, double max, int db);

	Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count, int db);

	Long zcount(String key, double min, double max, int db);

	Long zcard(String key, int db);

	Long zremrangeByScore(String key, double start, double end, int db);

	Double zincrby(String key, double score, String member, int db);

	Long zrank(String key, String member, int db);

	Long zrevrank(String key, String member, int db);

	Long zremrangeByRank(String key, long start, long end, int db);

	Set<String> zrange(String key, long start, long end, int db);

	Set<Tuple> zrangeWithScores(String key, long start, long end, int db);

	Set<Tuple> zrevrangeWithScores(String key, long start, long end, int db);

	Long ttl(String key, int db);

	String type(String key, int db);

	String lpop(String key, int db);

	List<String> blpop(int db, int timeout, String... keys);

	Long srem(int db, String key, String... members);

	String srandmember(int db, String key);

	Long scard(int db, String key);

	String spop(int db, String key);

	Boolean hexists(String key, String member, int db);
}
