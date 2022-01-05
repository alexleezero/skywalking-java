package org.apache.skywalking.apm.plugin.lettuce.pt.v5.model;

import io.lettuce.core.KillArgs;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;

import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisSentinelCommandsDelegate<K, V> implements RedisSentinelCommands<K, V> {
    private final RedisSentinelCommands<K, V> origin;

    public RedisSentinelCommandsDelegate(RedisSentinelCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public SocketAddress getMasterAddrByName(K key) {
        return origin.getMasterAddrByName(key);
    }

    @Override
    public List<Map<K, V>> masters() {
        return origin.masters();
    }

    @Override
    public Map<K, V> master(K key) {
        return origin.master(key);
    }

    @Override
    public List<Map<K, V>> slaves(K key) {
        return origin.slaves(key);
    }

    @Override
    public Long reset(K key) {
        return origin.reset(key);
    }

    @Override
    public String failover(K key) {
        return origin.failover(key);
    }

    @Override
    public String monitor(K key, String ip, int port, int quorum) {
        return origin.monitor(key, ip, port, quorum);
    }

    @Override
    public String set(K key, String option, V value) {
        return origin.set(key, option, value);
    }

    @Override
    public String remove(K key) {
        return origin.remove(key);
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
    public String clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public String clientList() {
        return origin.clientList();
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
    public String ping() {
        return origin.ping();
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
    public StatefulRedisSentinelConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }
}
