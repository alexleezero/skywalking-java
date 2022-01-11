package org.apache.skywalking.apm.plugin.redisson.pt.model;

import org.apache.skywalking.apm.plugin.redisson.pt.common.RedisToolManager;
import org.redisson.api.BatchOptions;
import org.redisson.api.ClusterNode;
import org.redisson.api.MapOptions;
import org.redisson.api.Node;
import org.redisson.api.NodesGroup;
import org.redisson.api.RAtomicDoubleRx;
import org.redisson.api.RAtomicLongRx;
import org.redisson.api.RBatchRx;
import org.redisson.api.RBinaryStreamRx;
import org.redisson.api.RBitSetRx;
import org.redisson.api.RBlockingDequeRx;
import org.redisson.api.RBlockingQueueRx;
import org.redisson.api.RBucketRx;
import org.redisson.api.RBucketsRx;
import org.redisson.api.RCountDownLatchRx;
import org.redisson.api.RDequeRx;
import org.redisson.api.RGeoRx;
import org.redisson.api.RHyperLogLogRx;
import org.redisson.api.RIdGeneratorRx;
import org.redisson.api.RKeysRx;
import org.redisson.api.RLexSortedSetRx;
import org.redisson.api.RListMultimapCacheRx;
import org.redisson.api.RListMultimapRx;
import org.redisson.api.RListRx;
import org.redisson.api.RLock;
import org.redisson.api.RLockRx;
import org.redisson.api.RMapCacheRx;
import org.redisson.api.RMapRx;
import org.redisson.api.RPatternTopicRx;
import org.redisson.api.RPermitExpirableSemaphoreRx;
import org.redisson.api.RQueueRx;
import org.redisson.api.RRateLimiterRx;
import org.redisson.api.RReadWriteLockRx;
import org.redisson.api.RReliableTopicRx;
import org.redisson.api.RRemoteService;
import org.redisson.api.RRingBufferRx;
import org.redisson.api.RScoredSortedSetRx;
import org.redisson.api.RScriptRx;
import org.redisson.api.RSemaphoreRx;
import org.redisson.api.RSetCacheRx;
import org.redisson.api.RSetMultimapCacheRx;
import org.redisson.api.RSetMultimapRx;
import org.redisson.api.RSetRx;
import org.redisson.api.RStreamRx;
import org.redisson.api.RTimeSeriesRx;
import org.redisson.api.RTopicRx;
import org.redisson.api.RTransactionRx;
import org.redisson.api.RTransferQueueRx;
import org.redisson.api.RedissonRxClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class ShadowRedissonRxClient implements RedissonRxClient {

	private final RedissonRxClient origin;

	public ShadowRedissonRxClient(RedissonRxClient origin) {
		this.origin = origin;
	}

	@Override
	public <V> RTimeSeriesRx<V> getTimeSeries(String name) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTimeSeriesRx<V> getTimeSeries(String name, Codec codec) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RStreamRx<K, V> getStream(String name) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RStreamRx<K, V> getStream(String name, Codec codec) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RGeoRx<V> getGeo(String name) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RGeoRx<V> getGeo(String name, Codec codec) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RRateLimiterRx getRateLimiter(String name) {
		return origin.getRateLimiter(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RBinaryStreamRx getBinaryStream(String name) {
		return origin.getBinaryStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RSemaphoreRx getSemaphore(String name) {
		return origin.getSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RPermitExpirableSemaphoreRx getPermitExpirableSemaphore(String name) {
		return origin.getPermitExpirableSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReadWriteLockRx getReadWriteLock(String name) {
		return origin.getReadWriteLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockRx getFairLock(String name) {
		return origin.getFairLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockRx getLock(String name) {
		return origin.getLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLockRx getMultiLock(RLock... locks) {
		return origin.getMultiLock(locks);
	}

	@Override
	@Deprecated
	public RLockRx getRedLock(RLock... locks) {
		return origin.getRedLock(locks);
	}

	@Override
	public RCountDownLatchRx getCountDownLatch(String name) {
		return origin.getCountDownLatch(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetCacheRx<V> getSetCache(String name) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetCacheRx<V> getSetCache(String name, Codec codec) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCacheRx<K, V> getMapCache(String name, Codec codec) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCacheRx<K, V> getMapCache(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <K, V> RMapCacheRx<K, V> getMapCache(String name) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMapCacheRx<K, V> getMapCache(String name, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <V> RBucketRx<V> getBucket(String name) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBucketRx<V> getBucket(String name, Codec codec) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RBucketsRx getBuckets() {
		return origin.getBuckets();
	}

	@Override
	public RBucketsRx getBuckets(Codec codec) {
		return origin.getBuckets(codec);
	}

	@Override
	public <V> RHyperLogLogRx<V> getHyperLogLog(String name) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RHyperLogLogRx<V> getHyperLogLog(String name, Codec codec) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RIdGeneratorRx getIdGenerator(String name) {
		return origin.getIdGenerator(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RListRx<V> getList(String name) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RListRx<V> getList(String name, Codec codec) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RListMultimapRx<K, V> getListMultimap(String name) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RListMultimapRx<K, V> getListMultimap(String name, Codec codec) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RListMultimapCacheRx<K, V> getListMultimapCache(String name) {
		return origin.getListMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RListMultimapCacheRx<K, V> getListMultimapCache(String name, Codec codec) {
		return origin.getListMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RSetMultimapRx<K, V> getSetMultimap(String name) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RSetMultimapRx<K, V> getSetMultimap(String name, Codec codec) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RSetMultimapCacheRx<K, V> getSetMultimapCache(String name) {
		return origin.getSetMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RSetMultimapCacheRx<K, V> getSetMultimapCache(String name, Codec codec) {
		return origin.getSetMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapRx<K, V> getMap(String name) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMapRx<K, V> getMap(String name, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <K, V> RMapRx<K, V> getMap(String name, Codec codec) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapRx<K, V> getMap(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <V> RSetRx<V> getSet(String name) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetRx<V> getSet(String name, Codec codec) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RScoredSortedSetRx<V> getScoredSortedSet(String name) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RScoredSortedSetRx<V> getScoredSortedSet(String name, Codec codec) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RLexSortedSetRx getLexSortedSet(String name) {
		return origin.getLexSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopicRx getTopic(String name) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopicRx getTopic(String name, Codec codec) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RReliableTopicRx getReliableTopic(String name) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReliableTopicRx getReliableTopic(String name, Codec codec) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RPatternTopicRx getPatternTopic(String pattern) {
		return origin.getPatternTopic(pattern);
	}

	@Override
	public RPatternTopicRx getPatternTopic(String pattern, Codec codec) {
		return origin.getPatternTopic(pattern, codec);
	}

	@Override
	public <V> RQueueRx<V> getQueue(String name) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RQueueRx<V> getQueue(String name, Codec codec) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RRingBufferRx<V> getRingBuffer(String name) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RRingBufferRx<V> getRingBuffer(String name, Codec codec) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingQueueRx<V> getBlockingQueue(String name) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingQueueRx<V> getBlockingQueue(String name, Codec codec) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingDequeRx<V> getBlockingDeque(String name) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingDequeRx<V> getBlockingDeque(String name, Codec codec) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RTransferQueueRx<V> getTransferQueue(String name) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTransferQueueRx<V> getTransferQueue(String name, Codec codec) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RDequeRx<V> getDeque(String name) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RDequeRx<V> getDeque(String name, Codec codec) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RAtomicLongRx getAtomicLong(String name) {
		return origin.getAtomicLong(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RAtomicDoubleRx getAtomicDouble(String name) {
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
	public RBitSetRx getBitSet(String name) {
		return origin.getBitSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RScriptRx getScript() {
		return origin.getScript();
	}

	@Override
	public RScriptRx getScript(Codec codec) {
		return origin.getScript(codec);
	}

	@Override
	public RTransactionRx createTransaction(TransactionOptions options) {
		return origin.createTransaction(options);
	}

	@Override
	public RBatchRx createBatch(BatchOptions options) {
		return origin.createBatch(options);
	}

	@Override
	public RBatchRx createBatch() {
		return origin.createBatch();
	}

	@Override
	public RKeysRx getKeys() {
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
