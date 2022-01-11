package org.apache.skywalking.apm.plugin.redisson.pt.model;

import org.apache.skywalking.apm.plugin.redisson.pt.common.RedisToolManager;
import org.redisson.api.BatchOptions;
import org.redisson.api.ClusterNodesGroup;
import org.redisson.api.ExecutorOptions;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.MapOptions;
import org.redisson.api.Node;
import org.redisson.api.NodesGroup;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBatch;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RBitSet;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RDeque;
import org.redisson.api.RDoubleAdder;
import org.redisson.api.RGeo;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RKeys;
import org.redisson.api.RLexSortedSet;
import org.redisson.api.RList;
import org.redisson.api.RListMultimap;
import org.redisson.api.RListMultimapCache;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RLock;
import org.redisson.api.RLongAdder;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RPriorityBlockingDeque;
import org.redisson.api.RPriorityBlockingQueue;
import org.redisson.api.RPriorityDeque;
import org.redisson.api.RPriorityQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RReliableTopic;
import org.redisson.api.RRemoteService;
import org.redisson.api.RRingBuffer;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RScript;
import org.redisson.api.RSemaphore;
import org.redisson.api.RSet;
import org.redisson.api.RSetCache;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RSetMultimapCache;
import org.redisson.api.RSortedSet;
import org.redisson.api.RStream;
import org.redisson.api.RTimeSeries;
import org.redisson.api.RTopic;
import org.redisson.api.RTransaction;
import org.redisson.api.RTransferQueue;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.redisson.api.redisnode.BaseRedisNodes;
import org.redisson.api.redisnode.RedisNodes;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class ShadowRedissonClient implements RedissonClient {
	private final RedissonClient origin;

	public ShadowRedissonClient(RedissonClient origin) {
		this.origin = origin;
	}

	@Override
	public <V> RTimeSeries<V> getTimeSeries(String name) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTimeSeries<V> getTimeSeries(String name, Codec codec) {
		return origin.getTimeSeries(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RStream<K, V> getStream(String name) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RStream<K, V> getStream(String name, Codec codec) {
		return origin.getStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RRateLimiter getRateLimiter(String name) {
		return origin.getRateLimiter(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RBinaryStream getBinaryStream(String name) {
		return origin.getBinaryStream(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RGeo<V> getGeo(String name) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RGeo<V> getGeo(String name, Codec codec) {
		return origin.getGeo(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RSetCache<V> getSetCache(String name) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSetCache<V> getSetCache(String name, Codec codec) {
		return origin.getSetCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCache<K, V> getMapCache(String name, Codec codec) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMapCache<K, V> getMapCache(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <K, V> RMapCache<K, V> getMapCache(String name) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMapCache<K, V> getMapCache(String name, MapOptions<K, V> options) {
		return origin.getMapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <V> RBucket<V> getBucket(String name) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBucket<V> getBucket(String name, Codec codec) {
		return origin.getBucket(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RBuckets getBuckets() {
		return origin.getBuckets();
	}

	@Override
	public RBuckets getBuckets(Codec codec) {
		return origin.getBuckets(codec);
	}

	@Override
	public <V> RHyperLogLog<V> getHyperLogLog(String name) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RHyperLogLog<V> getHyperLogLog(String name, Codec codec) {
		return origin.getHyperLogLog(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RList<V> getList(String name) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RList<V> getList(String name, Codec codec) {
		return origin.getList(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RListMultimap<K, V> getListMultimap(String name) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RListMultimap<K, V> getListMultimap(String name, Codec codec) {
		return origin.getListMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RListMultimapCache<K, V> getListMultimapCache(String name) {
		return origin.getListMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RListMultimapCache<K, V> getListMultimapCache(String name, Codec codec) {
		return origin.getListMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, LocalCachedMapOptions<K, V> options) {
		return origin.getLocalCachedMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, Codec codec, LocalCachedMapOptions<K, V> options) {
		return origin.getLocalCachedMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <K, V> RMap<K, V> getMap(String name) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RMap<K, V> getMap(String name, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public <K, V> RMap<K, V> getMap(String name, Codec codec) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RMap<K, V> getMap(String name, Codec codec, MapOptions<K, V> options) {
		return origin.getMap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
	}

	@Override
	public <K, V> RSetMultimap<K, V> getSetMultimap(String name) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RSetMultimap<K, V> getSetMultimap(String name, Codec codec) {
		return origin.getSetMultimap(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name) {
		return origin.getSetMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name, Codec codec) {
		return origin.getSetMultimapCache(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RSemaphore getSemaphore(String name) {
		return origin.getSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RPermitExpirableSemaphore getPermitExpirableSemaphore(String name) {
		return origin.getPermitExpirableSemaphore(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLock getLock(String name) {
		return origin.getLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLock getMultiLock(RLock... locks) {
		return origin.getMultiLock(locks);
	}

	@Override
	@Deprecated
	public RLock getRedLock(RLock... locks) {
		return origin.getRedLock(locks);
	}

	@Override
	public RLock getFairLock(String name) {
		return origin.getFairLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReadWriteLock getReadWriteLock(String name) {
		return origin.getReadWriteLock(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSet<V> getSet(String name) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSet<V> getSet(String name, Codec codec) {
		return origin.getSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RSortedSet<V> getSortedSet(String name) {
		return origin.getSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RSortedSet<V> getSortedSet(String name, Codec codec) {
		return origin.getSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RScoredSortedSet<V> getScoredSortedSet(String name) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RScoredSortedSet<V> getScoredSortedSet(String name, Codec codec) {
		return origin.getScoredSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RLexSortedSet getLexSortedSet(String name) {
		return origin.getLexSortedSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopic getTopic(String name) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RTopic getTopic(String name, Codec codec) {
		return origin.getTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RReliableTopic getReliableTopic(String name) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RReliableTopic getReliableTopic(String name, Codec codec) {
		return origin.getReliableTopic(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RPatternTopic getPatternTopic(String pattern) {
		return origin.getPatternTopic(pattern);
	}

	@Override
	public RPatternTopic getPatternTopic(String pattern, Codec codec) {
		return origin.getPatternTopic(pattern, codec);
	}

	@Override
	public <V> RQueue<V> getQueue(String name) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTransferQueue<V> getTransferQueue(String name) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RTransferQueue<V> getTransferQueue(String name, Codec codec) {
		return origin.getTransferQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RDelayedQueue<V> getDelayedQueue(RQueue<V> destinationQueue) {
		return origin.getDelayedQueue(destinationQueue);
	}

	@Override
	public <V> RQueue<V> getQueue(String name, Codec codec) {
		return origin.getQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RRingBuffer<V> getRingBuffer(String name) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RRingBuffer<V> getRingBuffer(String name, Codec codec) {
		return origin.getRingBuffer(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RPriorityQueue<V> getPriorityQueue(String name) {
		return origin.getPriorityQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RPriorityQueue<V> getPriorityQueue(String name, Codec codec) {
		return origin.getPriorityQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RPriorityBlockingQueue<V> getPriorityBlockingQueue(String name) {
		return origin.getPriorityBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RPriorityBlockingQueue<V> getPriorityBlockingQueue(String name, Codec codec) {
		return origin.getPriorityBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RPriorityBlockingDeque<V> getPriorityBlockingDeque(String name) {
		return origin.getPriorityBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RPriorityBlockingDeque<V> getPriorityBlockingDeque(String name, Codec codec) {
		return origin.getPriorityBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RPriorityDeque<V> getPriorityDeque(String name) {
		return origin.getPriorityDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RPriorityDeque<V> getPriorityDeque(String name, Codec codec) {
		return origin.getPriorityDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingQueue<V> getBlockingQueue(String name) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingQueue<V> getBlockingQueue(String name, Codec codec) {
		return origin.getBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name) {
		return origin.getBoundedBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name, Codec codec) {
		return origin.getBoundedBlockingQueue(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RDeque<V> getDeque(String name) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RDeque<V> getDeque(String name, Codec codec) {
		return origin.getDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public <V> RBlockingDeque<V> getBlockingDeque(String name) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBlockingDeque<V> getBlockingDeque(String name, Codec codec) {
		return origin.getBlockingDeque(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RAtomicLong getAtomicLong(String name) {
		return origin.getAtomicLong(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RAtomicDouble getAtomicDouble(String name) {
		return origin.getAtomicDouble(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RLongAdder getLongAdder(String name) {
		return origin.getLongAdder(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RDoubleAdder getDoubleAdder(String name) {
		return origin.getDoubleAdder(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RCountDownLatch getCountDownLatch(String name) {
		return origin.getCountDownLatch(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RBitSet getBitSet(String name) {
		return origin.getBitSet(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBloomFilter<V> getBloomFilter(String name) {
		return origin.getBloomFilter(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public <V> RBloomFilter<V> getBloomFilter(String name, Codec codec) {
		return origin.getBloomFilter(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RIdGenerator getIdGenerator(String name) {
		return origin.getIdGenerator(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RScript getScript() {
		return origin.getScript();
	}

	@Override
	public RScript getScript(Codec codec) {
		return origin.getScript(codec);
	}

	@Override
	public RScheduledExecutorService getExecutorService(String name) {
		return origin.getExecutorService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name));
	}

	@Override
	public RScheduledExecutorService getExecutorService(String name, ExecutorOptions options) {
		return origin.getExecutorService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), options);
	}

	@Override
	public RScheduledExecutorService getExecutorService(String name, Codec codec) {
		return origin.getExecutorService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec);
	}

	@Override
	public RScheduledExecutorService getExecutorService(String name, Codec codec, ExecutorOptions options) {
		return origin.getExecutorService(RedisToolManager.REDISSON_TOOL.convertShadowKey(name), codec, options);
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
	public RTransaction createTransaction(TransactionOptions options) {
		return origin.createTransaction(options);
	}

	@Override
	public RBatch createBatch(BatchOptions options) {
		return origin.createBatch(options);
	}

	@Override
	public RBatch createBatch() {
		return origin.createBatch();
	}

	@Override
	public RKeys getKeys() {
		return origin.getKeys();
	}

	@Override
	public RLiveObjectService getLiveObjectService() {
		return origin.getLiveObjectService();
	}

	@Override
	public void shutdown() {
		origin.shutdown();
	}

	@Override
	public void shutdown(long quietPeriod, long timeout, TimeUnit unit) {
		origin.shutdown(quietPeriod, timeout, unit);
	}

	@Override
	public Config getConfig() {
		return origin.getConfig();
	}

	@Override
	public <T extends BaseRedisNodes> T getRedisNodes(RedisNodes<T> nodes) {
		return origin.getRedisNodes(nodes);
	}

	@Override
	@Deprecated
	public NodesGroup<Node> getNodesGroup() {
		return origin.getNodesGroup();
	}

	@Override
	@Deprecated
	public ClusterNodesGroup getClusterNodesGroup() {
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
