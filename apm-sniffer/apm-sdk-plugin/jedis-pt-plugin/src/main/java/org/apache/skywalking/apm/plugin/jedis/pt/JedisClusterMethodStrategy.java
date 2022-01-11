package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.skywalking.apm.plugin.jedis.pt.common.JedisTool;
import org.apache.skywalking.apm.plugin.jedis.pt.common.RedisToolManager;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisClusterMethodStrategy implements MethodStrategy {

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
	SET_3("set", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SET_4("set", new Class[]{byte[].class, byte[].class, SetParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET_1("get", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET_2("get", new Class[]{byte[].class}) {
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
	EXISTS_3("exists", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	EXISTS_4("exists", new Class[]{byte[].class}) {
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
	DEL_3("del", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	DEL_4("del", new Class[]{byte[].class}) {
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
	UNLINK_3("unlink", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	UNLINK_4("unlink", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TYPE_1("type", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TYPE_2("type", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RENAME_1("rename", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	RENAME_2("rename", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	RENAME_NX_1("renamenx", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	RENAME_NX_2("renamenx", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	EXPIRE_1("expire", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	EXPIRE_2("expire", new Class[]{byte[].class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	EXPIRE_AT_1("expireAt", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	EXPIRE_AT_2("expireAt", new Class[]{byte[].class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TTL_1("ttl", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	TTL_2("ttl", new Class[]{byte[].class}) {
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
	TOUCH_3("touch", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	TOUCH_4("touch", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MOVE_1("move", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MOVE_2("move", new Class[]{byte[].class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET_SET_1("getSet", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GET_SET_2("getSet", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MGET_1("mget", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	MGET_2("mget", new Class[]{byte[][].class}) {
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
	SET_NX_2("setnx", new Class[]{String.class, int.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SET_NX_3("setnx", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SET_NX_4("setnx", new Class[]{byte[].class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MSET_1("mset", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	MSET_2("mset", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	MSET_NX_1("msetnx", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	MSET_NX_2("msetnx", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				if (i % 2 == 0) {
					allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
				}
			}
		}
	},
	DECR_BY_1("decrBy", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DECR_BY_2("decrBy", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DECR_1("decr", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DECR_2("decr", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY_1("incrBy", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY_2("incrBy", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY_FLOAT_1("incrByFloat", new Class[]{String.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_BY_FLOAT_2("incrByFloat", new Class[]{byte[].class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_1("incr", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	INCR_2("incr", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	APPEND_1("append", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	APPEND_2("append", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SUBSTR_1("substr", new Class[]{byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SUBSTR_2("substr", new Class[]{byte[].class, int.class, int.class}) {
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
	HSET_3("hset", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_4("hset", new Class[]{byte[].class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGET_1("hget", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGET_2("hget", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_NX_1("hsetnx", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSET_NX_2("hsetnx", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMSET_1("hmset", new Class[]{String.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMSET_2("hmset", new Class[]{byte[].class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMGET_1("hmget", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HMGET_2("hmget", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY_1("hincrBy", new Class[]{String.class, String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY_2("hincrBy", new Class[]{byte[].class, byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY_FLOAT_1("hincrByFloat", new Class[]{String.class, String.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HINCR_BY_FLOAT_2("hincrByFloat", new Class[]{byte[].class, byte[].class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HEXISTS_1("hexists", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HEXISTS_2("hexists", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HDEL_1("hdel", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HDEL_2("hdel", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HLEN_1("hlen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HLEN_2("hlen", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HKEYS_1("hkeys", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HKEYS_2("hkeys", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HVALS_1("hvals", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HVALS_2("hvals", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGETALL_1("hgetAll", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HGETALL_2("hgetAll", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSH_1("rpush", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSH_2("rpush", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSH_1("lpush", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSH_2("lpush", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LLEN_1("llen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LLEN_2("llen", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LRANGE_1("lrange", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LRANGE_2("lrange", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LTRIM_1("ltrim", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LTRIM_2("ltrim", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINDEX_1("lindex", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINDEX_2("lindex", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LSET_1("lset", new Class[]{String.class, long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LSET_2("lset", new Class[]{byte[].class, long.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LREM_1("lrem", new Class[]{String.class, long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LREM_2("lrem", new Class[]{byte[].class, long.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPOP_1("lpop", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPOP_2("lpop", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPOP_1("rpop", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPOP_2("rpop", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPOPLPUSH_1("rpoplpush", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	RPOPLPUSH_2("rpoplpush", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SADD_1("sadd", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SADD_2("sadd", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SMEMBERS_1("smembers", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SMEMBERS_2("smembers", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SREM_1("srem", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SREM_2("srem", new Class[]{byte[].class, byte[][].class}) {
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
	SPOP_2("spop", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SPOP_3("spop", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SPOP_4("spop", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SMOVE_1("smove", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	SMOVE_2("smove", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	SCARD_1("scard", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SCARD_2("scard", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SISMEMBER_1("sismember", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SISMEMBER_2("sismember", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SINTER_1("sinter", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SINTER_2("sinter", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SINTERSTORE_1("sinterstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SINTERSTORE_2("sinterstore", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SUNION_1("sunion", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SUNION_2("sunion", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SUNIONSTORE_1("sunionstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SUNIONSTORE_2("sunionstore", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SDIFF_1("sdiff", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SDIFF_2("sdiff", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SDIFFSTORE_1("sdiffstore", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SDIFFSTORE_2("sdiffstore", new Class[]{byte[].class, byte[][].class}) {
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
	SRANDMEMBER_2("srandmember", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SRANDMEMBER_3("srandmember", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SRANDMEMBER_4("srandmember", new Class[]{byte[].class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_1("zadd", new Class[]{String.class, double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_2("zadd", new Class[]{String.class, double.class, String.class, ZAddParams.class}) {
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
	ZADD_5("zadd", new Class[]{byte[].class, double.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_6("zadd", new Class[]{byte[].class, double.class, byte[].class, ZAddParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_7("zadd", new Class[]{byte[].class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZADD_8("zadd", new Class[]{byte[].class, Map.class, ZAddParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_1("zrange", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_2("zrange", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREM_1("zrem", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREM_2("zrem", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_1("zincrby", new Class[]{String.class, double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_2("zincrby", new Class[]{String.class, double.class, String.class, ZIncrByParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_3("zincrby", new Class[]{byte[].class, double.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZINCR_BY_4("zincrby", new Class[]{byte[].class, double.class, byte[].class, ZIncrByParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANK_1("zrank", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANK_2("zrank", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANK_1("zrevrank", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANK_2("zrevrank", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_1("zrevrange", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_2("zrevrange", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_WITH_SCORES_1("zrangeWithScores", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_WITH_SCORES_2("zrangeWithScores", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_WITH_SCORES_1("zrevrangeWithScores", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_WITH_SCORES_2("zrevrangeWithScores", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZCARD_1("zcard", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZCARD_2("zcard", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCORE_1("zscore", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCORE_2("zscore", new Class[]{byte[].class, byte[].class}) {
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
	ZPOPMAX_2("zpopmax", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMAX_3("zpopmax", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMAX_4("zpopmax", new Class[]{byte[].class, int.class}) {
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
	ZPOPMIN_2("zpopmin", new Class[]{String.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMIN_3("zpopmin", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZPOPMIN_4("zpopmin", new Class[]{byte[].class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	WATCH_1("watch", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	WATCH_2("watch", new Class[]{byte[][].class}) {
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
	SORT_3("sort", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SORT_4("sort", new Class[]{byte[].class, SortingParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BLPOP_1("blpop", new Class[]{int.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	BLPOP_3("blpop", new Class[]{int.class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	SORT_5("sort", new Class[]{String.class, SortingParams.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[2] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[2]);
		}
	},
	SORT_6("sort", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	SORT_7("sort", new Class[]{byte[].class, SortingParams.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			allArguments[2] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[2]);
		}
	},
	SORT_8("sort", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	BRPOP_1("brpop", new Class[]{int.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	BRPOP_3("brpop", new Class[]{int.class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			Object[] secondParam = (Object[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	ZCOUNT_1("zcount", new Class[]{String.class, double.class, double.class}) {
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
	ZCOUNT_3("zcount", new Class[]{byte[].class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZCOUNT_4("zcount", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_1("zrangeByScore", new Class[]{String.class, double.class, double.class}) {
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
	ZRANGE_BY_SCORE_3("zrangeByScore", new Class[]{String.class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_4("zrangeByScore", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_5("zrangeByScore", new Class[]{byte[].class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_6("zrangeByScore", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_7("zrangeByScore", new Class[]{byte[].class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_8("zrangeByScore", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_1("zrangeByScoreWithScores", new Class[]{String.class, double.class, double.class}) {
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
	ZRANGE_BY_SCORE_WITH_SCORES_3("zrangeByScoreWithScores", new Class[]{String.class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_4("zrangeByScoreWithScores", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_5("zrangeByScoreWithScores", new Class[]{byte[].class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_6("zrangeByScoreWithScores", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_7("zrangeByScoreWithScores", new Class[]{byte[].class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_SCORE_WITH_SCORES_8("zrangeByScoreWithScores", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_1("zrevrangeByScore", new Class[]{String.class, double.class, double.class}) {
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
	ZREVRANGE_BY_SCORE_3("zrevrangeByScore", new Class[]{String.class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_4("zrevrangeByScore", new Class[]{byte[].class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_5("zrevrangeByScore", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_6("zrevrangeByScore", new Class[]{byte[].class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_1("zrevrangeByScoreWithScores", new Class[]{String.class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_2("zrevrangeByScoreWithScores", new Class[]{String.class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_3("zrevrangeByScoreWithScores", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
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
	ZREVRANGE_BY_SCORE_WITH_SCORES_5("zrevrangeByScoreWithScores", new Class[]{byte[].class, double.class, double.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_6("zrevrangeByScoreWithScores", new Class[]{byte[].class, double.class, double.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_7("zrevrangeByScoreWithScores", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_WITH_SCORES_8("zrevrangeByScoreWithScores", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_7("zrevrangeByScore", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_SCORE_8("zrevrangeByScore", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_RANK_1("zremrangeByRank", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_RANK_2("zremrangeByRank", new Class[]{byte[].class, long.class, long.class}) {
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
	ZREMRANGE_BY_SCORE_2("zremrangeByScore", new Class[]{String.class, double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_SCORE_3("zremrangeByScore", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_SCORE_4("zremrangeByScore", new Class[]{byte[].class, double.class, byte[].class}) {
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
	ZUNIONSTORE_3("zunionstore", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZUNIONSTORE_4("zunionstore", new Class[]{byte[].class, ZParams.class, byte[].class}) {
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
	zinterstore_3("zinterstore", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	zinterstore_4("zinterstore", new Class[]{byte[].class, ZParams.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZLEXCOUNT_1("zlexcount", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZLEXCOUNT_2("zlexcount", new Class[]{byte[].class, byte[].class, byte[].class}) {
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
	ZRANGE_BY_LEX_2("zrangeByLex", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_LEX_3("zrangeByLex", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZRANGE_BY_LEX_4("zrangeByLex", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
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
	ZREVRANGE_BY_LEX_2("zrevrangeByLex", new Class[]{String.class, String.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_LEX_3("zrevrangeByLex", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREVRANGE_BY_LEX_4("zrevrangeByLex", new Class[]{byte[].class, byte[].class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_LEX_1("zremrangeByLex", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZREMRANGE_BY_LEX_2("zremrangeByLex", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	STRLEN_1("strlen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	STRLEN_2("strlen", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSHX_1("lpushx", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LPUSHX_2("lpushx", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PERSIST_1("persist", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PERSIST_2("persist", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSHX_1("rpushx", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RPUSHX_2("rpushx", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINSERT_1("linsert", new Class[]{String.class, ListPosition.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	LINSERT_2("linsert", new Class[]{byte[].class, ListPosition.class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_1("setbit", new Class[]{String.class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_2("setbit", new Class[]{String.class, long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_3("setbit", new Class[]{byte[].class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETBIT_4("setbit", new Class[]{byte[].class, long.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETBIT_1("getbit", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETBIT_2("getbit", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETRANGE_1("setrange", new Class[]{String.class, long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SETRANGE_2("setrange", new Class[]{byte[].class, long.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETRANGE_1("getrange", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GETRANGE_2("getrange", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_1("bitpos", new Class[]{String.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_2("bitpos", new Class[]{String.class, boolean.class, BitPosParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_3("bitpos", new Class[]{byte[].class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITPOS_4("bitpos", new Class[]{byte[].class, boolean.class, BitPosParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SUBSCRIBE_1("subscribe", new Class[]{JedisPubSub.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			String[] keys = (String[]) allArguments[1];
			for (int i = 0; i < keys.length; i++) {
				keys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(keys[i]);
			}
		}
	},
	SUBSCRIBE_2("subscribe", new Class[]{JedisPubSub.class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			String[] keys = (String[]) allArguments[1];
			for (int i = 0; i < keys.length; i++) {
				keys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(keys[i]);
			}
		}
	},
	EVAL_1("eval", new Class[]{String.class, List.class, List.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			List<String> keys = (List<String>) allArguments[1];
			List<String> newKeys = new ArrayList<>();
			for (int i = 0; i < keys.size(); i++) {
				newKeys.add(RedisToolManager.JEDIS_TOOL.convertShadowKey(keys.get(i)));
			}
			allArguments[1] = newKeys;
		}
	},
	EVAL_2("eval", new Class[]{byte[].class, List.class, List.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			List<byte[]> keys = (List<byte[]>) allArguments[1];
			List<byte[]> newKeys = new ArrayList<>();
			for (int i = 0; i < keys.size(); i++) {
				newKeys.add(RedisToolManager.JEDIS_TOOL.convertShadowKey(keys.get(i)));
			}
			allArguments[1] = newKeys;
		}
	},
	EVALSHA_1("evalsha", new Class[]{String.class, List.class, List.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			List<String> keys = (List<String>) allArguments[1];
			List<String> newKeys = new ArrayList<>();
			for (int i = 0; i < keys.size(); i++) {
				newKeys.add(RedisToolManager.JEDIS_TOOL.convertShadowKey(keys.get(i)));
			}
			allArguments[1] = newKeys;
		}
	},
	EVALSHA_2("evalsha", new Class[]{String.class, List.class, List.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			List<byte[]> keys = (List<byte[]>) allArguments[1];
			List<byte[]> newKeys = new ArrayList<>();
			for (int i = 0; i < keys.size(); i++) {
				newKeys.add(RedisToolManager.JEDIS_TOOL.convertShadowKey(keys.get(i)));
			}
			allArguments[1] = newKeys;
		}
	},
	OBJECT_REFCOUNT_1("objectRefcount", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_REFCOUNT_2("objectRefcount", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_ENCODING_1("objectEncoding", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_ENCODING_2("objectEncoding", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_IDLE_TIME_1("objectIdletime", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_IDLE_TIME_2("objectIdletime", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_FREQ_1("objectFreq", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	OBJECT_FREQ_2("objectFreq", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BIT_COUNT_1("bitcount", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BIT_COUNT_2("bitcount", new Class[]{String.class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BIT_COUNT_3("bitcount", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BIT_COUNT_4("bitcount", new Class[]{byte[].class, long.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITOP_1("bitop", new Class[]{BitOP.class, String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
			String[] srcKeys = (String[]) allArguments[2];
			for (int i = 0; i < srcKeys.length; i++) {
				srcKeys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(srcKeys[i]);
			}
		}
	},
	BITOP_2("bitop", new Class[]{BitOP.class, byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
			String[] srcKeys = (String[]) allArguments[2];
			for (int i = 0; i < srcKeys.length; i++) {
				srcKeys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(srcKeys[i]);
			}
		}
	},
	DUMP_1("dump", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	DUMP_2("dump", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RESTORE_1("restore", new Class[]{String.class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RESTORE_2("restore", new Class[]{byte[].class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RESTORE_REPLACE_1("restoreReplace", new Class[]{String.class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	RESTORE_REPLACE_2("restoreReplace", new Class[]{byte[].class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PEXPIRE_1("pexpire", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PEXPIRE_2("pexpire", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PEXPIREAT_1("pexpireAt", new Class[]{String.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PEXPIREAT_2("pexpireAt", new Class[]{byte[].class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PTTL_1("pttl", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PTTL_2("pttl", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PSETEX_1("psetex", new Class[]{String.class, long.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PSETEX_2("psetex", new Class[]{byte[].class, long.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	MIGRATE_1("migrate", new Class[]{String.class, int.class, String.class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[2] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[2]);
		}
	},
	MIGRATE_2("migrate", new Class[]{String.class, int.class, int.class, int.class, MigrateParams.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			String[] keys = (String[]) allArguments[5];
			for (int i = 0; i < keys.length; i++) {
				keys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(keys[i]);
			}
		}
	},
	MIGRATE_3("migrate", new Class[]{String.class, int.class, byte[].class, int.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[2] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[2]);
		}
	},
	MIGRATE_4("migrate", new Class[]{String.class, int.class, int.class, int.class, MigrateParams.class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			String[] keys = (String[]) allArguments[5];
			for (int i = 0; i < keys.length; i++) {
				keys[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(keys[i]);
			}
		}
	},
	HSCAN_1("hscan", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSCAN_2("hscan", new Class[]{String.class, String.class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSCAN_3("hscan", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSCAN_4("hscan", new Class[]{byte[].class, byte[].class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SSCAN_1("sscan", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SSCAN_2("sscan", new Class[]{String.class, String.class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SSCAN_3("sscan", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	SSCAN_4("sscan", new Class[]{byte[].class, byte[].class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCAN_1("zscan", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCAN_2("zscan", new Class[]{String.class, String.class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCAN_3("zscan", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	ZSCAN_4("zscan", new Class[]{byte[].class, byte[].class, ScanParams.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	CLUSTER_KEY_SLOT("clusterKeySlot", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PUBSUB_CHANNELS("pubsubChannels", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PUBSUB_NUM_SUB("pubsubNumSub", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	PFADD_1("pfadd", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PFADD_2("pfadd", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PFCOUNT_1("pfcount", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PFCOUNT_2("pfcount", new Class[]{String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	PFCOUNT_3("pfcount", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	PFCOUNT_4("pfcount", new Class[]{byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			for (int i = 0; i < allArguments.length; i++) {
				allArguments[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[i]);
			}
		}
	},
	PFMERGE_1("pfmerge", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			String[] secondParam = (String[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	PFMERGE_2("pfmerge", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
			String[] secondParam = (String[]) allArguments[1];
			for (int i = 0; i < secondParam.length; i++) {
				secondParam[i] = RedisToolManager.JEDIS_TOOL.convertShadowKey(secondParam[i]);
			}
		}
	},
	BLPOP_2("blpop", new Class[]{int.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	BLPOP_4("blpop", new Class[]{int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	BRPOP_2("brpop", new Class[]{int.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	BRPOP_4("brpop", new Class[]{int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[1] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[1]);
		}
	},
	GEOADD_1("geoadd", new Class[]{String.class, double.class, double.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOADD_2("geoadd", new Class[]{String.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOADD_3("geoadd", new Class[]{byte[].class, double.class, double.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOADD_4("geoadd", new Class[]{byte[].class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEODIST_1("geodist", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEODIST_2("geodist", new Class[]{String.class, String.class, String.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEODIST_3("geodist", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEODIST_4("geodist", new Class[]{byte[].class, byte[].class, byte[].class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOHASH_1("geohash", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOHASH_2("geohash", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOPOS_1("geopos", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEOPOS_2("geopos", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_1("georadius", new Class[]{String.class, double.class, double.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_2("georadius", new Class[]{String.class, double.class, double.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_3("georadius", new Class[]{byte[].class, double.class, double.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_4("georadius", new Class[]{byte[].class, double.class, double.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_READONLY_1("georadiusReadonly", new Class[]{String.class, double.class, double.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_READONLY_2("georadiusReadonly", new Class[]{String.class, double.class, double.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_READONLY_3("georadiusReadonly", new Class[]{byte[].class, double.class, double.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_READONLY_4("georadiusReadonly", new Class[]{byte[].class, double.class, double.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_1("georadiusByMember", new Class[]{String.class, String.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_2("georadiusByMember", new Class[]{byte[].class, byte[].class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_READONLY_1("georadiusByMemberReadonly", new Class[]{String.class, String.class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_READONLY_2("georadiusByMemberReadonly", new Class[]{String.class, String.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_READONLY_3("georadiusByMemberReadonly", new Class[]{byte[].class, byte[].class, double.class, GeoUnit.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUS_BY_MEMBER_READONLY_4("georadiusByMemberReadonly", new Class[]{byte[].class, byte[].class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUSBYMEMBER_1("georadiusByMember", new Class[]{String.class, String.class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	GEORADIUSBYMEMBER_2("georadiusByMember", new Class[]{byte[].class, byte[].class, double.class, GeoUnit.class, GeoRadiusParam.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITFIELD_1("bitfield", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITFIELD_2("bitfield", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITFIELD_READONLY_1("bitfieldReadonly", new Class[]{String.class, String[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	BITFIELD_READONLY_2("bitfieldReadonly", new Class[]{byte[].class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSTRLEN_1("hstrlen", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	HSTRLEN_2("hstrlen", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XADD_1("xadd", new Class[]{String.class, StreamEntryID.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XADD_2("xadd", new Class[]{String.class, StreamEntryID.class, Map.class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XADD_3("xadd", new Class[]{byte[].class, StreamEntryID.class, Map.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XADD_4("xadd", new Class[]{byte[].class, StreamEntryID.class, Map.class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XLEN_1("xlen", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XLEN_2("xlen", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XRANGE_1("xrange", new Class[]{String.class, StreamEntryID.class, StreamEntryID.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XRANGE_2("xrange", new Class[]{byte[].class, StreamEntryID.class, StreamEntryID.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XREVRANGE_1("xrevrange", new Class[]{String.class, StreamEntryID.class, StreamEntryID.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XREVRANGE_2("xrevrange", new Class[]{byte[].class, StreamEntryID.class, StreamEntryID.class, int.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XACK_1("xack", new Class[]{String.class, String.class, StreamEntryID[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XACK_2("xack", new Class[]{byte[].class, byte[].class, StreamEntryID[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_CREATE_1("xgroupCreate", new Class[]{String.class, String.class, StreamEntryID.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_CREATE_2("xgroupCreate", new Class[]{byte[].class, byte[].class, StreamEntryID.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_SET_ID_1("xgroupSetID", new Class[]{String.class, String.class, StreamEntryID.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_SET_ID_2("xgroupSetID", new Class[]{byte[].class, byte[].class, StreamEntryID.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_DESTROY_1("xgroupDestroy", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_DESTROY_2("xgroupDestroy", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_DEL_CONSUMER_1("xgroupDelConsumer", new Class[]{String.class, String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XGROUP_DEL_CONSUMER_2("xgroupDelConsumer", new Class[]{byte[].class, byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XDEL_1("xdel", new Class[]{String.class, StreamEntryID[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XDEL_2("xdel", new Class[]{byte[].class, StreamEntryID[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XTRIM_1("xtrim", new Class[]{String.class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XTRIM_2("xtrim", new Class[]{byte[].class, long.class, boolean.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XPENDING_1("xpending", new Class[]{String.class, String.class, StreamEntryID.class, StreamEntryID.class, int.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XPENDING_2("xpending", new Class[]{byte[].class, byte[].class, StreamEntryID.class, StreamEntryID.class, int.class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XCLAIM_1("xclaim", new Class[]{String.class, String.class, String.class, long.class, long.class, int.class, boolean.class, StreamEntryID[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XCLAIM_2("xclaim", new Class[]{byte[].class, byte[].class, byte[].class, long.class, long.class, int.class, boolean.class, byte[][].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_STREAM_1("xinfoStream", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_STREAM_2("xinfoStream", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_GROUP_1("xinfoGroup", new Class[]{String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_GROUP_2("xinfoGroup", new Class[]{byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_CONSUMERS_1("xinfoConsumers", new Class[]{String.class, String.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	XINFO_CONSUMERS_2("xinfoConsumers", new Class[]{byte[].class, byte[].class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	WAIT_REPLICAS_1("waitReplicas", new Class[]{String.class, int.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	WAIT_REPLICAS_2("waitReplicas", new Class[]{byte[].class, int.class, long.class}) {
		@Override
		public void doArguments(Object[] allArguments) {
			allArguments[0] = RedisToolManager.JEDIS_TOOL.convertShadowKey(allArguments[0]);
		}
	},
	;

	private final String methodName;
	private final Class<?>[] argumentsTypes;

	JedisClusterMethodStrategy(String methodName, Class<?>[] argumentsTypes) {
		this.methodName = methodName;
		this.argumentsTypes = argumentsTypes;
	}

	public abstract void doArguments(Object[] allArguments);

	public static JedisClusterMethodStrategy getStrategy(String methodName, Class<?>[] argumentsTypes) {
		return (JedisClusterMethodStrategy) JedisTool.findStrategy(values(), methodName, argumentsTypes);
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
