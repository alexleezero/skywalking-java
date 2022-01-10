package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterHostAndPortMap;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamPendingEntry;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijian
 * @since 2022/1/7
 */
public class CopyJedis extends JedisCluster {

	public CopyJedis(HostAndPort node) {
		super(node);
	}

	public CopyJedis(HostAndPort node, int timeout) {
		super(node, timeout);
	}

	public CopyJedis(HostAndPort node, int timeout, int maxAttempts) {
		super(node, timeout, maxAttempts);
	}

	public CopyJedis(HostAndPort node, GenericObjectPoolConfig poolConfig) {
		super(node, poolConfig);
	}

	public CopyJedis(HostAndPort node, int timeout, GenericObjectPoolConfig poolConfig) {
		super(node, timeout, poolConfig);
	}

	public CopyJedis(HostAndPort node, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(node, timeout, maxAttempts, poolConfig);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(node, connectionTimeout, soTimeout, maxAttempts, poolConfig);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {
		super(node, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig) {
		super(node, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig) {
		super(node, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl) {
		super(node, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig, ssl);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl) {
		super(node, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig, ssl);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier, JedisClusterHostAndPortMap hostAndPortMap) {
		super(node, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig, ssl, sslSocketFactory, sslParameters, hostnameVerifier, hostAndPortMap);
	}

	public CopyJedis(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier, JedisClusterHostAndPortMap hostAndPortMap) {
		super(node, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig, ssl, sslSocketFactory, sslParameters, hostnameVerifier, hostAndPortMap);
	}

	public CopyJedis(Set<HostAndPort> nodes) {
		super(nodes);
	}

	public CopyJedis(Set<HostAndPort> nodes, int timeout) {
		super(nodes, timeout);
	}

	public CopyJedis(Set<HostAndPort> nodes, int timeout, int maxAttempts) {
		super(nodes, timeout, maxAttempts);
	}

	public CopyJedis(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig) {
		super(nodes, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> nodes, int timeout, GenericObjectPoolConfig poolConfig) {
		super(nodes, timeout, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, timeout, maxAttempts, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig, ssl);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig, ssl);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier, JedisClusterHostAndPortMap hostAndPortMap) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig, ssl, sslSocketFactory, sslParameters, hostnameVerifier, hostAndPortMap);
	}

	public CopyJedis(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String user, String password, String clientName, GenericObjectPoolConfig poolConfig, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier, JedisClusterHostAndPortMap hostAndPortMap) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, user, password, clientName, poolConfig, ssl, sslSocketFactory, sslParameters, hostnameVerifier, hostAndPortMap);
	}

	@Override
	public String set(String key, String value) {
		return super.set(key, value);
	}

	@Override
	public String set(String key, String value, SetParams params) {
		return super.set(key, value, params);
	}

	@Override
	public String get(String key) {
		return super.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return super.exists(key);
	}

	@Override
	public Long exists(String... keys) {
		return super.exists(keys);
	}

	@Override
	public Long persist(String key) {
		return super.persist(key);
	}

	@Override
	public String type(String key) {
		return super.type(key);
	}

	@Override
	public byte[] dump(String key) {
		return super.dump(key);
	}

	@Override
	public String restore(String key, int ttl, byte[] serializedValue) {
		return super.restore(key, ttl, serializedValue);
	}

	@Override
	public Long expire(String key, int seconds) {
		return super.expire(key, seconds);
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		return super.pexpire(key, milliseconds);
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		return super.expireAt(key, unixTime);
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		return super.pexpireAt(key, millisecondsTimestamp);
	}

	@Override
	public Long ttl(String key) {
		return super.ttl(key);
	}

	@Override
	public Long pttl(String key) {
		return super.pttl(key);
	}

	@Override
	public Long touch(String key) {
		return super.touch(key);
	}

	@Override
	public Long touch(String... keys) {
		return super.touch(keys);
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		return super.setbit(key, offset, value);
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		return super.setbit(key, offset, value);
	}

	@Override
	public Boolean getbit(String key, long offset) {
		return super.getbit(key, offset);
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		return super.setrange(key, offset, value);
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		return super.getrange(key, startOffset, endOffset);
	}

	@Override
	public String getSet(String key, String value) {
		return super.getSet(key, value);
	}

