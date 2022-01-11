package org.apache.skywalking.apm.plugin.jedis.pt;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisMethodMatcher {
	INSTANCE;


	public ElementMatcher.Junction<MethodDescription> getJedisMethodMatcher() {
		return methods();
	}

	public ElementMatcher.Junction<MethodDescription> getJedisClusterMethodMatcher() {
		return methods();
	}

	private ElementMatcher.Junction<MethodDescription> methods() {
		return named("set").or(named("get")).or(named("exists")).or(named("persist")).or(named("type"))
				.or(named("dump")).or(named("restore")).or(named("expire")).or(named("pexpire"))
				.or(named("expireAt")).or(named("pexpireAt")).or(named("ttl")).or(named("pttl"))
				.or(named("touch")).or(named("setbit")).or(named("getbit")).or(named("setrange"))
				.or(named("getrange")).or(named("getSet")).or(named("setnx")).or(named("setex"))
				.or(named("psetex")).or(named("decrBy")).or(named("decr")).or(named("incrBy"))
				.or(named("incrByFloat")).or(named("incr")).or(named("append")).or(named("substr"))
				.or(named("hset")).or(named("hget")).or(named("hsetnx")).or(named("hmset"))
				.or(named("hmget")).or(named("hincrBy")).or(named("hexists")).or(named("hdel"))
				.or(named("hlen")).or(named("hkeys")).or(named("hvals")).or(named("hgetAll"))
				.or(named("rpush")).or(named("lpush")).or(named("llen")).or(named("lrange"))
				.or(named("ltrim")).or(named("lindex")).or(named("lset")).or(named("lrem"))
				.or(named("lpop")).or(named("rpop")).or(named("sadd")).or(named("smembers"))
				.or(named("srem")).or(named("spop")).or(named("scard")).or(named("sismember"))
				.or(named("srandmember")).or(named("strlen")).or(named("zadd")).or(named("zrange"))
				.or(named("zrem")).or(named("zincrby")).or(named("zrank")).or(named("zrevrank"))
				.or(named("zrevrange")).or(named("zrangeWithScores")).or(named("zrevrangeWithScores")).or(named("zcard"))
				.or(named("zscore")).or(named("zpopmax")).or(named("zpopmin")).or(named("sort"))
				.or(named("zcount")).or(named("zrangeByScore")).or(named("zrevrangeByScore")).or(named("zrangeByScore"))
				.or(named("zrevrangeByScore")).or(named("zrevrangeByScoreWithScores")).or(named("zrangeByScoreWithScores")).or(named("zremrangeByRank"))
				.or(named("zremrangeByScore")).or(named("zlexcount")).or(named("zrangeByLex")).or(named("zrevrangeByLex"))
				.or(named("zremrangeByLex")).or(named("linsert")).or(named("lpushx")).or(named("rpushx"))
				.or(named("del")).or(named("unlink")).or(named("bitcount")).or(named("hscan"))
				.or(named("sscan")).or(named("zscan")).or(named("pfadd")).or(named("pfcount"))
				.or(named("blpop")).or(named("brpop")).or(named("mget")).or(named("mset"))
				.or(named("msetnx")).or(named("rename")).or(named("renamenx")).or(named("rpoplpush"))
				.or(named("sdiff")).or(named("sdiffstore")).or(named("sinter")).or(named("sinterstore"))
				.or(named("smove")).or(named("sunion")).or(named("sunionstore")).or(named("zinterstore"))
				.or(named("zunionstore")).or(named("publish")).or(named("subscribe")).or(named("psubscribe"))
				.or(named("bitop")).or(named("pfmerge")).or(named("pfcount")).or(named("eval"))
				.or(named("evalsha")).or(named("geoadd")).or(named("geodist")).or(named("geohash"))
				.or(named("geopos")).or(named("georadius")).or(named("georadiusReadonly")).or(named("georadiusByMember"))
				.or(named("georadiusByMemberReadonly")).or(named("georadiusByMember")).or(named("bitfield")).or(named("bitfieldReadonly"))
				.or(named("hstrlen")).or(named("xadd")).or(named("xlen")).or(named("xrange"))
				.or(named("xrevrange")).or(named("xack")).or(named("xgroupCreate")).or(named("xgroupSetID"))
				.or(named("xgroupDestroy")).or(named("xgroupDelConsumer")).or(named("xpending")).or(named("xdel"))
				.or(named("xtrim")).or(named("xclaim")).or(named("waitReplicas"));
	}
}
