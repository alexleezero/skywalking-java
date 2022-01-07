package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.skywalking.apm.plugin.jedis.pt.common.JedisTool;
import org.apache.skywalking.apm.plugin.jedis.pt.common.RedisToolManager;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import java.util.Map;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisMethodStrategy implements MethodStrategy {

	SET_1("set", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SET_2("set", new Class[]{String.class, String.class, SetParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET("get", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	EXISTS_1("exists", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	EXISTS_2("exists", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DEL_1("del", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	DEL_2("del", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	UNLINK_1("unlink", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	UNLINK_2("unlink", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TYPE("type", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RENAME("rename", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	RENAME_NX("renamenx", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	EXPIRE("expire", new Class[]{String.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	EXPIRE_AT("expireAt", new Class[]{String.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TTL("ttl", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TOUCH_1("touch", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	TOUCH_2("touch", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MOVE("move", new Class[]{String.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET_SET("getSet", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MGET("mget", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SET_NX_1("setnx", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SET_NX_2("setnx", new Class[]{String.class, Integer.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MSET("mset", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	MSET_NX("msetnx", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	DECR_BY("decrBy", new Class[]{String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DECR("decr", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY("incrBy", new Class[]{String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY_FLOAT("incrByFloat", new Class[]{String.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR("incr", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	APPEND("append", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SUBSTR("substr", new Class[]{String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_1("hset", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_2("hset", new Class[]{String.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGET("hget", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_NX("hsetnx", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMSET("hmset", new Class[]{String.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMGET("hmget", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY("hincrBy", new Class[]{String.class, String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY_FLOAT("hincrByFloat", new Class[]{String.class, String.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HEXISTS("hexists", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HDEL("hdel", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HLEN("hlen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HKEYS("hkeys", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HVALS("hvals", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGETALL("hgetAll", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSH("rpush", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSH("lpush", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LLEN("llen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LRANGE("lrange", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LTRIM("ltrim", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINDEX("lindex", new Class[]{String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LSET("lset", new Class[]{String.class, Long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LREM("lrem", new Class[]{String.class, Long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPOP("lpop", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPOP("rpop", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	rpoplpush("rpoplpush", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SADD("sadd", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SMEMBERS("smembers", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SREM("srem", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SPOP_1("spop", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SPOP_2("spop", new Class[]{String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SMOVE("smove", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	SCARD("scard", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SISMEMBER("sismember", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SINTER("sinter", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SINTERSTORE("sinterstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SUNION("sunion", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SUNIONSTORE("sunionstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SDIFF("sdiff", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SDIFFSTORE("sdiffstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SRANDMEMBER_1("srandmember", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SRANDMEMBER_2("srandmember", new Class[]{String.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_1("zadd", new Class[]{String.class, Double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_2("zadd", new Class[]{String.class, Double.class, String.class, ZAddParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_3("zadd", new Class[]{String.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_4("zadd", new Class[]{String.class, Map.class, ZAddParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE("zrange", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREM("zrem", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_1("zincrby", new Class[]{String.class, Double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_2("zincrby", new Class[]{String.class, Double.class, String.class, ZIncrByParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANK("zrank", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANK("zrevrank", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE("zrevrange", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_WITH_SCORES("zrangeWithScores", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_WITH_SCORES("zrevrangeWithScores", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZCARD("zcard", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCORE("zscore", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMAX_1("zpopmax", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMIN_1("zpopmin", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMIN_2("zpopmin", new Class[]{String.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	WATCH("watch", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SORT_1("sort", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SORT_2("sort", new Class[]{String.class, SortingParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BLPOP("blpop", new Class[]{Integer.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SORT_3("sort", new Class[]{String.class, SortingParams.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[2] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[2]);
		}
	},
	SORT_4("sort", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	BRPOP("brpop", new Class[]{Integer.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	ZCOUNT_1("zcount", new Class[]{String.class, Double.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZCOUNT_2("zcount", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_1("zrangeByScore", new Class[]{String.class, Double.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_2("zrangeByScore", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_3("zrangeByScore", new Class[]{String.class, Double.class, Double.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_4("zrangeByScore", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_1("zrangeByScoreWithScores", new Class[]{String.class, Double.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_2("zrangeByScoreWithScores", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_3("zrangeByScoreWithScores", new Class[]{String.class, Double.class, Double.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_4("zrangeByScoreWithScores", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_1("zrevrangeByScore", new Class[]{String.class, Double.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_2("zrevrangeByScore", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_3("zrevrangeByScore", new Class[]{String.class, Double.class, Double.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_1("zrevrangeByScoreWithScores", new Class[]{String.class, Double.class, Double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_2("zrevrangeByScoreWithScores", new Class[]{String.class, Double.class, Double.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_3("zrevrangeByScoreWithScores", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_4("zrevrangeByScoreWithScores", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE("zrevrangeByScore", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_RANK("zremrangeByRank", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_SCORE_1("zremrangeByScore", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_SCORE_2("zremrangeByScore", new Class[]{String.class, Double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZUNIONSTORE_1("zunionstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZUNIONSTORE_2("zunionstore", new Class[]{String.class, ZParams.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	zinterstore_1("zinterstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	zinterstore_2("zinterstore", new Class[]{String.class, ZParams.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZLEXCOUNT("zlexcount", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_LEX_1("zrangeByLex", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_LEX_2("zrangeByLex", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_LEX_1("zrevrangeByLex", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_LEX_2("zrevrangeByLex", new Class[]{String.class, String.class, String.class, Integer.class, Integer.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_LEX("zremrangeByLex", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	STRLEN("strlen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSHX("lpushx", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PERSIST("persist", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSHX("rpushx", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINSERT("linsert", new Class[]{String.class, ListPosition.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_1("setbit", new Class[]{String.class, Long.class, Boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_2("setbit", new Class[]{String.class, Long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETBIT("getbit", new Class[]{String.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETRANGE("setrange", new Class[]{String.class, Long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETRANGE("getrange", new Class[]{String.class, Long.class, Long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_1("bitpos", new Class[]{String.class, Boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_2("bitpos", new Class[]{String.class, Boolean.class, BitPosParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	;

	private final String methodName;
	private final Class<?>[] argumentsTypes;

	JedisMethodStrategy(String methodName, Class<?>[] argumentsTypes) {
		this.methodName = methodName;
		this.argumentsTypes = argumentsTypes;
	}

	public abstract void doArguments(Object[] allArguments);

	public static JedisMethodStrategy getStrategy(String methodName, Class<?>[] argumentsTypes) {
		return (JedisMethodStrategy) JedisTool.findStrategy(values(), methodName, argumentsTypes);
	}



	@Override
	public String toString() {
		return JedisTool.strategy2String(methodName, argumentsTypes);
	}

	@Override
	public String getMethodName() {
		return this.methodName;
	}

	@Override
	public Class<?>[] getArgumentsTypes() {
		return this.argumentsTypes;
	}

}