	@Override
	public Long setnx(String key, String value) {
		return super.setnx(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		return super.setex(key, seconds, value);
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		return super.psetex(key, milliseconds, value);
	}

	@Override
	public Long decrBy(String key, long decrement) {
		return super.decrBy(key, decrement);
	}

	@Override
	public Long decr(String key) {
		return super.decr(key);
	}

	@Override
	public Long incrBy(String key, long increment) {
		return super.incrBy(key, increment);
	}

	@Override
	public Double incrByFloat(String key, double increment) {
		return super.incrByFloat(key, increment);
	}

	@Override
	public Long incr(String key) {
		return super.incr(key);
	}

	@Override
	public Long append(String key, String value) {
		return super.append(key, value);
	}

	@Override
	public String substr(String key, int start, int end) {
		return super.substr(key, start, end);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return super.hset(key, field, value);
	}

	@Override
	public Long hset(String key, Map<String, String> hash) {
		return super.hset(key, hash);
	}

	@Override
	public String hget(String key, String field) {
		return super.hget(key, field);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		return super.hsetnx(key, field, value);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return super.hmset(key, hash);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return super.hmget(key, fields);
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		return super.hincrBy(key, field, value);
	}

	@Override
	public Boolean hexists(String key, String field) {
		return super.hexists(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return super.hdel(key, field);
	}

	@Override
	public Long hlen(String key) {
		return super.hlen(key);
	}

	@Override
	public Set<String> hkeys(String key) {
		return super.hkeys(key);
	}

	@Override
	public List<String> hvals(String key) {
		return super.hvals(key);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return super.hgetAll(key);
	}

	@Override
	public Long rpush(String key, String... string) {
		return super.rpush(key, string);
	}

	@Override
	public Long lpush(String key, String... string) {
		return super.lpush(key, string);
	}

	@Override
	public Long llen(String key) {
		return super.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long stop) {
		return super.lrange(key, start, stop);
	}

	@Override
	public String ltrim(String key, long start, long stop) {
		return super.ltrim(key, start, stop);
	}

	@Override
	public String lindex(String key, long index) {
		return super.lindex(key, index);
	}

	@Override
	public String lset(String key, long index, String value) {
		return super.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		return super.lrem(key, count, value);
	}

	@Override
	public String lpop(String key) {
		return super.lpop(key);
	}

	@Override
	public String rpop(String key) {
		return super.rpop(key);
	}

	@Override
	public Long sadd(String key, String... member) {
		return super.sadd(key, member);
	}

	@Override
	public Set<String> smembers(String key) {
		return super.smembers(key);
	}

	@Override
	public Long srem(String key, String... member) {
		return super.srem(key, member);
	}

	@Override
	public String spop(String key) {
		return super.spop(key);
	}

	@Override
	public Set<String> spop(String key, long count) {
		return super.spop(key, count);
	}

	@Override
	public Long scard(String key) {
		return super.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		return super.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		return super.srandmember(key);
	}

	@Override
	public List<String> srandmember(String key, int count) {
		return super.srandmember(key, count);
	}

	@Override
	public Long strlen(String key) {
		return super.strlen(key);
	}

	@Override
	public Long zadd(String key, double score, String member) {
		return super.zadd(key, score, member);
	}

	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		return super.zadd(key, score, member, params);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		return super.zadd(key, scoreMembers);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		return super.zadd(key, scoreMembers, params);
	}

	@Override
	public Set<String> zrange(String key, long start, long stop) {
		return super.zrange(key, start, stop);
	}

	@Override
	public Long zrem(String key, String... members) {
		return super.zrem(key, members);
	}

	@Override
	public Double zincrby(String key, double increment, String member) {
		return super.zincrby(key, increment, member);
	}

	@Override
	public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
		return super.zincrby(key, increment, member, params);
	}

	@Override
	public Long zrank(String key, String member) {
		return super.zrank(key, member);
	}

	@Override
	public Long zrevrank(String key, String member) {
		return super.zrevrank(key, member);
	}

	@Override
	public Set<String> zrevrange(String key, long start, long stop) {
		return super.zrevrange(key, start, stop);
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
		return super.zrangeWithScores(key, start, stop);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
		return super.zrevrangeWithScores(key, start, stop);
	}

	@Override
	public Long zcard(String key) {
		return super.zcard(key);
	}

	@Override
	public Double zscore(String key, String member) {
		return super.zscore(key, member);
	}

	@Override
	public Tuple zpopmax(String key) {
		return super.zpopmax(key);
	}

	@Override
	public Set<Tuple> zpopmax(String key, int count) {
		return super.zpopmax(key, count);
	}

	@Override
	public Tuple zpopmin(String key) {
		return super.zpopmin(key);
	}

	@Override
	public Set<Tuple> zpopmin(String key, int count) {
		return super.zpopmin(key, count);
	}

	@Override
	public List<String> sort(String key) {
		return super.sort(key);
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		return super.sort(key, sortingParameters);
	}

	@Override
	public Long zcount(String key, double min, double max) {
		return super.zcount(key, min, max);
	}

