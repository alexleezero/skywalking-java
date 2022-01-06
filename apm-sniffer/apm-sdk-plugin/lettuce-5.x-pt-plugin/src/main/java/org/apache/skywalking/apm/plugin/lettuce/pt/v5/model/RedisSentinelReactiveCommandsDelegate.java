package org.apache.skywalking.apm.plugin.lettuce.pt.v5.model;

import io.lettuce.core.KillArgs;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.reactive.RedisSentinelReactiveCommands;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.RedisToolManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.SocketAddress;
import java.util.Map;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisSentinelReactiveCommandsDelegate<K, V> implements RedisSentinelReactiveCommands<K, V> {
    private final RedisSentinelReactiveCommands<K, V> origin;

    public RedisSentinelReactiveCommandsDelegate(RedisSentinelReactiveCommands<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public Mono<SocketAddress> getMasterAddrByName(K key) {
        return origin.getMasterAddrByName(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<Map<K, V>> masters() {
        return origin.masters();
    }

    @Override
    public Mono<Map<K, V>> master(K key) {
        return origin.master(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Flux<Map<K, V>> slaves(K key) {
        return origin.slaves(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<Long> reset(K key) {
        return origin.reset(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> failover(K key) {
        return origin.failover(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<String> monitor(K key, String ip, int port, int quorum) {
        return origin.monitor(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), ip, port, quorum);
    }

    @Override
    public Mono<String> set(K key, String option, V value) {
        return origin.set(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key), option, value);
    }

    @Override
    public Mono<String> remove(K key) {
        return origin.remove(RedisToolManager.LETTUCE_TOOL.convertShadowKey(key));
    }

    @Override
    public Mono<K> clientGetname() {
        return origin.clientGetname();
    }

    @Override
    public Mono<String> clientSetname(K name) {
        return origin.clientSetname(name);
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
    public Mono<String> clientPause(long timeout) {
        return origin.clientPause(timeout);
    }

    @Override
    public Mono<String> clientList() {
        return origin.clientList();
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
    public Mono<String> ping() {
        return origin.ping();
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
    public StatefulRedisSentinelConnection<K, V> getStatefulConnection() {
        return origin.getStatefulConnection();
    }
}
