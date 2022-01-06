package org.apache.skywalking.apm.plugin.lettuce.pt.v5.model;

import io.lettuce.core.KillArgs;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.async.RedisSentinelAsyncCommands;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.RedisToolManager;

import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisSentinelAsyncCommandsDelegate<K, V> implements RedisSentinelAsyncCommands<K, V> {
    private final RedisSentinelAsyncCommands<K, V> origin;

    public RedisSentinelAsyncCommandsDelegate(RedisSentinelAsyncCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public RedisFuture<SocketAddress> getMasterAddrByName(K key) {
        return origin.getMasterAddrByName(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<Map<K, V>>> masters() {
        return origin.masters();
    }

    @Override
    public RedisFuture<Map<K, V>> master(K key) {
        return origin.master(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<List<Map<K, V>>> slaves(K key) {
        return origin.slaves(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<Long> reset(K key) {
        return origin.reset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> failover(K key) {
        return origin.failover(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<String> monitor(K key, String ip, int port, int quorum) {
        return origin.monitor(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), ip, port, quorum);
    }

    @Override
    public RedisFuture<String> set(K key, String option, V value) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), option, value);
    }

    @Override
    public RedisFuture<String> remove(K key) {
        return origin.remove(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public RedisFuture<K> clientGetname() {
        return origin.clientGetname();
    }

    @Override
    public RedisFuture<String> clientSetname(K name) {
        return origin.clientSetname(name);
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
    public RedisFuture<String> clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public RedisFuture<String> clientList() {
        return origin.clientList();
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
    public RedisFuture<String> ping() {
        return origin.ping();
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
    public StatefulRedisSentinelConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }
}