	@Override
	public Long zcount(String key, String min, String max) {
		return super.zcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		return super.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		return super.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		return super.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		return super.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		return super.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		return super.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		return super.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		return super.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		return super.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		return super.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		return super.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		return super.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		return super.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		return super.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByRank(String key, long start, long stop) {
		return super.zremrangeByRank(key, start, stop);
	}

	@Override
	public Long zremrangeByScore(String key, double min, double max) {
		return super.zremrangeByScore(key, min, max);
	}

	@Override
	public Long zremrangeByScore(String key, String min, String max) {
		return super.zremrangeByScore(key, min, max);
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		return super.zlexcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		return super.zrangeByLex(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		return super.zrangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		return super.zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		return super.zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		return super.zremrangeByLex(key, min, max);
	}

	@Override
	public Long linsert(String key, ListPosition where, String pivot, String value) {
		return super.linsert(key, where, pivot, value);
	}

	@Override
	public Long lpushx(String key, String... string) {
		return super.lpushx(key, string);
	}

	@Override
	public Long rpushx(String key, String... string) {
		return super.rpushx(key, string);
	}

	@Override
	public Long del(String key) {
		return super.del(key);
	}

	@Override
	public Long unlink(String key) {
		return super.unlink(key);
	}

	@Override
	public Long unlink(String... keys) {
		return super.unlink(keys);
	}

	@Override
	public String echo(String string) {
		return super.echo(string);
	}

	@Override
	public Long bitcount(String key) {
		return super.bitcount(key);
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		return super.bitcount(key, start, end);
	}

	@Override
	public Set<String> keys(String pattern) {
		return super.keys(pattern);
	}

	@Override
	public ScanResult<String> scan(String cursor, ScanParams params) {
		return super.scan(cursor, params);
	}

	@Override
	public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
		return super.hscan(key, cursor);
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		return super.sscan(key, cursor);
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		return super.zscan(key, cursor);
	}

	@Override
	public Long pfadd(String key, String... elements) {
		return super.pfadd(key, elements);
	}

	@Override
	public long pfcount(String key) {
		return super.pfcount(key);
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		return super.blpop(timeout, key);
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		return super.brpop(timeout, key);
	}

	@Override
	public Long del(String... keys) {
		return super.del(keys);
	}

	@Override
	public List<String> blpop(int timeout, String... keys) {
		return super.blpop(timeout, keys);
	}

	@Override
	public List<String> brpop(int timeout, String... keys) {
		return super.brpop(timeout, keys);
	}

	@Override
	public List<String> mget(String... keys) {
		return super.mget(keys);
	}

	@Override
	public String mset(String... keysvalues) {
		return super.mset(keysvalues);
	}

	@Override
	public Long msetnx(String... keysvalues) {
		return super.msetnx(keysvalues);
	}

	@Override
	public String rename(String oldkey, String newkey) {
		return super.rename(oldkey, newkey);
	}

	@Override
	public Long renamenx(String oldkey, String newkey) {
		return super.renamenx(oldkey, newkey);
	}

	@Override
	public String rpoplpush(String srckey, String dstkey) {
		return super.rpoplpush(srckey, dstkey);
	}

	@Override
	public Set<String> sdiff(String... keys) {
		return super.sdiff(keys);
	}

	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		return super.sdiffstore(dstkey, keys);
	}

	@Override
	public Set<String> sinter(String... keys) {
		return super.sinter(keys);
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		return super.sinterstore(dstkey, keys);
	}

	@Override
	public Long smove(String srckey, String dstkey, String member) {
		return super.smove(srckey, dstkey, member);
	}

	@Override
	public Long sort(String key, SortingParams sortingParameters, String dstkey) {
		return super.sort(key, sortingParameters, dstkey);
	}

	@Override
	public Long sort(String key, String dstkey) {
		return super.sort(key, dstkey);
	}

	@Override
	public Set<String> sunion(String... keys) {
		return super.sunion(keys);
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		return super.sunionstore(dstkey, keys);
	}

	@Override
	public Long zinterstore(String dstkey, String... sets) {
		return super.zinterstore(dstkey, sets);
	}

	@Override
	public Long zinterstore(String dstkey, ZParams params, String... sets) {
		return super.zinterstore(dstkey, params, sets);
	}

	@Override
	public Long zunionstore(String dstkey, String... sets) {
		return super.zunionstore(dstkey, sets);
	}

	@Override
	public Long zunionstore(String dstkey, ZParams params, String... sets) {
		return super.zunionstore(dstkey, params, sets);
	}

	@Override
	public String brpoplpush(String source, String destination, int timeout) {
		return super.brpoplpush(source, destination, timeout);
	}

	@Override
	public Long publish(String channel, String message) {
		return super.publish(channel, message);
	}

	@Override
	public void subscribe(JedisPubSub jedisPubSub, String... channels) {
		super.subscribe(jedisPubSub, channels);
	}

