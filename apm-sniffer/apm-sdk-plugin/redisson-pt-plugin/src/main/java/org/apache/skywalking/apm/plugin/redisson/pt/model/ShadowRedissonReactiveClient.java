package org.apache.skywalking.apm.plugin.redisson.pt.model;

import org.apache.skywalking.apm.plugin.redisson.pt.common.RedisToolManager;
import org.redisson.api.BatchOptions;
import org.redisson.api.ClusterNode;
import org.redisson.api.MapOptions;
import org.redisson.api.Node;
import org.redisson.api.NodesGroup;
import org.redisson.api.RAtomicDoubleReactive;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RBatchReactive;
import org.redisson.api.RBinaryStreamReactive;
import org.redisson.api.RBitSetReactive;
import org.redisson.api.RBlockingDequeReactive;
import org.redisson.api.RBlockingQueueReactive;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RBucketsReactive;
import org.redisson.api.RCountDownLatchReactive;
import org.redisson.api.RDequeReactive;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RHyperLogLogReactive;
import org.redisson.api.RIdGeneratorReactive;
import org.redisson.api.RKeysReactive;
import org.redisson.api.RLexSortedSetReactive;
import org.redisson.api.RListMultimapReactive;
import org.redisson.api.RListReactive;
import org.redisson.api.RLock;
import org.redisson.api.RLockReactive;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RPatternTopicReactive;
import org.redisson.api.RPermitExpirableSemaphoreReactive;
import org.redisson.api.RQueueReactive;
import org.redisson.api.RRateLimiterReactive;
import org.redisson.api.RReadWriteLockReactive;
import org.redisson.api.RReliableTopicReactive;
import org.redisson.api.RRemoteService;
import org.redisson.api.RRingBufferReactive;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.api.RScriptReactive;
import org.redisson.api.RSemaphoreReactive;
import org.redisson.api.RSetCacheReactive;
import org.redisson.api.RSetMultimapReactive;
import org.redisson.api.RSetReactive;
import org.redisson.api.RStreamReactive;
import org.redisson.api.RTimeSeriesReactive;
import org.redisson.api.RTopicReactive;
import org.redisson.api.RTransactionReactive;
import org.redisson.api.RTransferQueueReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;

