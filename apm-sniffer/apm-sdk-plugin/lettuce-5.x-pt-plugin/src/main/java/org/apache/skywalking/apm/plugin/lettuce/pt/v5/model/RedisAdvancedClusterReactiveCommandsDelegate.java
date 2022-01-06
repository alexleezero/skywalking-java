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
import io.lettuce.core.cluster.api.reactive.RedisAdvancedClusterReactiveCommands;
import io.lettuce.core.cluster.api.reactive.RedisClusterReactiveCommands;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.output.KeyStreamingChannel;
import io.lettuce.core.output.KeyValueStreamingChannel;
import io.lettuce.core.output.ScoredValueStreamingChannel;
import io.lettuce.core.output.ValueStreamingChannel;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.RedisToolManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisAdvancedClusterReactiveCommandsDelegate<K, V> implements RedisAdvancedClusterReactiveCommands<K, V> {
    private final RedisAdvancedClusterReactiveCommands<K, V> origin;

    public RedisAdvancedClusterReactiveCommandsDelegate(RedisAdvancedClusterReactiveCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public RedisClusterReactiveCommands<K, V> getConnection(String nodeId) {
        return origin.getConnection(nodeId);
    }

    @Override
    public RedisClusterReactiveCommands<K, V> getConnection(String host, int port) {
        return origin.getConnection(host, port);
    }

    @Override
    public StatefulRedisClusterConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }

    @Override
    public Mono<Long> del(K... keys) {
        return origin.del(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> unlink(K... keys) {
        return origin.unlink(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> exists(K... keys) {
        return origin.exists(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Flux<KeyValue<K, V>> mget(K... keys) {
        return origin.mget(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<String> mset(Map<K, V> map) {
        Map<K, V> newMap = new HashMap<>();
        map.forEach((key, value) -> newMap.put(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value));
        return origin.mset(newMap);
    }

    @Override
    public Mono<Boolean> msetnx(Map<K, V> map) {
        Map<K, V> newMap = new HashMap<>();
        map.forEach((key, value) -> newMap.put(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value));
        return origin.msetnx(newMap);
    }

    @Override
    public Mono<String> clientSetname(K name) {
        return origin.clientSetname(name);
    }

    @Override
    public Mono<String> flushall() {
        return origin.flushall();
    }

    @Override
    public Mono<String> flushdb() {
        return origin.flushdb();
    }

    @Override
    public Mono<Long> dbsize() {
        return origin.dbsize();
    }

    @Override
    public Flux<K> keys(K pattern) {
        return origin.keys(pattern);
    }

    @Override
    public Mono<Long> keys(KeyStreamingChannel<K> channel, K pattern) {
        return origin.keys(channel, pattern);
    }

    @Override
    public Mono<K> randomkey() {
        return origin.randomkey();
    }

    @Override
    public Mono<String> scriptFlush() {
        return origin.scriptFlush();
    }

    @Override
    public Mono<String> scriptKill() {
        return origin.scriptKill();
    }

    @Override
    public Mono<String> scriptLoad(V script) {
        return origin.scriptLoad(script);
    }

    @Override
    public Mono<Void> shutdown(boolean save) {
        return origin.shutdown(save);
    }

    @Override
    public Mono<KeyScanCursor<K>> scan() {
        return origin.scan();
    }

    @Override
    public Mono<KeyScanCursor<K>> scan(ScanArgs scanArgs) {
        return origin.scan(scanArgs);
    }

    @Override
    public Mono<KeyScanCursor<K>> scan(ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(scanCursor, scanArgs);
    }

    @Override
    public Mono<KeyScanCursor<K>> scan(ScanCursor scanCursor) {
        return origin.scan(scanCursor);
    }

    @Override
    public Mono<StreamScanCursor> scan(KeyStreamingChannel<K> channel) {
        return origin.scan(channel);
    }

    @Override
    public Mono<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanArgs scanArgs) {
        return origin.scan(channel, scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.scan(channel, scanCursor, scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor) {
        return origin.scan(channel, scanCursor);
    }

    @Override
    public Mono<Long> touch(K... keys) {
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
    public Mono<String> auth(String password) {
        return origin.auth(password);
    }

    @Override
    public Mono<String> clusterBumpepoch() {
        return origin.clusterBumpepoch();
    }

    @Override
    public Mono<String> clusterMeet(String ip, int port) {
        return origin.clusterMeet(ip, port);
    }

    @Override
    public Mono<String> clusterForget(String nodeId) {
        return origin.clusterForget(nodeId);
    }

    @Override
    public Mono<String> clusterAddSlots(int... slots) {
        return origin.clusterAddSlots(slots);
    }

    @Override
    public Mono<String> clusterDelSlots(int... slots) {
        return origin.clusterDelSlots(slots);
    }

    @Override
    public Mono<String> clusterSetSlotNode(int slot, String nodeId) {
        return origin.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public Mono<String> clusterSetSlotStable(int slot) {
        return origin.clusterSetSlotStable(slot);
    }

    @Override
    public Mono<String> clusterSetSlotMigrating(int slot, String nodeId) {
        return origin.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public Mono<String> clusterSetSlotImporting(int slot, String nodeId) {
        return origin.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public Mono<String> clusterInfo() {
        return origin.clusterInfo();
    }

    @Override
    public Mono<String> clusterMyId() {
        return origin.clusterMyId();
    }

    @Override
    public Mono<String> clusterNodes() {
        return origin.clusterNodes();
    }

    @Override
    public Flux<String> clusterSlaves(String nodeId) {
        return origin.clusterSlaves(nodeId);
    }

    @Override
    public Flux<K> clusterGetKeysInSlot(int slot, int count) {
        return origin.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public Mono<Long> clusterCountKeysInSlot(int slot) {
        return origin.clusterCountKeysInSlot(slot);
    }

    @Override
    public Mono<Long> clusterCountFailureReports(String nodeId) {
        return origin.clusterCountFailureReports(nodeId);
    }

    @Override
    public Mono<Long> clusterKeyslot(K key) {
        return origin.clusterKeyslot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> clusterSaveconfig() {
        return origin.clusterSaveconfig();
    }

    @Override
    public Mono<String> clusterSetConfigEpoch(long configEpoch) {
        return origin.clusterSetConfigEpoch(configEpoch);
    }

    @Override
    public Flux<Object> clusterSlots() {
        return origin.clusterSlots();
    }

    @Override
    public Mono<String> asking() {
        return origin.asking();
    }

    @Override
    public Mono<String> clusterReplicate(String nodeId) {
        return origin.clusterReplicate(nodeId);
    }

    @Override
    public Mono<String> clusterFailover(boolean force) {
        return origin.clusterFailover(force);
    }

    @Override
    public Mono<String> clusterReset(boolean hard) {
        return origin.clusterReset(hard);
    }

    @Override
    public Mono<String> clusterFlushslots() {
        return origin.clusterFlushslots();
    }

    @Override
    public Mono<String> readOnly() {
        return origin.readOnly();
    }

    @Override
    public Mono<String> readWrite() {
        return origin.readWrite();
    }

    @Override
    public Mono<Long> publish(K channel, V message) {
        return origin.publish(RedisToolManager.LETTUCE_TOOL.convertShadowKey(channel), message);
    }

    @Override
    public Flux<K> pubsubChannels() {
        return origin.pubsubChannels();
    }

    @Override
    public Flux<K> pubsubChannels(K channel) {
        return origin.pubsubChannels(RedisToolManager.LETTUCE_TOOL.convertShadowKey(channel));
    }

    @Override
    public Mono<Map<K, Long>> pubsubNumsub(K... channels) {
        return origin.pubsubNumsub(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(channels));
    }

    @Override
    public Mono<Long> pubsubNumpat() {
        return origin.pubsubNumpat();
    }

    @Override
    public Mono<V> echo(V msg) {
        return origin.echo(msg);
    }

    @Override
    public Flux<Object> role() {
        return origin.role();
    }

    @Override
    public Mono<String> ping() {
        return origin.ping();
    }

    @Override
    public Mono<String> quit() {
        return origin.quit();
    }

    @Override
    public Mono<Long> waitForReplication(int replicas, long timeout) {
        return origin.waitForReplication(replicas, timeout);
    }

    @Override
    public <T> Flux<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, ?> output) {
        return origin.dispatch(type, output);
    }

    @Override
    public <T> Flux<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, ?> output, CommandArgs<K, V> args) {
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
    public Mono<Long> geoadd(K key, double longitude, double latitude, V member) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, member);
    }

    @Override
    public Mono<Long> geoadd(K key, Object... lngLatMember) {
        return origin.geoadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), lngLatMember);
    }

    @Override
    public Flux<Value<String>> geohash(K key, V... members) {
        return origin.geohash(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Flux<V> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit);
    }

    @Override
    public Flux<GeoWithin<V>> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoArgs);
    }

    @Override
    public Mono<Long> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadius(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), longitude, latitude, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public Flux<V> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit);
    }

    @Override
    public Flux<GeoWithin<V>> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoArgs);
    }

    @Override
    public Mono<Long> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs) {
        return origin.georadiusbymember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member, distance, unit, geoRadiusStoreArgs);
    }

    @Override
    public Flux<Value<GeoCoordinates>> geopos(K key, V... members) {
        return origin.geopos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Mono<Double> geodist(K key, V from, V to, GeoArgs.Unit unit) {
        return origin.geodist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), from, to, unit);
    }

    @Override
    public Mono<Long> hdel(K key, K... fields) {
        return origin.hdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public Mono<Boolean> hexists(K key, K field) {
        return origin.hexists(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public Mono<V> hget(K key, K field) {
        return origin.hget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public Mono<Long> hincrby(K key, K field, long amount) {
        return origin.hincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public Mono<Double> hincrbyfloat(K key, K field, double amount) {
        return origin.hincrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, amount);
    }

    @Override
    public Mono<Map<K, V>> hgetall(K key) {
        return origin.hgetall(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> hgetall(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hgetall(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<K> hkeys(K key) {
        return origin.hkeys(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> hkeys(KeyStreamingChannel<K> channel, K key) {
        return origin.hkeys(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> hlen(K key) {
        return origin.hlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<KeyValue<K, V>> hmget(K key, K... fields) {
        return origin.hmget(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public Mono<Long> hmget(KeyValueStreamingChannel<K, V> channel, K key, K... fields) {
        return origin.hmget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), fields);
    }

    @Override
    public Mono<String> hmset(K key, Map<K, V> map) {
        return origin.hmset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public Mono<MapScanCursor<K, V>> hscan(K key) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<MapScanCursor<K, V>> hscan(K key, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<MapScanCursor<K, V>> hscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<MapScanCursor<K, V>> hscan(K key, ScanCursor scanCursor) {
        return origin.hscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> hscan(KeyValueStreamingChannel<K, V> channel, K key, ScanCursor scanCursor) {
        return origin.hscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<Boolean> hset(K key, K field, V value) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public Mono<Long> hset(K key, Map<K, V> map) {
        return origin.hset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), map);
    }

    @Override
    public Mono<Boolean> hsetnx(K key, K field, V value) {
        return origin.hsetnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field, value);
    }

    @Override
    public Mono<Long> hstrlen(K key, K field) {
        return origin.hstrlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), field);
    }

    @Override
    public Flux<V> hvals(K key) {
        return origin.hvals(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> hvals(ValueStreamingChannel<V> channel, K key) {
        return origin.hvals(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> pfadd(K key, V... values) {
        return origin.pfadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Mono<String> pfmerge(K destkey, K... sourcekeys) {
        return origin.pfmerge(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destkey),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(sourcekeys));
    }

    @Override
    public Mono<Long> pfcount(K... keys) {
        return origin.pfcount(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<byte[]> dump(K key) {
        return origin.dump(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Boolean> expire(K key, long seconds) {
        return origin.expire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds);
    }

    @Override
    public Mono<Boolean> expireat(K key, Date timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Mono<Boolean> expireat(K key, long timestamp) {
        return origin.expireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Mono<String> migrate(String host, int port, K key, int db, long timeout) {
        return origin.migrate(host, port, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db, timeout);
    }

    @Override
    public Mono<String> migrate(String host, int port, int db, long timeout, MigrateArgs<K> migrateArgs) {
        return origin.migrate(host, port, db, timeout, migrateArgs);
    }

    @Override
    public Mono<Boolean> move(K key, int db) {
        return origin.move(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), db);
    }

    @Override
    public Mono<String> objectEncoding(K key) {
        return origin.objectEncoding(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> objectIdletime(K key) {
        return origin.objectIdletime(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> objectRefcount(K key) {
        return origin.objectRefcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Boolean> persist(K key) {
        return origin.persist(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Boolean> pexpire(K key, long milliseconds) {
        return origin.pexpire(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds);
    }

    @Override
    public Mono<Boolean> pexpireat(K key, Date timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Mono<Boolean> pexpireat(K key, long timestamp) {
        return origin.pexpireat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), timestamp);
    }

    @Override
    public Mono<Long> pttl(K key) {
        return origin.pttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> rename(K key, K newKey) {
        return origin.rename(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(newKey));
    }

    @Override
    public Mono<Boolean> renamenx(K key, K newKey) {
        return origin.renamenx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(newKey));
    }

    @Override
    public Mono<String> restore(K key, long ttl, byte[] value) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), ttl, value);
    }

    @Override
    public Mono<String> restore(K key, byte[] value, RestoreArgs args) {
        return origin.restore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, args);
    }

    @Override
    public Flux<V> sort(K key) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> sort(ValueStreamingChannel<V> channel, K key) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<V> sort(K key, SortArgs sortArgs) {
        return origin.sort(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public Mono<Long> sort(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs) {
        return origin.sort(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs);
    }

    @Override
    public Mono<Long> sortStore(K key, SortArgs sortArgs, K destination) {
        return origin.sortStore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), sortArgs, destination);
    }

    @Override
    public Mono<Long> ttl(K key) {
        return origin.ttl(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> type(K key) {
        return origin.type(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<KeyValue<K, V>> blpop(long timeout, K... keys) {
        return origin.blpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<KeyValue<K, V>> brpop(long timeout, K... keys) {
        return origin.brpop(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<V> brpoplpush(long timeout, K source, K destination) {
        return origin.brpoplpush(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKey(source),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public Mono<V> lindex(K key, long index) {
        return origin.lindex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index);
    }

    @Override
    public Mono<Long> linsert(K key, boolean before, V pivot, V value) {
        return origin.linsert(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), before, pivot, value);
    }

    @Override
    public Mono<Long> llen(K key) {
        return origin.llen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<V> lpop(K key) {
        return origin.lpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> lpush(K key, V... values) {
        return origin.lpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Mono<Long> lpushx(K key, V... values) {
        return origin.lpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Flux<V> lrange(K key, long start, long stop) {
        return origin.lrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> lrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.lrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> lrem(K key, long count, V value) {
        return origin.lrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count, value);
    }

    @Override
    public Mono<String> lset(K key, long index, V value) {
        return origin.lset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), index, value);
    }

    @Override
    public Mono<String> ltrim(K key, long start, long stop) {
        return origin.ltrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<V> rpop(K key) {
        return origin.rpop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<V> rpoplpush(K source, K destination) {
        return origin.rpoplpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination));
    }

    @Override
    public Mono<Long> rpush(K key, V... values) {
        return origin.rpush(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public Mono<Long> rpushx(K key, V... values) {
        return origin.rpushx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), values);
    }

    @Override
    public <T> Flux<T> eval(String script, ScriptOutputType type, K... keys) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> Flux<T> eval(String script, ScriptOutputType type, K[] keys, V... values) {
        return origin.eval(script, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public <T> Flux<T> evalsha(String digest, ScriptOutputType type, K... keys) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public <T> Flux<T> evalsha(String digest, ScriptOutputType type, K[] keys, V... values) {
        return origin.evalsha(digest, type, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys), values);
    }

    @Override
    public Flux<Boolean> scriptExists(String... digests) {
        return origin.scriptExists(digests);
    }

    @Override
    public String digest(V script) {
        return origin.digest(script);
    }

    @Override
    public Mono<String> bgrewriteaof() {
        return origin.bgrewriteaof();
    }

    @Override
    public Mono<String> bgsave() {
        return origin.bgsave();
    }

    @Override
    public Mono<K> clientGetname() {
        return origin.clientGetname();
    }

    @Override
    public Mono<String> clientKill(String addr) {
        return origin.clientKill(addr);
    }

    @Override
    public Mono<Long> clientKill(KillArgs killArgs) {
        return origin.clientKill(killArgs);
    }

    @Override
    public Mono<Long> clientUnblock(long id, UnblockType type) {
        return origin.clientUnblock(id, type);
    }

    @Override
    public Mono<String> clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public Mono<String> clientList() {
        return origin.clientList();
    }

    @Override
    public Mono<Long> clientId() {
        return origin.clientId();
    }

    @Override
    public Flux<Object> command() {
        return origin.command();
    }

    @Override
    public Flux<Object> commandInfo(String... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public Flux<Object> commandInfo(CommandType... commands) {
        return origin.commandInfo(commands);
    }

    @Override
    public Mono<Long> commandCount() {
        return origin.commandCount();
    }

    @Override
    public Mono<Map<String, String>> configGet(String parameter) {
        return origin.configGet(parameter);
    }

    @Override
    public Mono<String> configResetstat() {
        return origin.configResetstat();
    }

    @Override
    public Mono<String> configRewrite() {
        return origin.configRewrite();
    }

    @Override
    public Mono<String> configSet(String parameter, String value) {
        return origin.configSet(parameter, value);
    }

    @Override
    public Mono<String> debugCrashAndRecover(Long delay) {
        return origin.debugCrashAndRecover(delay);
    }

    @Override
    public Mono<String> debugHtstats(int db) {
        return origin.debugHtstats(db);
    }

    @Override
    public Mono<String> debugObject(K key) {
        return origin.debugObject(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Void> debugOom() {
        return origin.debugOom();
    }

    @Override
    public Mono<Void> debugSegfault() {
        return origin.debugSegfault();
    }

    @Override
    public Mono<String> debugReload() {
        return origin.debugReload();
    }

    @Override
    public Mono<String> debugRestart(Long delay) {
        return origin.debugRestart(delay);
    }

    @Override
    public Mono<String> debugSdslen(K key) {
        return origin.debugSdslen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> flushallAsync() {
        return origin.flushallAsync();
    }

    @Override
    public Mono<String> flushdbAsync() {
        return origin.flushdbAsync();
    }

    @Override
    public Mono<String> info() {
        return origin.info();
    }

    @Override
    public Mono<String> info(String section) {
        return origin.info(section);
    }

    @Override
    public Mono<Date> lastsave() {
        return origin.lastsave();
    }

    @Override
    public Mono<Long> memoryUsage(K key) {
        return origin.memoryUsage(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> save() {
        return origin.save();
    }

    @Override
    public Mono<String> slaveof(String host, int port) {
        return origin.slaveof(host, port);
    }

    @Override
    public Mono<String> slaveofNoOne() {
        return origin.slaveofNoOne();
    }

    @Override
    public Flux<Object> slowlogGet() {
        return origin.slowlogGet();
    }

    @Override
    public Flux<Object> slowlogGet(int count) {
        return origin.slowlogGet(count);
    }

    @Override
    public Mono<Long> slowlogLen() {
        return origin.slowlogLen();
    }

    @Override
    public Mono<String> slowlogReset() {
        return origin.slowlogReset();
    }

    @Override
    public Flux<V> time() {
        return origin.time();
    }

    @Override
    public Mono<Long> sadd(K key, V... members) {
        return origin.sadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Mono<Long> scard(K key) {
        return origin.scard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<V> sdiff(K... keys) {
        return origin.sdiff(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sdiff(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sdiff(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sdiffstore(K destination, K... keys) {
        return origin.sdiffstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Flux<V> sinter(K... keys) {
        return origin.sinter(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sinter(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sinter(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sinterstore(K destination, K... keys) {
        return origin.sinterstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Boolean> sismember(K key, V member) {
        return origin.sismember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Mono<Boolean> smove(K source, K destination, V member) {
        return origin.smove(RedisToolManager.LETTUCE_TOOL.convertShadowKey(source),
                RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), member);
    }

    @Override
    public Flux<V> smembers(K key) {
        return origin.smembers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> smembers(ValueStreamingChannel<V> channel, K key) {
        return origin.smembers(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<V> spop(K key) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<V> spop(K key, long count) {
        return origin.spop(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Mono<V> srandmember(K key) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<V> srandmember(K key, long count) {
        return origin.srandmember(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Mono<Long> srandmember(ValueStreamingChannel<V> channel, K key, long count) {
        return origin.srandmember(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Mono<Long> srem(K key, V... members) {
        return origin.srem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    public Flux<V> sunion(K... keys) {
        return origin.sunion(RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sunion(ValueStreamingChannel<V> channel, K... keys) {
        return origin.sunion(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> sunionstore(K destination, K... keys) {
        return origin.sunionstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<ValueScanCursor<V>> sscan(K key) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<ValueScanCursor<V>> sscan(K key, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<ValueScanCursor<V>> sscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<ValueScanCursor<V>> sscan(K key, ScanCursor scanCursor) {
        return origin.sscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> sscan(ValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.sscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<KeyValue<K, ScoredValue<V>>> bzpopmin(long timeout, K... keys) {
        return origin.bzpopmin(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<KeyValue<K, ScoredValue<V>>> bzpopmax(long timeout, K... keys) {
        return origin.bzpopmax(timeout, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> zadd(K key, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public Mono<Long> zadd(K key, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoresAndValues);
    }

    @Override
    public Mono<Long> zadd(K key, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scoredValues);
    }

    @Override
    public Mono<Long> zadd(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public Mono<Long> zadd(K key, ZAddArgs zAddArgs, Object... scoresAndValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoresAndValues);
    }

    @Override
    public Mono<Long> zadd(K key, ZAddArgs zAddArgs, ScoredValue<V>... scoredValues) {
        return origin.zadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, scoredValues);
    }

    @Override
    public Mono<Double> zaddincr(K key, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), score, member);
    }

    @Override
    public Mono<Double> zaddincr(K key, ZAddArgs zAddArgs, double score, V member) {
        return origin.zaddincr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), zAddArgs, score, member);
    }

    @Override
    public Mono<Long> zcard(K key) {
        return origin.zcard(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    @Deprecated
    public Mono<Long> zcount(K key, double min, double max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Mono<Long> zcount(K key, String min, String max) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zcount(K key, Range<? extends Number> range) {
        return origin.zcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Mono<Double> zincrby(K key, double amount, V member) {
        return origin.zincrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount, member);
    }

    @Override
    public Mono<Long> zinterstore(K destination, K... keys) {
        return origin.zinterstore(destination, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> zinterstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zinterstore(destination, storeArgs, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    @Deprecated
    public Mono<Long> zlexcount(K key, String min, String max) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zlexcount(K key, Range<? extends V> range) {
        return origin.zlexcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Mono<ScoredValue<V>> zpopmin(K key) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<ScoredValue<V>> zpopmin(K key, long count) {
        return origin.zpopmin(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Mono<ScoredValue<V>> zpopmax(K key) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<ScoredValue<V>> zpopmax(K key, long count) {
        return origin.zpopmax(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Flux<V> zrange(K key, long start, long stop) {
        return origin.zrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> zrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Flux<ScoredValue<V>> zrangeWithScores(K key, long start, long stop) {
        return origin.zrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> zrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebylex(K key, String min, String max) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Flux<V> zrangebylex(K key, Range<? extends V> range) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebylex(K key, String min, String max, long offset, long count) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Flux<V> zrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebyscore(K key, double min, double max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebyscore(K key, String min, String max) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Flux<V> zrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebyscore(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Flux<V> zrangebyscore(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Flux<V> zrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Mono<Long> zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Flux<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    @Deprecated
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max, offset, count);
    }

    @Override
    public Mono<Long> zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Mono<Long> zrank(K key, V member) {
        return origin.zrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Mono<Long> zrem(K key, V... members) {
        return origin.zrem(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), members);
    }

    @Override
    @Deprecated
    public Mono<Long> zremrangebylex(K key, String min, String max) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zremrangebylex(K key, Range<? extends V> range) {
        return origin.zremrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Mono<Long> zremrangebyrank(K key, long start, long stop) {
        return origin.zremrangebyrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    @Deprecated
    public Mono<Long> zremrangebyscore(K key, double min, double max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    @Deprecated
    public Mono<Long> zremrangebyscore(K key, String min, String max) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), min, max);
    }

    @Override
    public Mono<Long> zremrangebyscore(K key, Range<? extends Number> range) {
        return origin.zremrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Flux<V> zrevrange(K key, long start, long stop) {
        return origin.zrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> zrevrange(ValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrange(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Flux<ScoredValue<V>> zrevrangeWithScores(K key, long start, long stop) {
        return origin.zrevrangeWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Mono<Long> zrevrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop) {
        return origin.zrevrangeWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, stop);
    }

    @Override
    public Flux<V> zrevrangebylex(K key, Range<? extends V> range) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Flux<V> zrevrangebylex(K key, Range<? extends V> range, Limit limit) {
        return origin.zrevrangebylex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Flux<V> zrevrangebyscore(K key, double max, double min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Flux<V> zrevrangebyscore(K key, String max, String min) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Flux<V> zrevrangebyscore(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Flux<V> zrevrangebyscore(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Flux<V> zrevrangebyscore(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public Flux<V> zrevrangebyscore(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscore(channel, key, max, min, offset, count);
    }

    @Override
    public Mono<Long> zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscore(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public Flux<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min);
    }

    @Override
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    @Deprecated
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), max, min, offset, count);
    }

    @Override
    public Mono<Long> zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit) {
        return origin.zrevrangebyscoreWithScores(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Mono<Long> zrevrank(K key, V member) {
        return origin.zrevrank(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Mono<ScoredValueScanCursor<V>> zscan(K key) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<ScoredValueScanCursor<V>> zscan(K key, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<ScoredValueScanCursor<V>> zscan(K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<ScoredValueScanCursor<V>> zscan(K key, ScanCursor scanCursor) {
        return origin.zscan(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor, scanArgs);
    }

    @Override
    public Mono<StreamScanCursor> zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor) {
        return origin.zscan(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), scanCursor);
    }

    @Override
    public Mono<Double> zscore(K key, V member) {
        return origin.zscore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), member);
    }

    @Override
    public Mono<Long> zunionstore(K destination, K... keys) {
        return origin.zunionstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> zunionstore(K destination, ZStoreArgs storeArgs, K... keys) {
        return origin.zunionstore(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), storeArgs, keys);
    }

    @Override
    public Mono<Long> xack(K key, K group, String... messageIds) {
        return origin.xack(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, messageIds);
    }

    @Override
    public Mono<String> xadd(K key, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), body);
    }

    @Override
    public Mono<String> xadd(K key, XAddArgs args, Map<K, V> body) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, body);
    }

    @Override
    public Mono<String> xadd(K key, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), keysAndValues);
    }

    @Override
    public Mono<String> xadd(K key, XAddArgs args, Object... keysAndValues) {
        return origin.xadd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), args, keysAndValues);
    }

    @Override
    public Flux<StreamMessage<K, V>> xclaim(K key, Consumer<K> consumer, long minIdleTime, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, minIdleTime, messageIds);
    }

    @Override
    public Flux<StreamMessage<K, V>> xclaim(K key, Consumer<K> consumer, XClaimArgs args, String... messageIds) {
        return origin.xclaim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, args, messageIds);
    }

    @Override
    public Mono<Long> xdel(K key, String... messageIds) {
        return origin.xdel(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), messageIds);
    }

    @Override
    public Mono<String> xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupCreate(streamOffset, group);
    }

    @Override
    public Mono<String> xgroupCreate(XReadArgs.StreamOffset<K> streamOffset, K group, XGroupCreateArgs args) {
        return origin.xgroupCreate(streamOffset, group, args);
    }

    @Override
    public Mono<Boolean> xgroupDelconsumer(K key, Consumer<K> consumer) {
        return origin.xgroupDelconsumer(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer);
    }

    @Override
    public Mono<Boolean> xgroupDestroy(K key, K group) {
        return origin.xgroupDestroy(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public Mono<String> xgroupSetid(XReadArgs.StreamOffset<K> streamOffset, K group) {
        return origin.xgroupSetid(streamOffset, group);
    }

    @Override
    public Flux<Object> xinfoStream(K key) {
        return origin.xinfoStream(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<Object> xinfoGroups(K key) {
        return origin.xinfoGroups(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<Object> xinfoConsumers(K key, K group) {
        return origin.xinfoConsumers(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public Mono<Long> xlen(K key) {
        return origin.xlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<Object> xpending(K key, K group) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group);
    }

    @Override
    public Flux<Object> xpending(K key, K group, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), group, range, limit);
    }

    @Override
    public Flux<Object> xpending(K key, Consumer<K> consumer, Range<String> range, Limit limit) {
        return origin.xpending(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), consumer, range, limit);
    }

    @Override
    public Flux<StreamMessage<K, V>> xrange(K key, Range<String> range) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Flux<StreamMessage<K, V>> xrange(K key, Range<String> range, Limit limit) {
        return origin.xrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Flux<StreamMessage<K, V>> xread(XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(streams);
    }

    @Override
    public Flux<StreamMessage<K, V>> xread(XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xread(args, streams);
    }

    @Override
    public Flux<StreamMessage<K, V>> xreadgroup(Consumer<K> consumer, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, streams);
    }

    @Override
    public Flux<StreamMessage<K, V>> xreadgroup(Consumer<K> consumer, XReadArgs args, XReadArgs.StreamOffset<K>... streams) {
        return origin.xreadgroup(consumer, args, streams);
    }

    @Override
    public Flux<StreamMessage<K, V>> xrevrange(K key, Range<String> range) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range);
    }

    @Override
    public Flux<StreamMessage<K, V>> xrevrange(K key, Range<String> range, Limit limit) {
        return origin.xrevrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), range, limit);
    }

    @Override
    public Mono<Long> xtrim(K key, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), count);
    }

    @Override
    public Mono<Long> xtrim(K key, boolean approximateTrimming, long count) {
        return origin.xtrim(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), approximateTrimming, count);
    }

    @Override
    public Mono<Long> append(K key, V value) {
        return origin.append(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Mono<Long> bitcount(K key) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> bitcount(K key, long start, long end) {
        return origin.bitcount(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public Flux<Value<Long>> bitfield(K key, BitFieldArgs bitFieldArgs) {
        return origin.bitfield(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), bitFieldArgs);
    }

    @Override
    public Mono<Long> bitpos(K key, boolean state) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state);
    }

    @Override
    public Mono<Long> bitpos(K key, boolean state, long start) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start);
    }

    @Override
    public Mono<Long> bitpos(K key, boolean state, long start, long end) {
        return origin.bitpos(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), state, start, end);
    }

    @Override
    public Mono<Long> bitopAnd(K destination, K... keys) {
        return origin.bitopAnd(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> bitopNot(K destination, K source) {
        return origin.bitopNot(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKey(source));
    }

    @Override
    public Mono<Long> bitopOr(K destination, K... keys) {
        return origin.bitopOr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> bitopXor(K destination, K... keys) {
        return origin.bitopXor(RedisToolManager.LETTUCE_TOOL.convertShadowKey(destination), RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<Long> decr(K key) {
        return origin.decr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> decrby(K key, long amount) {
        return origin.decrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public Mono<V> get(K key) {
        return origin.get(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> getbit(K key, long offset) {
        return origin.getbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset);
    }

    @Override
    public Mono<V> getrange(K key, long start, long end) {
        return origin.getrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), start, end);
    }

    @Override
    public Mono<V> getset(K key, V value) {
        return origin.getset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Mono<Long> incr(K key) {
        return origin.incr(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> incrby(K key, long amount) {
        return origin.incrby(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public Mono<Double> incrbyfloat(K key, double amount) {
        return origin.incrbyfloat(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), amount);
    }

    @Override
    public Mono<Long> mget(KeyValueStreamingChannel<K, V> channel, K... keys) {
        return origin.mget(channel, RedisToolManager.LETTUCE_TOOL.convertShadowKeys(keys));
    }

    @Override
    public Mono<String> set(K key, V value) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Mono<String> set(K key, V value, SetArgs setArgs) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value, setArgs);
    }

    @Override
    public Mono<Long> setbit(K key, long offset, int value) {
        return origin.setbit(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public Mono<String> setex(K key, long seconds, V value) {
        return origin.setex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), seconds, value);
    }

    @Override
    public Mono<String> psetex(K key, long milliseconds, V value) {
        return origin.psetex(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), milliseconds, value);
    }

    @Override
    public Mono<Boolean> setnx(K key, V value) {
        return origin.setnx(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), value);
    }

    @Override
    public Mono<Long> setrange(K key, long offset, V value) {
        return origin.setrange(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), offset, value);
    }

    @Override
    public Mono<Long> strlen(K key) {
        return origin.strlen(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }
}