	@Override
	public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
		super.psubscribe(jedisPubSub, patterns);
	}

	@Override
	public Long bitop(BitOP op, String destKey, String... srcKeys) {
		return super.bitop(op, destKey, srcKeys);
	}

	@Override
	public String pfmerge(String destkey, String... sourcekeys) {
		return super.pfmerge(destkey, sourcekeys);
	}

	@Override
	public long pfcount(String... keys) {
		return super.pfcount(keys);
	}

	@Override
	public Object eval(String script, int keyCount, String... params) {
		return super.eval(script, keyCount, params);
	}

	@Override
	public Object eval(String script, String sampleKey) {
		return super.eval(script, sampleKey);
	}

	@Override
	public Object eval(String script, List<String> keys, List<String> args) {
		return super.eval(script, keys, args);
	}

	@Override
	public Object evalsha(String sha1, int keyCount, String... params) {
		return super.evalsha(sha1, keyCount, params);
	}

	@Override
	public Object evalsha(String sha1, List<String> keys, List<String> args) {
		return super.evalsha(sha1, keys, args);
	}

	@Override
	public Object evalsha(String sha1, String sampleKey) {
		return super.evalsha(sha1, sampleKey);
	}

	@Override
	public Boolean scriptExists(String sha1, String sampleKey) {
		return super.scriptExists(sha1, sampleKey);
	}

	@Override
	public List<Boolean> scriptExists(String sampleKey, String... sha1) {
		return super.scriptExists(sampleKey, sha1);
	}

	@Override
	public String scriptLoad(String script, String sampleKey) {
		return super.scriptLoad(script, sampleKey);
	}

	@Override
	public String scriptFlush(String sampleKey) {
		return super.scriptFlush(sampleKey);
	}

	@Override
	public String scriptKill(String sampleKey) {
		return super.scriptKill(sampleKey);
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		return super.geoadd(key, longitude, latitude, member);
	}

	@Override
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		return super.geoadd(key, memberCoordinateMap);
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		return super.geodist(key, member1, member2);
	}

	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		return super.geodist(key, member1, member2, unit);
	}

	@Override
	public List<String> geohash(String key, String... members) {
		return super.geohash(key, members);
	}

	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		return super.geopos(key, members);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
		return super.georadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit) {
		return super.georadiusReadonly(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadius(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusReadonly(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
		return super.georadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
		return super.georadiusByMemberReadonly(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusByMember(key, member, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusByMemberReadonly(key, member, radius, unit, param);
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		return super.bitfield(key, arguments);
	}

	@Override
	public List<Long> bitfieldReadonly(String key, String... arguments) {
		return super.bitfieldReadonly(key, arguments);
	}

	@Override
	public Long hstrlen(String key, String field) {
		return super.hstrlen(key, field);
	}

	@Override
	public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
		return super.xadd(key, id, hash);
	}

	@Override
	public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen, boolean approximateLength) {
		return super.xadd(key, id, hash, maxLen, approximateLength);
	}

	@Override
	public Long xlen(String key) {
		return super.xlen(key);
	}

	@Override
	public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
		return super.xrange(key, start, end, count);
	}

	@Override
	public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
		return super.xrevrange(key, end, start, count);
	}

	@Override
	public List<Map.Entry<String, List<StreamEntry>>> xread(int count, long block, Map.Entry<String, StreamEntryID>... streams) {
		return super.xread(count, block, streams);
	}

	@Override
	public Long xack(String key, String group, StreamEntryID... ids) {
		return super.xack(key, group, ids);
	}

	@Override
	public String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
		return super.xgroupCreate(key, groupname, id, makeStream);
	}

	@Override
	public String xgroupSetID(String key, String groupname, StreamEntryID id) {
		return super.xgroupSetID(key, groupname, id);
	}

	@Override
	public Long xgroupDestroy(String key, String groupname) {
		return super.xgroupDestroy(key, groupname);
	}

	@Override
	public Long xgroupDelConsumer(String key, String groupname, String consumername) {
		return super.xgroupDelConsumer(key, groupname, consumername);
	}

	@Override
	public List<Map.Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block, boolean noAck, Map.Entry<String, StreamEntryID>... streams) {
		return super.xreadGroup(groupname, consumer, count, block, noAck, streams);
	}

	@Override
	public List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end, int count, String consumername) {
		return super.xpending(key, groupname, start, end, count, consumername);
	}

	@Override
	public Long xdel(String key, StreamEntryID... ids) {
		return super.xdel(key, ids);
	}

	@Override
	public Long xtrim(String key, long maxLen, boolean approximateLength) {
		return super.xtrim(key, maxLen, approximateLength);
	}

	@Override
	public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime, int retries, boolean force, StreamEntryID... ids) {
		return super.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
	}

	@Override
	public Long waitReplicas(String key, int replicas, long timeout) {
		return super.waitReplicas(key, replicas, timeout);
	}

	@Override
	public Object sendCommand(String sampleKey, ProtocolCommand cmd, String... args) {
		return super.sendCommand(sampleKey, cmd, args);
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public Map<String, JedisPool> getClusterNodes() {
		return super.getClusterNodes();
	}

	@Override
	public Jedis getConnectionFromSlot(int slot) {
		return super.getConnectionFromSlot(slot);
	}

	@Override
	public String set(byte[] key, byte[] value) {
		return super.set(key, value);
	}

	@Override
	public String set(byte[] key, byte[] value, SetParams params) {
		return super.set(key, value, params);
	}

	@Override
	public byte[] get(byte[] key) {
		return super.get(key);
	}

	@Override
	public Long exists(byte[]... keys) {
		return super.exists(keys);
	}

	@Override
	public Boolean exists(byte[] key) {
		return super.exists(key);
	}

	@Override
	public Long persist(byte[] key) {
		return super.persist(key);
	}

	@Override
	public String type(byte[] key) {
		return super.type(key);
	}

	@Override
	public byte[] dump(byte[] key) {
		return super.dump(key);
	}

	@Override
	public String restore(byte[] key, int ttl, byte[] serializedValue) {
		return super.restore(key, ttl, serializedValue);
	}

	@Override
	public Long expire(byte[] key, int seconds) {
		return super.expire(key, seconds);
	}

	@Override
	public Long pexpire(byte[] key, long milliseconds) {
		return super.pexpire(key, milliseconds);
	}

	@Override
	public Long expireAt(byte[] key, long unixTime) {
		return super.expireAt(key, unixTime);
	}

	@Override
	public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
		return super.pexpireAt(key, millisecondsTimestamp);
	}

	@Override
	public Long ttl(byte[] key) {
		return super.ttl(key);
	}

	@Override
	public Long pttl(byte[] key) {
		return super.pttl(key);
	}

	@Override
	public Long touch(byte[] key) {
		return super.touch(key);
	}

	@Override
	public Long touch(byte[]... keys) {
		return super.touch(keys);
	}

	@Override
	public Boolean setbit(byte[] key, long offset, boolean value) {
		return super.setbit(key, offset, value);
	}

	@Override
	public Boolean setbit(byte[] key, long offset, byte[] value) {
		return super.setbit(key, offset, value);
	}

	@Override
	public Boolean getbit(byte[] key, long offset) {
		return super.getbit(key, offset);
	}

	@Override
	public Long setrange(byte[] key, long offset, byte[] value) {
		return super.setrange(key, offset, value);
	}

	@Override
	public byte[] getrange(byte[] key, long startOffset, long endOffset) {
		return super.getrange(key, startOffset, endOffset);
	}

	@Override
	public byte[] getSet(byte[] key, byte[] value) {
		return super.getSet(key, value);
	}

	@Override
	public Long setnx(byte[] key, byte[] value) {
		return super.setnx(key, value);
	}

	@Override
	public String psetex(byte[] key, long milliseconds, byte[] value) {
		return super.psetex(key, milliseconds, value);
	}

	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		return super.setex(key, seconds, value);
	}

	@Override
	public Long decrBy(byte[] key, long decrement) {
		return super.decrBy(key, decrement);
	}

	@Override
	public Long decr(byte[] key) {
		return super.decr(key);
	}

	@Override
	public Long incrBy(byte[] key, long increment) {
		return super.incrBy(key, increment);
	}

	@Override
	public Double incrByFloat(byte[] key, double increment) {
		return super.incrByFloat(key, increment);
	}

	@Override
	public Long incr(byte[] key) {
		return super.incr(key);
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		return super.append(key, value);
	}

	@Override
	public byte[] substr(byte[] key, int start, int end) {
		return super.substr(key, start, end);
	}

	@Override
	public Long hset(byte[] key, byte[] field, byte[] value) {
		return super.hset(key, field, value);
	}

	@Override
	public Long hset(byte[] key, Map<byte[], byte[]> hash) {
		return super.hset(key, hash);
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		return super.hget(key, field);
	}

	@Override
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		return super.hsetnx(key, field, value);
	}

	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		return super.hmset(key, hash);
	}

	@Override
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		return super.hmget(key, fields);
	}

	@Override
	public Long hincrBy(byte[] key, byte[] field, long value) {
		return super.hincrBy(key, field, value);
	}

	@Override
	public Double hincrByFloat(byte[] key, byte[] field, double value) {
		return super.hincrByFloat(key, field, value);
	}

	@Override
	public Boolean hexists(byte[] key, byte[] field) {
		return super.hexists(key, field);
	}

	@Override
	public Long hdel(byte[] key, byte[]... field) {
		return super.hdel(key, field);
	}

	@Override
	public Long hlen(byte[] key) {
		return super.hlen(key);
	}

	@Override
	public Set<byte[]> hkeys(byte[] key) {
		return super.hkeys(key);
	}

	@Override
	public List<byte[]> hvals(byte[] key) {
		return super.hvals(key);
	}

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		return super.hgetAll(key);
	}

	@Override
	public Long rpush(byte[] key, byte[]... args) {
		return super.rpush(key, args);
	}

	@Override
	public Long lpush(byte[] key, byte[]... args) {
		return super.lpush(key, args);
	}

	@Override
	public Long llen(byte[] key) {
		return super.llen(key);
	}

	@Override
	public List<byte[]> lrange(byte[] key, long start, long stop) {
		return super.lrange(key, start, stop);
	}

	@Override
	public String ltrim(byte[] key, long start, long stop) {
		return super.ltrim(key, start, stop);
	}

	@Override
	public byte[] lindex(byte[] key, long index) {
		return super.lindex(key, index);
	}

	@Override
	public String lset(byte[] key, long index, byte[] value) {
		return super.lset(key, index, value);
	}

	@Override
	public Long lrem(byte[] key, long count, byte[] value) {
		return super.lrem(key, count, value);
	}

	@Override
	public byte[] lpop(byte[] key) {
		return super.lpop(key);
	}

	@Override
	public byte[] rpop(byte[] key) {
		return super.rpop(key);
	}

	@Override
	public Long sadd(byte[] key, byte[]... member) {
		return super.sadd(key, member);
	}

	@Override
	public Set<byte[]> smembers(byte[] key) {
		return super.smembers(key);
	}

	@Override
	public Long srem(byte[] key, byte[]... member) {
		return super.srem(key, member);
	}

	@Override
	public byte[] spop(byte[] key) {
		return super.spop(key);
	}

	@Override
	public Set<byte[]> spop(byte[] key, long count) {
		return super.spop(key, count);
	}

	@Override
	public Long scard(byte[] key) {
		return super.scard(key);
	}

	@Override
	public Boolean sismember(byte[] key, byte[] member) {
		return super.sismember(key, member);
	}

	@Override
	public byte[] srandmember(byte[] key) {
		return super.srandmember(key);
	}

	@Override
	public Long strlen(byte[] key) {
		return super.strlen(key);
	}

	@Override
	public Long zadd(byte[] key, double score, byte[] member) {
		return super.zadd(key, score, member);
	}

	@Override
	public Long zadd(byte[] key, double score, byte[] member, ZAddParams params) {
		return super.zadd(key, score, member, params);
	}

	@Override
	public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
		return super.zadd(key, scoreMembers);
	}

	@Override
	public Long zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
		return super.zadd(key, scoreMembers, params);
	}

	@Override
	public Set<byte[]> zrange(byte[] key, long start, long stop) {
		return super.zrange(key, start, stop);
	}

	@Override
	public Long zrem(byte[] key, byte[]... members) {
		return super.zrem(key, members);
	}

	@Override
	public Double zincrby(byte[] key, double increment, byte[] member) {
		return super.zincrby(key, increment, member);
	}

	@Override
	public Double zincrby(byte[] key, double increment, byte[] member, ZIncrByParams params) {
		return super.zincrby(key, increment, member, params);
	}

	@Override
	public Long zrank(byte[] key, byte[] member) {
		return super.zrank(key, member);
	}

	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		return super.zrevrank(key, member);
	}

	@Override
	public Set<byte[]> zrevrange(byte[] key, long start, long stop) {
		return super.zrevrange(key, start, stop);
	}

	@Override
	public Set<Tuple> zrangeWithScores(byte[] key, long start, long stop) {
		return super.zrangeWithScores(key, start, stop);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long stop) {
		return super.zrevrangeWithScores(key, start, stop);
	}

	@Override
	public Long zcard(byte[] key) {
		return super.zcard(key);
	}

	@Override
	public Double zscore(byte[] key, byte[] member) {
		return super.zscore(key, member);
	}

	@Override
	public Tuple zpopmax(byte[] key) {
		return super.zpopmax(key);
	}

	@Override
	public Set<Tuple> zpopmax(byte[] key, int count) {
		return super.zpopmax(key, count);
	}

	@Override
	public Tuple zpopmin(byte[] key) {
		return super.zpopmin(key);
	}

	@Override
	public Set<Tuple> zpopmin(byte[] key, int count) {
		return super.zpopmin(key, count);
	}

	@Override
	public List<byte[]> sort(byte[] key) {
		return super.sort(key);
	}

	@Override
	public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
		return super.sort(key, sortingParameters);
	}

	@Override
	public Long zcount(byte[] key, double min, double max) {
		return super.zcount(key, min, max);
	}

	@Override
	public Long zcount(byte[] key, byte[] min, byte[] max) {
		return super.zcount(key, min, max);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		return super.zrangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
		return super.zrangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
		return super.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
		return super.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
		return super.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return super.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
		return super.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		return super.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
		return super.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
		return super.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return super.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
		return super.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
		return super.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return super.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
		return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByRank(byte[] key, long start, long stop) {
		return super.zremrangeByRank(key, start, stop);
	}

	@Override
	public Long zremrangeByScore(byte[] key, double min, double max) {
		return super.zremrangeByScore(key, min, max);
	}

	@Override
	public Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
		return super.zremrangeByScore(key, min, max);
	}

	@Override
	public Long linsert(byte[] key, ListPosition where, byte[] pivot, byte[] value) {
		return super.linsert(key, where, pivot, value);
	}

	@Override
	public Long lpushx(byte[] key, byte[]... arg) {
		return super.lpushx(key, arg);
	}

	@Override
	public Long rpushx(byte[] key, byte[]... arg) {
		return super.rpushx(key, arg);
	}

	@Override
	public Long del(byte[] key) {
		return super.del(key);
	}

	@Override
	public Long unlink(byte[] key) {
		return super.unlink(key);
	}

	@Override
	public Long unlink(byte[]... keys) {
		return super.unlink(keys);
	}

	@Override
	public byte[] echo(byte[] arg) {
		return super.echo(arg);
	}

	@Override
	public Long bitcount(byte[] key) {
		return super.bitcount(key);
	}

	@Override
	public Long bitcount(byte[] key, long start, long end) {
		return super.bitcount(key, start, end);
	}

	@Override
	public Long pfadd(byte[] key, byte[]... elements) {
		return super.pfadd(key, elements);
	}

	@Override
	public long pfcount(byte[] key) {
		return super.pfcount(key);
	}

	@Override
	public List<byte[]> srandmember(byte[] key, int count) {
		return super.srandmember(key, count);
	}

	@Override
	public Long zlexcount(byte[] key, byte[] min, byte[] max) {
		return super.zlexcount(key, min, max);
	}

	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
		return super.zrangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return super.zrangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
		return super.zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return super.zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
		return super.zremrangeByLex(key, min, max);
	}

	@Override
	public Object eval(byte[] script, byte[] keyCount, byte[]... params) {
		return super.eval(script, keyCount, params);
	}

	@Override
	public Object eval(byte[] script, int keyCount, byte[]... params) {
		return super.eval(script, keyCount, params);
	}

	@Override
	public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
		return super.eval(script, keys, args);
	}

	@Override
	public Object eval(byte[] script, byte[] sampleKey) {
		return super.eval(script, sampleKey);
	}

	@Override
	public Object evalsha(byte[] sha1, byte[] sampleKey) {
		return super.evalsha(sha1, sampleKey);
	}

	@Override
	public Object evalsha(byte[] sha1, List<byte[]> keys, List<byte[]> args) {
		return super.evalsha(sha1, keys, args);
	}

	@Override
	public Object evalsha(byte[] sha1, int keyCount, byte[]... params) {
		return super.evalsha(sha1, keyCount, params);
	}

	@Override
	public List<Long> scriptExists(byte[] sampleKey, byte[]... sha1) {
		return super.scriptExists(sampleKey, sha1);
	}

	@Override
	public byte[] scriptLoad(byte[] script, byte[] sampleKey) {
		return super.scriptLoad(script, sampleKey);
	}

	@Override
	public String scriptFlush(byte[] sampleKey) {
		return super.scriptFlush(sampleKey);
	}

	@Override
	public String scriptKill(byte[] sampleKey) {
		return super.scriptKill(sampleKey);
	}

	@Override
	public Long del(byte[]... keys) {
		return super.del(keys);
	}

	@Override
	public List<byte[]> blpop(int timeout, byte[]... keys) {
		return super.blpop(timeout, keys);
	}

	@Override
	public List<byte[]> brpop(int timeout, byte[]... keys) {
		return super.brpop(timeout, keys);
	}

	@Override
	public List<byte[]> mget(byte[]... keys) {
		return super.mget(keys);
	}

	@Override
	public String mset(byte[]... keysvalues) {
		return super.mset(keysvalues);
	}

	@Override
	public Long msetnx(byte[]... keysvalues) {
		return super.msetnx(keysvalues);
	}

	@Override
	public String rename(byte[] oldkey, byte[] newkey) {
		return super.rename(oldkey, newkey);
	}

	@Override
	public Long renamenx(byte[] oldkey, byte[] newkey) {
		return super.renamenx(oldkey, newkey);
	}

	@Override
	public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
		return super.rpoplpush(srckey, dstkey);
	}

	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		return super.sdiff(keys);
	}

	@Override
	public Long sdiffstore(byte[] dstkey, byte[]... keys) {
		return super.sdiffstore(dstkey, keys);
	}

	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		return super.sinter(keys);
	}

	@Override
	public Long sinterstore(byte[] dstkey, byte[]... keys) {
		return super.sinterstore(dstkey, keys);
	}

	@Override
	public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
		return super.smove(srckey, dstkey, member);
	}

	@Override
	public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
		return super.sort(key, sortingParameters, dstkey);
	}

	@Override
	public Long sort(byte[] key, byte[] dstkey) {
		return super.sort(key, dstkey);
	}

	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		return super.sunion(keys);
	}

	@Override
	public Long sunionstore(byte[] dstkey, byte[]... keys) {
		return super.sunionstore(dstkey, keys);
	}

	@Override
	public Long zinterstore(byte[] dstkey, byte[]... sets) {
		return super.zinterstore(dstkey, sets);
	}

	@Override
	public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
		return super.zinterstore(dstkey, params, sets);
	}

	@Override
	public Long zunionstore(byte[] dstkey, byte[]... sets) {
		return super.zunionstore(dstkey, sets);
	}

	@Override
	public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
		return super.zunionstore(dstkey, params, sets);
	}

	@Override
	public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
		return super.brpoplpush(source, destination, timeout);
	}

	@Override
	public Long publish(byte[] channel, byte[] message) {
		return super.publish(channel, message);
	}

	@Override
	public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
		super.subscribe(jedisPubSub, channels);
	}

	@Override
	public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
		super.psubscribe(jedisPubSub, patterns);
	}

	@Override
	public Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
		return super.bitop(op, destKey, srcKeys);
	}

	@Override
	public String pfmerge(byte[] destkey, byte[]... sourcekeys) {
		return super.pfmerge(destkey, sourcekeys);
	}

	@Override
	public Long pfcount(byte[]... keys) {
		return super.pfcount(keys);
	}

	@Override
	public Long geoadd(byte[] key, double longitude, double latitude, byte[] member) {
		return super.geoadd(key, longitude, latitude, member);
	}

	@Override
	public Long geoadd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
		return super.geoadd(key, memberCoordinateMap);
	}

	@Override
	public Double geodist(byte[] key, byte[] member1, byte[] member2) {
		return super.geodist(key, member1, member2);
	}

	@Override
	public Double geodist(byte[] key, byte[] member1, byte[] member2, GeoUnit unit) {
		return super.geodist(key, member1, member2, unit);
	}

	@Override
	public List<byte[]> geohash(byte[] key, byte[]... members) {
		return super.geohash(key, members);
	}

	@Override
	public List<GeoCoordinate> geopos(byte[] key, byte[]... members) {
		return super.geopos(key, members);
	}

	@Override
	public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
		return super.georadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
		return super.georadiusReadonly(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadius(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusReadonly(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit) {
		return super.georadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit) {
		return super.georadiusByMemberReadonly(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusByMember(key, member, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
		return super.georadiusByMemberReadonly(key, member, radius, unit, param);
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		return super.keys(pattern);
	}

	@Override
	public ScanResult<byte[]> scan(byte[] cursor, ScanParams params) {
		return super.scan(cursor, params);
	}

	@Override
	public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor) {
		return super.hscan(key, cursor);
	}

	@Override
	public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor, ScanParams params) {
		return super.hscan(key, cursor, params);
	}

	@Override
	public ScanResult<byte[]> sscan(byte[] key, byte[] cursor) {
		return super.sscan(key, cursor);
	}

	@Override
	public ScanResult<byte[]> sscan(byte[] key, byte[] cursor, ScanParams params) {
		return super.sscan(key, cursor, params);
	}

	@Override
	public ScanResult<Tuple> zscan(byte[] key, byte[] cursor) {
		return super.zscan(key, cursor);
	}

	@Override
	public ScanResult<Tuple> zscan(byte[] key, byte[] cursor, ScanParams params) {
		return super.zscan(key, cursor, params);
	}

	@Override
	public List<Long> bitfield(byte[] key, byte[]... arguments) {
		return super.bitfield(key, arguments);
	}

	@Override
	public List<Long> bitfieldReadonly(byte[] key, byte[]... arguments) {
		return super.bitfieldReadonly(key, arguments);
	}

	@Override
	public Long hstrlen(byte[] key, byte[] field) {
		return super.hstrlen(key, field);
	}

	@Override
	public byte[] xadd(byte[] key, byte[] id, Map<byte[], byte[]> hash, long maxLen, boolean approximateLength) {
		return super.xadd(key, id, hash, maxLen, approximateLength);
	}

	@Override
	public Long xlen(byte[] key) {
		return super.xlen(key);
	}

	@Override
	public List<byte[]> xrange(byte[] key, byte[] start, byte[] end, long count) {
		return super.xrange(key, start, end, count);
	}

	@Override
	public List<byte[]> xrevrange(byte[] key, byte[] end, byte[] start, int count) {
		return super.xrevrange(key, end, start, count);
	}

	@Override
	public List<byte[]> xread(int count, long block, Map<byte[], byte[]> streams) {
		return super.xread(count, block, streams);
	}

	@Override
	public Long xack(byte[] key, byte[] group, byte[]... ids) {
		return super.xack(key, group, ids);
	}

	@Override
	public String xgroupCreate(byte[] key, byte[] consumer, byte[] id, boolean makeStream) {
		return super.xgroupCreate(key, consumer, id, makeStream);
	}

	@Override
	public String xgroupSetID(byte[] key, byte[] consumer, byte[] id) {
		return super.xgroupSetID(key, consumer, id);
	}

	@Override
	public Long xgroupDestroy(byte[] key, byte[] consumer) {
		return super.xgroupDestroy(key, consumer);
	}

	@Override
	public Long xgroupDelConsumer(byte[] key, byte[] consumer, byte[] consumerName) {
		return super.xgroupDelConsumer(key, consumer, consumerName);
	}

	@Override
	public List<byte[]> xreadGroup(byte[] groupname, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
		return super.xreadGroup(groupname, consumer, count, block, noAck, streams);
	}

	@Override
	public Long xdel(byte[] key, byte[]... ids) {
		return super.xdel(key, ids);
	}

	@Override
	public Long xtrim(byte[] key, long maxLen, boolean approximateLength) {
		return super.xtrim(key, maxLen, approximateLength);
	}

	@Override
	public List<byte[]> xpending(byte[] key, byte[] groupname, byte[] start, byte[] end, int count, byte[] consumername) {
		return super.xpending(key, groupname, start, end, count, consumername);
	}

	@Override
	public List<byte[]> xclaim(byte[] key, byte[] groupname, byte[] consumername, long minIdleTime, long newIdleTime, int retries, boolean force, byte[][] ids) {
		return super.xclaim(key, groupname, consumername, minIdleTime, newIdleTime, retries, force, ids);
	}

	@Override
	public Long waitReplicas(byte[] key, int replicas, long timeout) {
		return super.waitReplicas(key, replicas, timeout);
	}

	@Override
	public Object sendCommand(byte[] sampleKey, ProtocolCommand cmd, byte[]... args) {
		return super.sendCommand(sampleKey, cmd, args);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