import java.util.List;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class ShadowRedissonReactiveClient implements RedissonReactiveClient {
	private RedissonReactiveClient origin;

	public ShadowRedissonReactiveClient(RedissonReactiveClient origin) {
		this.origin = origin;
	}

	@Override
	public <V> RTimeSeriesReactive<V> getTimeSeries(String name) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTimeSeriesReactive<V> getTimeSeries(String name, Codec codec) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RStreamReactive<K, V> getStream(String name) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RStreamReactive<K, V> getStream(String name, Codec codec) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RGeoReactive<V> getGeo(String name) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RGeoReactive<V> getGeo(String name, Codec codec) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RRateLimiterReactive getRateLimiter(String name) {
		return origin.getRateLimiter(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RBinaryStreamReactive getBinaryStream(String name) {
		return origin.getBinaryStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RSemaphoreReactive getSemaphore(String name) {
		return origin.getSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RPermitExpirableSemaphoreReactive getPermitExpirableSemaphore(String name) {
		return origin.getPermitExpirableSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReadWriteLockReactive getReadWriteLock(String name) {
		return origin.getReadWriteLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockReactive getFairLock(String name) {
		return origin.getFairLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockReactive getLock(String name) {
		return origin.getLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockReactive getMultiLock(RLock... locks) {
		return origin.getMultiLock(locks);
	}

	@Override
	@Deprecated
	public RLockReactive getRedLock(RLock... locks) {
		return origin.getRedLock(locks);
	}

	@Override
	public RCountDownLatchReactive getCountDownLatch(String name) {
		return origin.getCountDownLatch(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetCacheReactive<V> getSetCache(String name) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetCacheReactive<V> getSetCache(String name, Codec codec) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <K, V> RMapCacheReactive<K, V> getMapCache(String name) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMapCacheReactive<K, V> getMapCache(String name, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <V> RBucketReactive<V> getBucket(String name) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBucketReactive<V> getBucket(String name, Codec codec) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RBucketsReactive getBuckets() {
		return origin.getBuckets();
	}

	@Override
	public RBucketsReactive getBuckets(Codec codec) {
		return origin.getBuckets(codec);
	}

	@Override
	public <V> List<RBucketReactive<V>> findBuckets(String pattern) {
		return origin.findBuckets(pattern);
	}

	@Override
	public <V> RHyperLogLogReactive<V> getHyperLogLog(String name) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RHyperLogLogReactive<V> getHyperLogLog(String name, Codec codec) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RIdGeneratorReactive getIdGenerator(String name) {
		return origin.getIdGenerator(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RListReactive<V> getList(String name) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RListReactive<V> getList(String name, Codec codec) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RListMultimapReactive<K, V> getListMultimap(String name) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RListMultimapReactive<K, V> getListMultimap(String name, Codec codec) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RSetMultimapReactive<K, V> getSetMultimap(String name) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RSetMultimapReactive<K, V> getSetMultimap(String name, Codec codec) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapReactive<K, V> getMap(String name) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMapReactive<K, V> getMap(String name, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <K, V> RMapReactive<K, V> getMap(String name, Codec codec) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapReactive<K, V> getMap(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <V> RSetReactive<V> getSet(String name) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetReactive<V> getSet(String name, Codec codec) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name, Codec codec) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RLexSortedSetReactive getLexSortedSet(String name) {
		return origin.getLexSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopicReactive getTopic(String name) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopicReactive getTopic(String name, Codec codec) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RReliableTopicReactive getReliableTopic(String name) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReliableTopicReactive getReliableTopic(String name, Codec codec) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RPatternTopicReactive getPatternTopic(String pattern) {
		return origin.getPatternTopic(pattern);
	}

	@Override
	public RPatternTopicReactive getPatternTopic(String pattern, Codec codec) {
		return origin.getPatternTopic(pattern, codec);
	}

	@Override
	public <V> RQueueReactive<V> getQueue(String name) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RQueueReactive<V> getQueue(String name, Codec codec) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RRingBufferReactive<V> getRingBuffer(String name) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RRingBufferReactive<V> getRingBuffer(String name, Codec codec) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingQueueReactive<V> getBlockingQueue(String name) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingQueueReactive<V> getBlockingQueue(String name, Codec codec) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingDequeReactive<V> getBlockingDeque(String name) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingDequeReactive<V> getBlockingDeque(String name, Codec codec) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RTransferQueueReactive<V> getTransferQueue(String name) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTransferQueueReactive<V> getTransferQueue(String name, Codec codec) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RDequeReactive<V> getDeque(String name) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RDequeReactive<V> getDeque(String name, Codec codec) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RAtomicLongReactive getAtomicLong(String name) {
		return origin.getAtomicLong(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RAtomicDoubleReactive getAtomicDouble(String name) {
		return origin.getAtomicDouble(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RRemoteService getRemoteService() {
		return origin.getRemoteService();
	}

	@Override
	public RRemoteService getRemoteService(Codec codec) {
		return origin.getRemoteService(codec);
	}

	@Override
	public RRemoteService getRemoteService(String name) {
		return origin.getRemoteService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RRemoteService getRemoteService(String name, Codec codec) {
		return origin.getRemoteService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RBitSetReactive getBitSet(String name) {
		return origin.getBitSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RScriptReactive getScript() {
		return origin.getScript();
	}

	@Override
	public RScriptReactive getScript(Codec codec) {
		return origin.getScript(codec);
	}

	@Override
	public RTransactionReactive createTransaction(TransactionOptions options) {
		return origin.createTransaction(options);
	}

	@Override
	public RBatchReactive createBatch(BatchOptions options) {
		return origin.createBatch(options);
	}

	@Override
	public RBatchReactive createBatch() {
		return origin.createBatch();
	}

	@Override
	public RKeysReactive getKeys() {
		return origin.getKeys();
	}

	@Override
	public void shutdown() {
		origin.shutdown();
	}

	@Override
	public Config getConfig() {
		return origin.getConfig();
	}

	@Override
	public NodesGroup<Node> getNodesGroup() {
		return origin.getNodesGroup();
	}

	@Override
	public NodesGroup<ClusterNode> getClusterNodesGroup() {
		return origin.getClusterNodesGroup();
	}

	@Override
	public boolean isShutdown() {
		return origin.isShutdown();
	}

	@Override
	public boolean isShuttingDown() {
		return origin.isShuttingDown();
	}

	@Override
	public String getId() {
		return origin.getId();
	}
}
