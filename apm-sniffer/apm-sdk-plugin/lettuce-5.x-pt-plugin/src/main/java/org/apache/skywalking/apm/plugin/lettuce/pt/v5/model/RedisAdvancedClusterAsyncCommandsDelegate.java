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
import io.lettuce.core.RedisFuture;
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
import io.lettuce.core.UnblockType;
import io.lettuce.core.Value;
import io.lettuce.core.ValueScanCursor;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XGroupCreateArgs;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.ZAddArgs;
import io.lettuce.core.ZStoreArgs;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.AsyncNodeSelection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;
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
import java.util.function.Predicate;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisAdvancedClusterAsyncCommandsDelegate<K, V> implements RedisAdvancedClusterAsyncCommands<K, V> {
    private final RedisAdvancedClusterAsyncCommands<K, V> origin;

    public RedisAdvancedClusterAsyncCommandsDelegate(RedisAdvancedClusterAsyncCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public RedisClusterAsyncCommands<K, V> getConnection(String nodeId) {
        return origin.getConnection(nodeId);
    }

    @Override
    public RedisClusterAsyncCommands<K, V> getConnection(String host, int port) {
        return origin.getConnection(host, port);
    }

    @Override
    public StatefulRedisClusterConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }

    @Override
    public AsyncNodeSelection<K, V> masters() {
        return origin.masters();
    }

    @Override
    @Deprecated
    public AsyncNodeSelection<K, V> slaves() {
        return origin.slaves();
    }

    @Override
    @Deprecated
    public AsyncNodeSelection<K, V> slaves(Predicate<RedisClusterNode> predicate) {
        return origin.slaves(predicate);
    }

    @Override
    public AsyncNodeSelection<K, V> replicas() {
        return origin.replicas();
    }

    @Override
    public AsyncNodeSelection<K, V> replicas(Predicate<RedisClusterNode> predicate) {
        return origin.replicas(predicate);
    }

    @Override
    public AsyncNodeSelection<K, V> all() {
        return origin.all();
    }

    @Override
    public AsyncNodeSelection<K, V> readonly(Predicate<RedisClusterNode> predicate) {
        return origin.readonly(predicate);
    }

    @Override
    public AsyncNodeSelection<K, V> nodes(Predicate<RedisClusterNode> predicate) {
        return origin.nodes(predicate);
    }

    @Override
    public AsyncNodeSelection<K, V> nodes(Predicate<RedisClusterNode> predicate, boolean dynamic) {
        return origin.nodes(predicate, dynamic);
    }

    @Override
    public RedisFuture<Long> del(K... keys) {
        return origin.del(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> unlink(K... keys) {
        return origin.unlink(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> exists(K... keys) {
        return origin.exists(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<List<KeyValue<K, V>>> mget(K... keys) {
        return origin.mget(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<String> mset(Map<K, V> map) {
        return origin.mset(map);
    }

    @Override
    public RedisFuture<Boolean> msetnx(Map<K, V> map) {
        return origin.msetnx(map);
    }

    @Override
    public RedisFuture<String> clientSetname(K name) {
        return origin.clientSetname(name);
    }

    @Override
    public RedisFuture<String> flushall() {
        return origin.flushall();
    }

    @Override
    public RedisFuture<String> flushdb() {
        return origin.flushdb();
    }

    @Override
    public RedisFuture<Long> dbsize() {
        return origin.dbsize();
    }

    @Override
    public RedisFuture<List<K>> keys(K pattern) {
        return origin.keys(pattern);
    }

    @Override
    public RedisFuture<Long> keys(KeyStreamingChannel<K> channel, K pattern) {
        return origin.keys(channel, pattern);
    }

    @Override
    public RedisFuture<K> randomkey() {
        return origin.randomkey();
    }

    @Override
    public RedisFuture<String> scriptFlush() {
        return origin.scriptFlush();
    }

    @Override
    public RedisFuture<String> scriptKill() {
        return origin.scriptKill();
    }

    @Override
    public RedisFuture<String> scriptLoad(V script) {
        return origin.scriptLoad(script);
    }

    @Override
    public void shutdown(boolean save) {
        origin.shutdown(save);
    }

    @Override
    public RedisFuture<KeyScanCursor<K>> scan() {
        return origin.scan();
    }

    @Override
    public RedisFuture<KeyScanCursor<K>> scan(ScanArgs scanArgs) {
        return origin.scan(scanArgs);
    }

    @Override
    public RedisFuture<KeyScanCursor<K>> scan(ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<KeyScanCursor<K>> scan(ScanCursor scanCursor) {
        return origin.scan(scanCursor);
    }

    @Override
    public RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel) {
        return origin.scan(channel);
    }

    @Override
    public RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanArgs scanArgs) {
        return origin.scan(channel, scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(channel, scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor) {
        return origin.scan(channel, scanCursor);
    }

    @Override
    public RedisFuture<Long> touch(K... keys) {
        return origin.touch(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
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
    public String auth(String password) {
        return origin.auth(password);
    }

    @Override
    public RedisFuture<String> clusterBumpepoch() {
        return origin.clusterBumpepoch();
    }

    @Override
    public RedisFuture<String> clusterMeet(String ip, int port) {
        return origin.clusterMeet(ip, port);
    }

    @Override
    public RedisFuture<String> clusterForget(String nodeId) {
        return origin.clusterForget(nodeId);
    }

    @Override
    public RedisFuture<String> clusterAddSlots(int... slots) {
        return origin.clusterAddSlots(slots);
    }

    @Override
    public RedisFuture<String> clusterDelSlots(int... slots) {
        return origin.clusterDelSlots(slots);
    }

    @Override
    public RedisFuture<String> clusterSetSlotNode(int slot, String nodeId) {
        return origin.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public RedisFuture<String> clusterSetSlotStable(int slot) {
        return origin.clusterSetSlotStable(slot);
    }

    @Override
    public RedisFuture<String> clusterSetSlotMigrating(int slot, String nodeId) {
        return origin.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public RedisFuture<String> clusterSetSlotImporting(int slot, String nodeId) {
        return origin.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public RedisFuture<String> clusterInfo() {
        return origin.clusterInfo();
    }

    @Override
    public RedisFuture<String> clusterMyId() {
        return origin.clusterMyId();
    }

    @Override
    public RedisFuture<String> clusterNodes() {
        return origin.clusterNodes();
    }

    @Override
    public RedisFuture<List<String>> clusterSlaves(String nodeId) {
        return origin.clusterSlaves(nodeId);
    }

    @Override
    public RedisFuture<List<K>> clusterGetKeysInSlot(int slot, int count) {
        return origin.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public RedisFuture<Long> clusterCountKeysInSlot(int slot) {
        return origin.clusterCountKeysInSlot(slot);
    }

    @Override
    public RedisFuture<Long> clusterCountFailureReports(String nodeId) {
        return origin.clusterCountFailureReports(nodeId);
    }

    @Override
    public RedisFuture<Long> clusterKeyslot(K key) {
        return origin.clusterKeyslot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> clusterSaveconfig() {
        return origin.clusterSaveconfig();
    }

    @Override
    public RedisFuture<String> clusterSetConfigEpoch(long configEpoch) {
        return origin.clusterSetConfigEpoch(configEpoch);
    }

    @Override
    public RedisFuture<List<Object>> clusterSlots() {
        return origin.clusterSlots();
    }

    @Override
    public RedisFuture<String> asking() {
        return origin.asking();
    }

    @Override
    public RedisFuture<String> clusterReplicate(String nodeId) {
        return origin.clusterReplicate(nodeId);
    }

    @Override
    public RedisFuture<String> clusterFailover(boolean force) {
        return origin.clusterFailover(force);
    }

    @Override
    public RedisFuture<String> clusterReset(boolean hard) {
        return origin.clusterReset(hard);
    }

    @Override
    public RedisFuture<String> clusterFlushslots() {
        return origin.clusterFlushslots();
    }

    @Override
    public RedisFuture<String> readOnly() {
        return origin.readOnly();
    }

    @Override
    public RedisFuture<String> readWrite() {
        return origin.readWrite();
    }

    @Override
    public RedisFuture<Long> publish(K channel, V message) {
        return origin.publish(RedisToolManager.LETTUCE_TOOL.convertShadowKey(channel), message);
    }

    @Override
    public RedisFuture<List<K>> pubsubChannels() {
        return origin.pubsubChannels();
    }

    @Override
    public RedisFuture<List<K>> pubsubChannels(K channel) {
        return origin.pubsubChannels(RedisToolManager.LETTUCE_TOOL.convertShadowKey(channel));
    }

    @Override
    public RedisFuture<Map<K, Long>> pubsubNumsub(K... channels) {
        return origin.pubsubNumsub(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(channels));
    }

    @Override
    public RedisFuture<Long> pubsubNumpat() {
        return origin.pubsubNumpat();
    }

    @Override
    public RedisFuture<V> echo(V msg) {
        return origin.echo(msg);
    }

    @Override
    public RedisFuture<List<Object>> role() {
        return origin.role();
    }

    @Override
    public RedisFuture<String> ping() {
        return origin.ping();
    }

    @Override
    public RedisFuture<String> quit() {
        return origin.quit();
    }

    @Override
    public RedisFuture<Long> waitForReplication(int replicas, long timeout) {
        return origin.waitForReplication(replicas, timeout);
    }

    @Override
    public <T> RedisFuture<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output) {
        return origin.dispatch(type, output);
    }

    @Override
    public <T> RedisFuture<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output, CommandArgs<K, V> args) {
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
    public void setAutoFlushCommands(boolean autoFlush) {
        origin.setAutoFlushCommands(autoFlush);
    }

    @Override
    public void flushCommands() {
        origin.flushCommands();
    }

    @Override
    public RedisFuture<Long> geoadd(K key, double longitude, double latitude, V member) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, member);
    }

    @Override
    public RedisFuture<Long> geoadd(K key, Object... lngLatMember) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), lngLatMember);
    }

    @Override
    public RedisFuture<List<Value<String>>> geohash(K key, V... members) {
        return origin.geohash(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public RedisFuture<Set<V>> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit);
    }

    @Override
    public RedisFuture<List<GeoWithin<V>>> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoArgs);
    }

    @Override
    public RedisFuture<Long> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public RedisFuture<Set<V>> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit);
    }

    @Override
    public RedisFuture<List<GeoWithin<V>>> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoArgs);
    }

    @Override
    public RedisFuture<Long> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public RedisFuture<List<GeoCoordinates>> geopos(K key, V... members) {
        return origin.geopos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public RedisFuture<Double> geodist(K key, V from, V to, GeoArgs.Unit unit) {
        return origin.geodist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), from, to, unit);
    }

    @Override
    public RedisFuture<Long> hdel(K key, K... fields) {
        return origin.hdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public RedisFuture<Boolean> hexists(K key, K field) {
        return origin.hexists(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public RedisFuture<V> hget(K key, K field) {
        return origin.hget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public RedisFuture<Long> hincrby(K key, K field, long amount) {
        return origin.hincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public RedisFuture<Double> hincrbyfloat(K key, K field, double amount) {
        return origin.hincrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public RedisFuture<Map<K, V>> hgetall(K key) {
        return origin.hgetall(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> hgetall(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hgetall(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<K>> hkeys(K key) {
        return origin.hkeys(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> hkeys(KeyStreamingChannel<K> channel, K key) {
        return origin.hkeys(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> hlen(K key) {
        return origin.hlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<KeyValue<K, V>>> hmget(K key, K... fields) {
        return origin.hmget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public RedisFuture<Long> hmget(KeyValueStreamingChannel<K, V> channel, K key, K... fields) {
        return origin.hmget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public RedisFuture<String> hmset(K key, Map<K, V> map) {
        return origin.hmset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public RedisFuture<MapScanCursor<K, V>> hscan(K key) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<MapScanCursor<K, V>> hscan(K key, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<MapScanCursor<K, V>> hscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<MapScanCursor<K, V>> hscan(K key, ScanCursor scanCursor) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<Boolean> hset(K key, K field, V value) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public RedisFuture<Long> hset(K key, Map<K, V> map) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public RedisFuture<Boolean> hsetnx(K key, K field, V value) {
        return origin.hsetnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public RedisFuture<Long> hstrlen(K key, K field) {
        return origin.hstrlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public RedisFuture<List<V>> hvals(K key) {
        return origin.hvals(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> hvals(ValueStreamingChannel<V> channel, K key) {
        return origin.hvals(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> pfadd(K key, V... values) {
        return origin.pfadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public RedisFuture<String> pfmerge(K destkey, K... sourcekeys) {
        return origin.pfmerge(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destkey), RedisToolManager.LETTUCE_TOOL.convertShadowKey(sourcekeys));
    }

    @Override
    public RedisFuture<Long> pfcount(K... keys) {
        return origin.pfcount(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<byte[]> dump(K key) {
        return origin.dump(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Boolean> expire(K key, long seconds) {
        return origin.expire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds);
    }

    @Override
    public RedisFuture<Boolean> expireat(K key, Date timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public RedisFuture<Boolean> expireat(K key, long timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public RedisFuture<String> migrate(String host, int port, K key, int db, long timeout) {
        return origin.migrate(host, port, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db, timeout);
    }

    @Override
    public RedisFuture<String> migrate(String host, int port, int db, long timeout, MigrateArgs<K> migrateArgs) {
        return origin.migrate(host, port, db, timeout, migrateArgs);
    }

    @Override
    public RedisFuture<Boolean> move(K key, int db) {
        return origin.move(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db);
    }

    @Override
    public RedisFuture<String> objectEncoding(K key) {
        return origin.objectEncoding(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> objectIdletime(K key) {
        return origin.objectIdletime(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> objectRefcount(K key) {
        return origin.objectRefcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Boolean> persist(K key) {
        return origin.persist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Boolean> pexpire(K key, long milliseconds) {
        return origin.pexpire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds);
    }

    @Override
    public RedisFuture<Boolean> pexpireat(K key, Date timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public RedisFuture<Boolean> pexpireat(K key, long timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public RedisFuture<Long> pttl(K key) {
        return origin.pttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> rename(K key, K newKey) {
        return origin.rename(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), newKey);
    }

    @Override
    public RedisFuture<Boolean> renamenx(K key, K newKey) {
        return origin.renamenx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), newKey);
    }

    @Override
    public RedisFuture<String> restore(K key, long ttl, byte[] value) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), ttl, value);
    }

    @Override
    public RedisFuture<String> restore(K key, byte[] value, RestoreArgs args) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, args);
    }

    @Override
    public RedisFuture<List<V>> sort(K key) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> sort(ValueStreamingChannel<V> channel, K key) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<V>> sort(K key, SortArgs sortArgs) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public RedisFuture<Long> sort(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public RedisFuture<Long> sortStore(K key, SortArgs sortArgs, K destination) {
        return origin.sortStore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs, destination);
    }

    @Override
    public RedisFuture<Long> ttl(K key) {
        return origin.ttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> type(K key) {
        return origin.type(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<KeyValue<K, V>> blpop(long timeout, K... keys) {
        return origin.blpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<KeyValue<K, V>> brpop(long timeout, K... keys) {
        return origin.brpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<V> brpoplpush(long timeout, K source, K destination) {
        return origin.brpoplpush(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKey(source), RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public RedisFuture<V> lindex(K key, long index) {
        return origin.lindex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index);
    }

    @Override
    public RedisFuture<Long> linsert(K key, boolean before, V pivot, V value) {
        return origin.linsert(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), before, pivot, value);
    }

    @Override
    public RedisFuture<Long> llen(K key) {
        return origin.llen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<V> lpop(K key) {
        return origin.lpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> lpush(K key, V... values) {
        return origin.lpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public RedisFuture<Long> lpushx(K key, V... values) {
        return origin.lpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public RedisFuture<List<V>> lrange(K key, long start, long stop) {
        return origin.lrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> lrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.lrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> lrem(K key, long count, V value) {
        return origin.lrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count, value);
    }

    @Override
    public RedisFuture<String> lset(K key, long index, V value) {
        return origin.lset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index, value);
    }

    @Override
    public RedisFuture<String> ltrim(K key, long start, long stop) {
        return origin.ltrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<V> rpop(K key) {
        return origin.rpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<V> rpoplpush(K source, K destination) {
        return origin.rpoplpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source), RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public RedisFuture<Long> rpush(K key, V... values) {
        return origin.rpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public RedisFuture<Long> rpushx(K key, V... values) {
        return origin.rpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public <T> RedisFuture<T> eval(String script, ScriptOutputType type, K... keys) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> RedisFuture<T> eval(String script, ScriptOutputType type, K[] keys, V... values) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public <T> RedisFuture<T> evalsha(String digest, ScriptOutputType type, K... keys) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> RedisFuture<T> evalsha(String digest, ScriptOutputType type, K[] keys, V... values) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public RedisFuture<List<Boolean>> scriptExists(String... digests) {
        return origin.scriptExists(digests);
    }

    @Override
    public String digest(V script) {
        return origin.digest(script);
    }

    @Override
    public RedisFuture<String> bgrewriteaof() {
        return origin.bgrewriteaof();
    }

    @Override
    public RedisFuture<String> bgsave() {
        return origin.bgsave();
    }

    @Override
    public RedisFuture<K> clientGetname() {
        return origin.clientGetname();
    }

    @Override
    public RedisFuture<String> clientKill(String addr) {
        return origin.clientKill(addr);
    }

    @Override
    public RedisFuture<Long> clientKill(KillArgs killArgs) {
        return origin.clientKill(killArgs);
    }

    @Override
    public RedisFuture<Long> clientUnblock(long id, UnblockType type) {
        return origin.clientUnblock(id, type);
    }

    @Override
    public RedisFuture<String> clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public RedisFuture<String> clientList() {
        return origin.clientList();
    }

    @Override
    public RedisFuture<Long> clientId() {
        return origin.clientId();
    }

    @Override
    public RedisFuture<List<Object>> command() {
        return origin.command();
    }

    @Override
    public RedisFuture<List<Object>> commandInfo(String... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public RedisFuture<List<Object>> commandInfo(CommandType... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public RedisFuture<Long> commandCount() {
        return origin.commandCount();
    }

    @Override
    public RedisFuture<Map<String, String>> configGet(String parameter) {
        return origin.configGet(parameter);
    }

    @Override
    public RedisFuture<String> configResetstat() {
        return origin.configResetstat();
    }

    @Override
    public RedisFuture<String> configRewrite() {
        return origin.configRewrite();
    }

    @Override
    public RedisFuture<String> configSet(String parameter, String value) {
        return origin.configSet(parameter, value);
    }

    @Override
    public RedisFuture<String> debugCrashAndRecover(Long delay) {
        return origin.debugCrashAndRecover(delay);
    }

    @Override
    public RedisFuture<String> debugHtstats(int db) {
        return origin.debugHtstats(db);
    }

    @Override
    public RedisFuture<String> debugObject(K key) {
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
    public RedisFuture<String> debugReload() {
        return origin.debugReload();
    }

    @Override
    public RedisFuture<String> debugRestart(Long delay) {
        return origin.debugRestart(delay);
    }

    @Override
    public RedisFuture<String> debugSdslen(K key) {
        return origin.debugSdslen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> flushallAsync() {
        return origin.flushallAsync();
    }

    @Override
    public RedisFuture<String> flushdbAsync() {
        return origin.flushdbAsync();
    }

    @Override
    public RedisFuture<String> info() {
        return origin.info();
    }

    @Override
    public RedisFuture<String> info(String section) {
        return origin.info(section);
    }

    @Override
    public RedisFuture<Date> lastsave() {
        return origin.lastsave();
    }

    @Override
    public RedisFuture<Long> memoryUsage(K key) {
        return origin.memoryUsage(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> save() {
        return origin.save();
    }

    @Override
    public RedisFuture<String> slaveof(String host, int port) {
        return origin.slaveof(host, port);
    }

    @Override
    public RedisFuture<String> slaveofNoOne() {
        return origin.slaveofNoOne();
    }

    @Override
    public RedisFuture<List<Object>> slowlogGet() {
        return origin.slowlogGet();
    }

    @Override
    public RedisFuture<List<Object>> slowlogGet(int count) {
        return origin.slowlogGet(count);
    }

    @Override
    public RedisFuture<Long> slowlogLen() {
        return origin.slowlogLen();
    }

    @Override
    public RedisFuture<String> slowlogReset() {
        return origin.slowlogReset();
    }

    @Override
    public RedisFuture<List<V>> time() {
        return origin.time();
    }

    @Override
    public RedisFuture<Long> sadd(K key, V... members) {
        return origin.sadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public RedisFuture<Long> scard(K key) {
        return origin.scard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Set<V>> sdiff(K... keys) {
        return origin.sdiff(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sdiff(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sdiff(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sdiffstore(K destination, K... keys) {
        return origin.sdiffstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination),
                RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Set<V>> sinter(K... keys) {
        return origin.sinter(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sinter(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sinter(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sinterstore(K destination, K... keys) {
        return origin.sinterstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination),
                RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Boolean> sismember(K key, V member) {
        return origin.sismember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public RedisFuture<Boolean> smove(K source, K destination, V member) {
        return origin.smove(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), member);
    }

    @Override
    public RedisFuture<Set<V>> smembers(K key) {
        return origin.smembers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> smembers(ValueStreamingChannel<V> channel, K key) {
        return origin.smembers(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<V> spop(K key) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Set<V>> spop(K key, long count) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<V> srandmember(K key) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<V>> srandmember(K key, long count) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<Long> srandmember(ValueStreamingChannel<V> channel, K key, long count) {
        return origin.srandmember(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<Long> srem(K key, V... members) {
        return origin.srem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public RedisFuture<Set<V>> sunion(K... keys) {
        return origin.sunion(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sunion(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sunion(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> sunionstore(K destination, K... keys) {
        return origin.sunionstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<ValueScanCursor<V>> sscan(K key) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<ValueScanCursor<V>> sscan(K key, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<ValueScanCursor<V>> sscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<ValueScanCursor<V>> sscan(K key, ScanCursor scanCursor) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<KeyValue<K, ScoredValue<V>>> bzpopmin(long timeout, K... keys) {
        return origin.bzpopmin(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<KeyValue<K, ScoredValue<V>>> bzpopmax(long timeout, K... keys) {
        return origin.bzpopmax(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> zadd(K key, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public RedisFuture<Long> zadd(K key, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoresAndValues);
    }

    @Override
    public RedisFuture<Long> zadd(K key, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoredValues);
    }

    @Override
    public RedisFuture<Long> zadd(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public RedisFuture<Long> zadd(K key, ZAddArgs zAddArgs, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoresAndValues);
    }

    @Override
    public RedisFuture<Long> zadd(K key, ZAddArgs zAddArgs, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoredValues);
    }

    @Override
    public RedisFuture<Double> zaddincr(K key, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public RedisFuture<Double> zaddincr(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public RedisFuture<Long> zcard(K key) {
        return origin.zcard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zcount(K key, double min, double max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zcount(K key, String min, String max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zcount(K key, Range<? extends Number> range) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<Double> zincrby(K key, double amount, V member) {
        return origin.zincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount, member);
    }

    @Override
    public RedisFuture<Long> zinterstore(K destination, K... keys) {
        return origin.zinterstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> zinterstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zinterstore(destination, storeArgs, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zlexcount(K key, String min, String max) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zlexcount(K key, Range<? extends V> range) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<ScoredValue<V>> zpopmin(K key) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zpopmin(K key, long count) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<ScoredValue<V>> zpopmax(K key) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zpopmax(K key, long count) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<List<V>> zrange(K key, long start, long stop) {
        return origin.zrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> zrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrangeWithScores(K key, long start, long stop) {
        return origin.zrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> zrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebylex(K key, String min, String max) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<List<V>> zrangebylex(K key, Range<? extends V> range) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebylex(K key, String min, String max, long offset, long count) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public RedisFuture<List<V>> zrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebyscore(K key, double min, double max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebyscore(K key, String min, String max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<List<V>> zrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebyscore(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrangebyscore(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public RedisFuture<List<V>> zrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public RedisFuture<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, double min, double max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, String min, String max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public RedisFuture<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public RedisFuture<Long> zrank(K key, V member) {
        return origin.zrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public RedisFuture<Long> zrem(K key, V... members) {
        return origin.zrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zremrangebylex(K key, String min, String max) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zremrangebylex(K key, Range<? extends V> range) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<Long> zremrangebyrank(K key, long start, long stop) {
        return origin.zremrangebyrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zremrangebyscore(K key, double min, double max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zremrangebyscore(K key, String min, String max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public RedisFuture<Long> zremrangebyscore(K key, Range<? extends Number> range) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<List<V>> zrevrange(K key, long start, long stop) {
        return origin.zrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> zrevrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrevrangeWithScores(K key, long start, long stop) {
        return origin.zrevrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<Long> zrevrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public RedisFuture<List<V>> zrevrangebylex(K key, Range<? extends V> range) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<List<V>> zrevrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrevrangebyscore(K key, double max, double min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrevrangebyscore(K key, String max, String min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public RedisFuture<List<V>> zrevrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrevrangebyscore(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<List<V>> zrevrangebyscore(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public RedisFuture<List<V>> zrevrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public RedisFuture<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public RedisFuture<List<ScoredValue<V>>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public RedisFuture<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public RedisFuture<Long> zrevrank(K key, V member) {
        return origin.zrevrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public RedisFuture<ScoredValueScanCursor<V>> zscan(K key) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<ScoredValueScanCursor<V>> zscan(K key, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<ScoredValueScanCursor<V>> zscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<ScoredValueScanCursor<V>> zscan(K key, ScanCursor scanCursor) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public RedisFuture<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public RedisFuture<Double> zscore(K key, V member) {
        return origin.zscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public RedisFuture<Long> zunionstore(K destination, K... keys) {
        return origin.zunionstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> zunionstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zunionstore(destination, storeArgs, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> xack(K key, K group, String... messageIds) {
        return origin.xack(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, messageIds);
    }

    @Override
    public RedisFuture<String> xadd(K key, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), body);
    }

    @Override
    public RedisFuture<String> xadd(K key, XAddArgs args, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, body);
    }

    @Override
    public RedisFuture<String> xadd(K key, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), keysAndValues);
    }

    @Override
    public RedisFuture<String> xadd(K key, XAddArgs args, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, keysAndValues);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xclaim(K key, Consumer<K> consumer, long minIdleTime, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, minIdleTime, messageIds);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xclaim(K key, Consumer<K> consumer, XClaimArgs args, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, args, messageIds);
    }

    @Override
    public RedisFuture<Long> xdel(K key, String... messageIds) {
        return origin.xdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), messageIds);
    }

    @Override
    public RedisFuture<String> xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupCreate(streamOffset, group);
    }

    @Override
    public RedisFuture<String> xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group, XGroupCreateArgs args) {
        return origin.xgroupCreate(streamOffset, group, args);
    }

    @Override
    public RedisFuture<Boolean> xgroupDelconsumer(K key, Consumer<K> consumer) {
        return origin.xgroupDelconsumer(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer);
    }

    @Override
    public RedisFuture<Boolean> xgroupDestroy(K key, K group) {
        return origin.xgroupDestroy(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public RedisFuture<String> xgroupSetid(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupSetid(streamOffset, group);
    }

    @Override
    public RedisFuture<List<Object>> xinfoStream(K key) {
        return origin.xinfoStream(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<Object>> xinfoGroups(K key) {
        return origin.xinfoGroups(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<Object>> xinfoConsumers(K key, K group) {
        return origin.xinfoConsumers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public RedisFuture<Long> xlen(K key) {
        return origin.xlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<Object>> xpending(K key, K group) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public RedisFuture<List<Object>> xpending(K key, K group, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, range, limit);
    }

    @Override
    public RedisFuture<List<Object>> xpending(K key, Consumer<K> consumer, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, range, limit);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xrange(K key, Range<String> range) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xrange(K key, Range<String> range, Limit limit) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xread(XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(streams);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xread(XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(args, streams);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xreadgroup(Consumer<K> consumer, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, streams);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xreadgroup(Consumer<K> consumer, XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, args, streams);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xrevrange(K key, Range<String> range) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public RedisFuture<List<StreamMessage<K, V>>> xrevrange(K key, Range<String> range, Limit limit) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public RedisFuture<Long> xtrim(K key, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public RedisFuture<Long> xtrim(K key, boolean approximateTrimming, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), approximateTrimming, count);
    }

    @Override
    public RedisFuture<Long> append(K key, V value) {
        return origin.append(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public RedisFuture<Long> bitcount(K key) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> bitcount(K key, long start, long end) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public RedisFuture<List<Long>> bitfield(K key, BitFieldArgs bitFieldArgs) {
        return origin.bitfield(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), bitFieldArgs);
    }

    @Override
    public RedisFuture<Long> bitpos(K key, boolean state) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state);
    }

    @Override
    public RedisFuture<Long> bitpos(K key, boolean state, long start) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start);
    }

    @Override
    public RedisFuture<Long> bitpos(K key, boolean state, long start, long end) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start, end);
    }

    @Override
    public RedisFuture<Long> bitopAnd(K destination, K... keys) {
        return origin.bitopAnd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> bitopNot(K destination, K source) {
        return origin.bitopNot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(source));
    }

    @Override
    public RedisFuture<Long> bitopOr(K destination, K... keys) {
        return origin.bitopOr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> bitopXor(K destination, K... keys) {
        return origin.bitopXor(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<Long> decr(K key) {
        return origin.decr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> decrby(K key, long amount) {
        return origin.decrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public RedisFuture<V> get(K key) {
        return origin.get(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> getbit(K key, long offset) {
        return origin.getbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset);
    }

    @Override
    public RedisFuture<V> getrange(K key, long start, long end) {
        return origin.getrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public RedisFuture<V> getset(K key, V value) {
        return origin.getset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public RedisFuture<Long> incr(K key) {
        return origin.incr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> incrby(K key, long amount) {
        return origin.incrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public RedisFuture<Double> incrbyfloat(K key, double amount) {
        return origin.incrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public RedisFuture<Long> mget(KeyValueStreamingChannel<K, V> channel, K... keys) {
        return origin.mget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public RedisFuture<String> set(K key, V value) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public RedisFuture<String> set(K key, V value, SetArgs setArgs) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, setArgs);
    }

    @Override
    public RedisFuture<Long> setbit(K key, long offset, int value) {
        return origin.setbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public RedisFuture<String> setex(K key, long seconds, V value) {
        return origin.setex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds, value);
    }

    @Override
    public RedisFuture<String> psetex(K key, long milliseconds, V value) {
        return origin.psetex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds, value);
    }

    @Override
    public RedisFuture<Boolean> setnx(K key, V value) {
        return origin.setnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public RedisFuture<Long> setrange(K key, long offset, V value) {
        return origin.setrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public RedisFuture<Long> strlen(K key) {
        return origin.strlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }
}
