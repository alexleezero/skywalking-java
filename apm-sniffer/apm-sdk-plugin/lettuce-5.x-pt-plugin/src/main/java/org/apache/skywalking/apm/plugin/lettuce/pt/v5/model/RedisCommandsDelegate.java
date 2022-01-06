package org.apache.skywalking.apm.plugin.lettuce.pt.v5.model;

import io.lettuce.core.BitFieldArgs;
import io.lettuce.core.Consumer;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoRadiusStoreArgs;
import io.lettuce.core.GeoWithin;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.KeyValue;
import io.lettuce.core.KillArgs;
import io.lettuce.core.Limit;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.MigrateArgs;
import io.lettuce.core.Range;
import io.lettuce.core.RestoreArgs;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.ScoredValueScanCursor;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.SetArgs;
import io.lettuce.core.SortArgs;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.StreamScanCursor;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.UnblockType;
import io.lettuce.core.Value;
import io.lettuce.core.ValueScanCursor;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XGroupCreateArgs;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.ZAddArgs;
import io.lettuce.core.ZStoreArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.output.KeyStreamingChannel;
import io.lettuce.core.output.KeyValueStreamingChannel;
import io.lettuce.core.output.ScoredValueStreamingChannel;
import io.lettuce.core.output.ValueStreamingChannel;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.RedisToolManager;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisCommandsDelegate<K, V> implements RedisCommands<K, V> {

    private final RedisCommands<K, V> origin;

    public RedisCommandsDelegate(RedisCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public String auth(String password) {
        return origin.auth(password);
    }

    @Override
    public String select(int db) {
        return origin.select(db);
    }

    @Override
    public String swapdb(int db1, int db2) {
        return origin.swapdb(db1, db2);
    }

    @Override
    public StatefulRedisConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }

    @Override
    public Long publish(K channel, V message) {
        return origin.publish(channel, message);
    }

    @Override
    public List<K> pubsubChannels() {
        return origin.pubsubChannels();
    }

    @Override
    public List<K> pubsubChannels(K channel) {
        return origin.pubsubChannels(channel);
    }

    @Override
    public Map<K, Long> pubsubNumsub(K... channels) {
        return origin.pubsubNumsub(channels);
    }

    @Override
    public Long pubsubNumpat() {
        return origin.pubsubNumpat();
    }

    @Override
    public V echo(V msg) {
        return origin.echo(msg);
    }

    @Override
    public List<Object> role() {
        return origin.role();
    }

    @Override
    public String ping() {
        return origin.ping();
    }

    @Override
    public String readOnly() {
        return origin.readOnly();
    }

    @Override
    public String readWrite() {
        return origin.readWrite();
    }

    @Override
    public String quit() {
        return origin.quit();
    }

    @Override
    public Long waitForReplication(int replicas, long timeout) {
        return origin.waitForReplication(replicas, timeout);
    }

    @Override
    public <T> T dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output) {
        return origin.dispatch(type, output);
    }

    @Override
    public <T> T dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output, CommandArgs<K, V> args) {
        return origin.dispatch(type, output, args);
    }

    @Override
    public boolean isOpen() {
        return origin.isOpen();
    }

    @Override
    public void reset() {
        origin.reset();
    }

    @Override
    public void setTimeout(Duration timeout) {
        origin.setTimeout(timeout);
    }

    @Override
    @Deprecated
    public void setTimeout(long timeout, TimeUnit unit) {
        origin.setTimeout(timeout, unit);
    }

    @Override
    public String clusterBumpepoch() {
        return origin.clusterBumpepoch();
    }

    @Override
    public String clusterMeet(String ip, int port) {
        return origin.clusterMeet(ip, port);
    }

    @Override
    public String clusterForget(String nodeId) {
        return origin.clusterForget(nodeId);
    }

    @Override
    public String clusterAddSlots(int... slots) {
        return origin.clusterAddSlots(slots);
    }

    @Override
    public String clusterDelSlots(int... slots) {
        return origin.clusterDelSlots(slots);
    }

    @Override
    public String clusterSetSlotNode(int slot, String nodeId) {
        return origin.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public String clusterSetSlotStable(int slot) {
        return origin.clusterSetSlotStable(slot);
    }

    @Override
    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return origin.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public String clusterSetSlotImporting(int slot, String nodeId) {
        return origin.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public String clusterInfo() {
        return origin.clusterInfo();
    }

    @Override
    public String clusterMyId() {
        return origin.clusterMyId();
    }

    @Override
    public String clusterNodes() {
        return origin.clusterNodes();
    }

    @Override
    public List<String> clusterSlaves(String nodeId) {
        return origin.clusterSlaves(nodeId);
    }

    @Override
    public List<K> clusterGetKeysInSlot(int slot, int count) {
        return origin.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public Long clusterCountKeysInSlot(int slot) {
        return origin.clusterCountKeysInSlot(slot);
    }

    @Override
    public Long clusterCountFailureReports(String nodeId) {
        return origin.clusterCountFailureReports(nodeId);
    }

    @Override
    public Long clusterKeyslot(K key) {
        return origin.clusterKeyslot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String clusterSaveconfig() {
        return origin.clusterSaveconfig();
    }

    @Override
    public String clusterSetConfigEpoch(long configEpoch) {
        return origin.clusterSetConfigEpoch(configEpoch);
    }

    @Override
    public List<Object> clusterSlots() {
        return origin.clusterSlots();
    }

    @Override
    public String asking() {
        return origin.asking();
    }

    @Override
    public String clusterReplicate(String nodeId) {
        return origin.clusterReplicate(nodeId);
    }

    @Override
    public String clusterFailover(boolean force) {
        return origin.clusterFailover(force);
    }

    @Override
    public String clusterReset(boolean hard) {
        return origin.clusterReset(hard);
    }

    @Override
    public String clusterFlushslots() {
        return origin.clusterFlushslots();
    }

    @Override
    public Long geoadd(K key, double longitude, double latitude, V member) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, member);
    }

    @Override
    public Long geoadd(K key, Object... lngLatMember) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), lngLatMember);
    }

    @Override
    public List<Value<String>> geohash(K key, V... members) {
        return origin.geohash(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Set<V> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit);
    }

    @Override
    public List<GeoWithin<V>> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoArgs);
    }

    @Override
    public Long georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public Set<V> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit);
    }

    @Override
    public List<GeoWithin<V>> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoArgs);
    }

    @Override
    public Long georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public List<GeoCoordinates> geopos(K key, V... members) {
        return origin.geopos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Double geodist(K key, V from, V to, GeoArgs.Unit unit) {
        return origin.geodist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), from, to, unit);
    }

    @Override
    public Long hdel(K key, K... fields) {
        return origin.hdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public Boolean hexists(K key, K field) {
        return origin.hexists(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public V hget(K key, K field) {
        return origin.hget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public Long hincrby(K key, K field, long amount) {
        return origin.hincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public Double hincrbyfloat(K key, K field, double amount) {
        return origin.hincrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public Map<K, V> hgetall(K key) {
        return origin.hgetall(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long hgetall(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hgetall(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<K> hkeys(K key) {
        return origin.hkeys(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long hkeys(KeyStreamingChannel<K> channel, K key) {
        return origin.hkeys(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long hlen(K key) {
        return origin.hlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<KeyValue<K, V>> hmget(K key, K... fields) {
        return origin.hmget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public Long hmget(KeyValueStreamingChannel<K, V> channel, K key, K... fields) {
        return origin.hmget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public String hmset(K key, Map<K, V> map) {
        return origin.hmset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public MapScanCursor<K, V> hscan(K key) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public MapScanCursor<K, V> hscan(K key, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public MapScanCursor<K, V> hscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public MapScanCursor<K, V> hscan(K key, ScanCursor scanCursor) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Boolean hset(K key, K field, V value) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public Long hset(K key, Map<K, V> map) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public Boolean hsetnx(K key, K field, V value) {
        return origin.hsetnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public Long hstrlen(K key, K field) {
        return origin.hstrlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public List<V> hvals(K key) {
        return origin.hvals(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long hvals(ValueStreamingChannel<V> channel, K key) {
        return origin.hvals(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long pfadd(K key, V... values) {
        return origin.pfadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public String pfmerge(K destkey, K... sourcekeys) {
        return origin.pfmerge(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destkey), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(sourcekeys));
    }

    @Override
    public Long pfcount(K... keys) {
        return origin.pfcount(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long del(K... keys) {
        return origin.del(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long unlink(K... keys) {
        return origin.unlink(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public byte[] dump(K key) {
        return origin.dump(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long exists(K... keys) {
        return origin.exists(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Boolean expire(K key, long seconds) {
        return origin.expire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds);
    }

    @Override
    public Boolean expireat(K key, Date timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Boolean expireat(K key, long timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public List<K> keys(K pattern) {
        return origin.keys(RedisToolManager.LETTUCE_TOOL.convertShadowKey(pattern));
    }

    @Override
    public Long keys(KeyStreamingChannel<K> channel, K pattern) {
        return origin.keys(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(pattern));
    }

    @Override
    public String migrate(String host, int port, K key, int db, long timeout) {
        return origin.migrate(host, port, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db, timeout);
    }

    @Override
    public String migrate(String host, int port, int db, long timeout, MigrateArgs<K> migrateArgs) {
        return origin.migrate(host, port, db, timeout, migrateArgs);
    }

    @Override
    public Boolean move(K key, int db) {
        return origin.move(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db);
    }

    @Override
    public String objectEncoding(K key) {
        return origin.objectEncoding(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long objectIdletime(K key) {
        return origin.objectIdletime(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long objectRefcount(K key) {
        return origin.objectRefcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Boolean persist(K key) {
        return origin.persist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Boolean pexpire(K key, long milliseconds) {
        return origin.pexpire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds);
    }

    @Override
    public Boolean pexpireat(K key, Date timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Boolean pexpireat(K key, long timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Long pttl(K key) {
        return origin.pttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public K randomkey() {
        return origin.randomkey();
    }

    @Override
    public String rename(K key, K newKey) {
        return origin.rename(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Boolean renamenx(K key, K newKey) {
        return origin.renamenx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String restore(K key, long ttl, byte[] value) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), ttl, value);
    }

    @Override
    public String restore(K key, byte[] value, RestoreArgs args) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, args);
    }

    @Override
    public List<V> sort(K key) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long sort(ValueStreamingChannel<V> channel, K key) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<V> sort(K key, SortArgs sortArgs) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public Long sort(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public Long sortStore(K key, SortArgs sortArgs, K destination) {
        return origin.sortStore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs, destination);
    }

    @Override
    public Long touch(K... keys) {
        return origin.touch(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long ttl(K key) {
        return origin.ttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String type(K key) {
        return origin.type(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public KeyScanCursor<K> scan() {
        return origin.scan();
    }

    @Override
    public KeyScanCursor<K> scan(ScanArgs scanArgs) {
        return origin.scan(scanArgs);
    }

    @Override
    public KeyScanCursor<K> scan(ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(scanCursor, scanArgs);
    }

    @Override
    public KeyScanCursor<K> scan(ScanCursor scanCursor) {
        return origin.scan(scanCursor);
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel<K> channel) {
        return origin.scan(channel);
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanArgs scanArgs) {
        return origin.scan(channel, scanArgs);
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(channel, scanCursor, scanArgs);
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor) {
        return origin.scan(channel, scanCursor);
    }

    @Override
    public KeyValue<K, V> blpop(long timeout, K... keys) {
        return origin.blpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public KeyValue<K, V> brpop(long timeout, K... keys) {
        return origin.brpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public V brpoplpush(long timeout, K source, K destination) {
        return origin.brpoplpush(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKey(source), RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public V lindex(K key, long index) {
        return origin.lindex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index);
    }

    @Override
    public Long linsert(K key, boolean before, V pivot, V value) {
        return origin.linsert(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), before, pivot, value);
    }

    @Override
    public Long llen(K key) {
        return origin.llen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public V lpop(K key) {
        return origin.lpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long lpush(K key, V... values) {
        return origin.lpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Long lpushx(K key, V... values) {
        return origin.lpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public List<V> lrange(K key, long start, long stop) {
        return origin.lrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long lrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.lrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long lrem(K key, long count, V value) {
        return origin.lrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count, value);
    }

    @Override
    public String lset(K key, long index, V value) {
        return origin.lset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index, value);
    }

    @Override
    public String ltrim(K key, long start, long stop) {
        return origin.ltrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public V rpop(K key) {
        return origin.rpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public V rpoplpush(K source, K destination) {
        return origin.rpoplpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source), RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public Long rpush(K key, V... values) {
        return origin.rpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Long rpushx(K key, V... values) {
        return origin.rpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public <T> T eval(String script, ScriptOutputType type, K... keys) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> T eval(String script, ScriptOutputType type, K[] keys, V... values) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public <T> T evalsha(String digest, ScriptOutputType type, K... keys) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> T evalsha(String digest, ScriptOutputType type, K[] keys, V... values) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public List<Boolean> scriptExists(String... digests) {
        return origin.scriptExists(digests);
    }

    @Override
    public String scriptFlush() {
        return origin.scriptFlush();
    }

    @Override
    public String scriptKill() {
        return origin.scriptKill();
    }

    @Override
    public String scriptLoad(V script) {
        return origin.scriptLoad(script);
    }

    @Override
    public String digest(V script) {
        return origin.digest(script);
    }

    @Override
    public String bgrewriteaof() {
        return origin.bgrewriteaof();
    }

    @Override
    public String bgsave() {
        return origin.bgsave();
    }

    @Override
    public K clientGetname() {
        return origin.clientGetname();
    }

    @Override
    public String clientSetname(K name) {
        return origin.clientSetname(name);
    }

    @Override
    public String clientKill(String addr) {
        return origin.clientKill(addr);
    }

    @Override
    public Long clientKill(KillArgs killArgs) {
        return origin.clientKill(killArgs);
    }

    @Override
    public Long clientUnblock(long id, UnblockType type) {
        return origin.clientUnblock(id, type);
    }

    @Override
    public String clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public String clientList() {
        return origin.clientList();
    }

    @Override
    public Long clientId() {
        return origin.clientId();
    }

    @Override
    public List<Object> command() {
        return origin.command();
    }

    @Override
    public List<Object> commandInfo(String... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public List<Object> commandInfo(CommandType... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public Long commandCount() {
        return origin.commandCount();
    }

    @Override
    public Map<String, String> configGet(String parameter) {
        return origin.configGet(parameter);
    }

    @Override
    public String configResetstat() {
        return origin.configResetstat();
    }

    @Override
    public String configRewrite() {
        return origin.configRewrite();
    }

    @Override
    public String configSet(String parameter, String value) {
        return origin.configSet(parameter, value);
    }

    @Override
    public Long dbsize() {
        return origin.dbsize();
    }

    @Override
    public String debugCrashAndRecover(Long delay) {
        return origin.debugCrashAndRecover(delay);
    }

    @Override
    public String debugHtstats(int db) {
        return origin.debugHtstats(db);
    }

    @Override
    public String debugObject(K key) {
        return origin.debugObject(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public void debugOom() {
        origin.debugOom();
    }

    @Override
    public void debugSegfault() {
        origin.debugSegfault();
    }

    @Override
    public String debugReload() {
        return origin.debugReload();
    }

    @Override
    public String debugRestart(Long delay) {
        return origin.debugRestart(delay);
    }

    @Override
    public String debugSdslen(K key) {
        return origin.debugSdslen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String flushall() {
        return origin.flushall();
    }

    @Override
    public String flushallAsync() {
        return origin.flushallAsync();
    }

    @Override
    public String flushdb() {
        return origin.flushdb();
    }

    @Override
    public String flushdbAsync() {
        return origin.flushdbAsync();
    }

    @Override
    public String info() {
        return origin.info();
    }

    @Override
    public String info(String section) {
        return origin.info(section);
    }

    @Override
    public Date lastsave() {
        return origin.lastsave();
    }

    @Override
    public Long memoryUsage(K key) {
        return origin.memoryUsage(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String save() {
        return origin.save();
    }

    @Override
    public void shutdown(boolean save) {
        origin.shutdown(save);
    }

    @Override
    public String slaveof(String host, int port) {
        return origin.slaveof(host, port);
    }

    @Override
    public String slaveofNoOne() {
        return origin.slaveofNoOne();
    }

    @Override
    public List<Object> slowlogGet() {
        return origin.slowlogGet();
    }

    @Override
    public List<Object> slowlogGet(int count) {
        return origin.slowlogGet(count);
    }

    @Override
    public Long slowlogLen() {
        return origin.slowlogLen();
    }

    @Override
    public String slowlogReset() {
        return origin.slowlogReset();
    }

    @Override
    public List<V> time() {
        return origin.time();
    }

    @Override
    public Long sadd(K key, V... members) {
        return origin.sadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Long scard(K key) {
        return origin.scard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Set<V> sdiff(K... keys) {
        return origin.sdiff(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sdiff(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sdiff(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sdiffstore(K destination, K... keys) {
        return origin.sdiffstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Set<V> sinter(K... keys) {
        return origin.sinter(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sinter(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sinter(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sinterstore(K destination, K... keys) {
        return origin.sinterstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Boolean sismember(K key, V member) {
        return origin.sismember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Boolean smove(K source, K destination, V member) {
        return origin.smove(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source), RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), member);
    }

    @Override
    public Set<V> smembers(K key) {
        return origin.smembers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long smembers(ValueStreamingChannel<V> channel, K key) {
        return origin.smembers(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public V spop(K key) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Set<V> spop(K key, long count) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public V srandmember(K key) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<V> srandmember(K key, long count) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Long srandmember(ValueStreamingChannel<V> channel, K key, long count) {
        return origin.srandmember(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Long srem(K key, V... members) {
        return origin.srem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Set<V> sunion(K... keys) {
        return origin.sunion(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sunion(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sunion(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long sunionstore(K destination, K... keys) {
        return origin.sunionstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public ValueScanCursor<V> sscan(K key) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public ValueScanCursor<V> sscan(K key, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public ValueScanCursor<V> sscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public ValueScanCursor<V> sscan(K key, ScanCursor scanCursor) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel<V> channel, K key) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public KeyValue<K, ScoredValue<V>> bzpopmin(long timeout, K... keys) {
        return origin.bzpopmin(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public KeyValue<K, ScoredValue<V>> bzpopmax(long timeout, K... keys) {
        return origin.bzpopmax(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long zadd(K key, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public Long zadd(K key, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoresAndValues);
    }

    @Override
    public Long zadd(K key, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoredValues);
    }

    @Override
    public Long zadd(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public Long zadd(K key, ZAddArgs zAddArgs, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoresAndValues);
    }

    @Override
    public Long zadd(K key, ZAddArgs zAddArgs, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoredValues);
    }

    @Override
    public Double zaddincr(K key, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public Double zaddincr(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public Long zcard(K key) {
        return origin.zcard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    @Deprecated
    public Long zcount(K key, double min, double max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Long zcount(K key, String min, String max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zcount(K key, Range<? extends Number> range) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Double zincrby(K key, double amount, V member) {
        return origin.zincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount, member);
    }

    @Override
    public Long zinterstore(K destination, K... keys) {
        return origin.zinterstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long zinterstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zinterstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), storeArgs, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    @Deprecated
    public Long zlexcount(K key, String min, String max) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zlexcount(K key, Range<? extends V> range) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public ScoredValue<V> zpopmin(K key) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<ScoredValue<V>> zpopmin(K key, long count) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public ScoredValue<V> zpopmax(K key) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<ScoredValue<V>> zpopmax(K key, long count) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public List<V> zrange(K key, long start, long stop) {
        return origin.zrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long zrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public List<ScoredValue<V>> zrangeWithScores(K key, long start, long stop) {
        return origin.zrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long zrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public List<V> zrangebylex(K key, String min, String max) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public List<V> zrangebylex(K key, Range<? extends V> range) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public List<V> zrangebylex(K key, String min, String max, long offset, long count) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public List<V> zrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public List<V> zrangebyscore(K key, double min, double max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public List<V> zrangebyscore(K key, String min, String max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public List<V> zrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public List<V> zrangebyscore(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public List<V> zrangebyscore(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public List<V> zrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public List<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Long zrank(K key, V member) {
        return origin.zrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Long zrem(K key, V... members) {
        return origin.zrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    @Deprecated
    public Long zremrangebylex(K key, String min, String max) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zremrangebylex(K key, Range<? extends V> range) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Long zremrangebyrank(K key, long start, long stop) {
        return origin.zremrangebyrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public Long zremrangebyscore(K key, double min, double max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Long zremrangebyscore(K key, String min, String max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Long zremrangebyscore(K key, Range<? extends Number> range) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public List<V> zrevrange(K key, long start, long stop) {
        return origin.zrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long zrevrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public List<ScoredValue<V>> zrevrangeWithScores(K key, long start, long stop) {
        return origin.zrevrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Long zrevrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public List<V> zrevrangebylex(K key, Range<? extends V> range) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public List<V> zrevrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public List<V> zrevrangebyscore(K key, double max, double min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public List<V> zrevrangebyscore(K key, String max, String min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public List<V> zrevrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public List<V> zrevrangebyscore(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public List<V> zrevrangebyscore(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public List<V> zrevrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Long zrevrank(K key, V member) {
        return origin.zrevrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public ScoredValueScanCursor<V> zscan(K key) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public ScoredValueScanCursor<V> zscan(K key, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public ScoredValueScanCursor<V> zscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public ScoredValueScanCursor<V> zscan(K key, ScanCursor scanCursor) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Double zscore(K key, V member) {
        return origin.zscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Long zunionstore(K destination, K... keys) {
        return origin.zunionstore(destination, keys);
    }

    @Override
    public Long zunionstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zunionstore(destination, storeArgs, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long xack(K key, K group, String... messageIds) {
        return origin.xack(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, messageIds);
    }

    @Override
    public String xadd(K key, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), body);
    }

    @Override
    public String xadd(K key, XAddArgs args, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, body);
    }

    @Override
    public String xadd(K key, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), keysAndValues);
    }

    @Override
    public String xadd(K key, XAddArgs args, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, keysAndValues);
    }

    @Override
    public List<StreamMessage<K, V>> xclaim(K key, Consumer<K> consumer, long minIdleTime, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, minIdleTime, messageIds);
    }

    @Override
    public List<StreamMessage<K, V>> xclaim(K key, Consumer<K> consumer, XClaimArgs args, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, args, messageIds);
    }

    @Override
    public Long xdel(K key, String... messageIds) {
        return origin.xdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), messageIds);
    }

    @Override
    public String xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupCreate(streamOffset, group);
    }

    @Override
    public String xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group, XGroupCreateArgs args) {
        return origin.xgroupCreate(streamOffset, group, args);
    }

    @Override
    public Boolean xgroupDelconsumer(K key, Consumer<K> consumer) {
        return origin.xgroupDelconsumer(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer);
    }

    @Override
    public Boolean xgroupDestroy(K key, K group) {
        return origin.xgroupDestroy(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public String xgroupSetid(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupSetid(streamOffset, group);
    }

    @Override
    public List<Object> xinfoStream(K key) {
        return origin.xinfoStream(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<Object> xinfoGroups(K key) {
        return origin.xinfoGroups(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<Object> xinfoConsumers(K key, K group) {
        return origin.xinfoConsumers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public Long xlen(K key) {
        return origin.xlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public List<Object> xpending(K key, K group) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public List<Object> xpending(K key, K group, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, range, limit);
    }

    @Override
    public List<Object> xpending(K key, Consumer<K> consumer, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, range, limit);
    }

    @Override
    public List<StreamMessage<K, V>> xrange(K key, Range<String> range) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public List<StreamMessage<K, V>> xrange(K key, Range<String> range, Limit limit) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public List<StreamMessage<K, V>> xread(XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(streams);
    }

    @Override
    public List<StreamMessage<K, V>> xread(XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(args, streams);
    }

    @Override
    public List<StreamMessage<K, V>> xreadgroup(Consumer<K> consumer, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, streams);
    }

    @Override
    public List<StreamMessage<K, V>> xreadgroup(Consumer<K> consumer, XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, args, streams);
    }

    @Override
    public List<StreamMessage<K, V>> xrevrange(K key, Range<String> range) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public List<StreamMessage<K, V>> xrevrange(K key, Range<String> range, Limit limit) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Long xtrim(K key, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Long xtrim(K key, boolean approximateTrimming, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), approximateTrimming, count);
    }

    @Override
    public Long append(K key, V value) {
        return origin.append(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Long bitcount(K key) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long bitcount(K key, long start, long end) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public List<Long> bitfield(K key, BitFieldArgs bitFieldArgs) {
        return origin.bitfield(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), bitFieldArgs);
    }

    @Override
    public Long bitpos(K key, boolean state) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state);
    }

    @Override
    public Long bitpos(K key, boolean state, long start) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start);
    }

    @Override
    public Long bitpos(K key, boolean state, long start, long end) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start, end);
    }

    @Override
    public Long bitopAnd(K destination, K... keys) {
        return origin.bitopAnd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long bitopNot(K destination, K source) {
        return origin.bitopNot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKey(source));
    }

    @Override
    public Long bitopOr(K destination, K... keys) {
        return origin.bitopOr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long bitopXor(K destination, K... keys) {
        return origin.bitopXor(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long decr(K key) {
        return origin.decr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long decrby(K key, long amount) {
        return origin.decrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public V get(K key) {
        return origin.get(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long getbit(K key, long offset) {
        return origin.getbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset);
    }

    @Override
    public V getrange(K key, long start, long end) {
        return origin.getrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public V getset(K key, V value) {
        return origin.getset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Long incr(K key) {
        return origin.incr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Long incrby(K key, long amount) {
        return origin.incrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public Double incrbyfloat(K key, double amount) {
        return origin.incrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public List<KeyValue<K, V>> mget(K... keys) {
        return origin.mget(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Long mget(KeyValueStreamingChannel<K, V> channel, K... keys) {
        return origin.mget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public String mset(Map<K, V> map) {
        return origin.mset(map);
    }

    @Override
    public Boolean msetnx(Map<K, V> map) {
        return origin.msetnx(map);
    }

    @Override
    public String set(K key, V value) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public String set(K key, V value, SetArgs setArgs) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, setArgs);
    }

    @Override
    public Long setbit(K key, long offset, int value) {
        return origin.setbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public String setex(K key, long seconds, V value) {
        return origin.setex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds, value);
    }

    @Override
    public String psetex(K key, long milliseconds, V value) {
        return origin.psetex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds, value);
    }

    @Override
    public Boolean setnx(K key, V value) {
        return origin.setnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Long setrange(K key, long offset, V value) {
        return origin.setrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public Long strlen(K key) {
        return origin.strlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public String discard() {
        return origin.discard();
    }

    @Override
    public TransactionResult exec() {
        return origin.exec();
    }

    @Override
    public String multi() {
        return origin.multi();
    }

    @Override
    public String watch(K... keys) {
        return origin.watch(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public String unwatch() {
        return origin.unwatch();
    }

}
