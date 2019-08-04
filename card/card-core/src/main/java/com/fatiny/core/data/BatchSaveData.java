package com.fatiny.core.data;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.fatiny.core.client.db.DbServerClient;
import com.fatiny.core.client.db.Response;
import com.fatiny.core.server.db.message.BatchSaveMsg;
import com.fatiny.core.server.db.message.Command;
import com.fatiny.core.server.db.message.CompositeReqMsg;
import com.fatiny.core.util.IdHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 批量保存
 */
@Slf4j
public class BatchSaveData {
	
	private static final int BATCH_COUNT = 100; // 批处理数量

	/** 批处理标识 */
	static int batchMark = 0;
	/** 玩家批量保存数据 */
	private static ConcurrentMap<Long, BatchSaveMsg> playerSaveMsgs = new ConcurrentHashMap<>();
	/** 公共模块批量保存数据 */
	private static ConcurrentMap<Long, BatchSaveMsg> publicSaveMsgs = new ConcurrentHashMap<>();
	
	
	public static void save(long id, Command cmd, BasePo basePo) {
		if (IdHelper.isPlayerType(id)) {
			BatchSaveMsg batchSaveMsg = getPlayerSaveMsg(id);
			batchSaveMsg.addComponent(cmd, basePo);
		} else {
			BatchSaveMsg batchSaveMsg = getPublicSaveMsg(id);
			batchSaveMsg.addComponent(cmd, basePo);
		}
	}
	
	
	static BatchSaveMsg getPlayerSaveMsg(long id) {
		BatchSaveMsg batchSaveMsg = playerSaveMsgs.get(id);
		if (batchSaveMsg != null) {
			return batchSaveMsg;
		}
		batchSaveMsg = new BatchSaveMsg(id);
		BatchSaveMsg old = playerSaveMsgs.putIfAbsent(id, batchSaveMsg);
		if (old != null) {
			batchSaveMsg = old;
		}
		return batchSaveMsg;
	}
	
	
	static BatchSaveMsg getPublicSaveMsg(long id) {
		BatchSaveMsg batchSaveMsg = publicSaveMsgs.get(id);
		if (batchSaveMsg != null) {
			return batchSaveMsg;
		}
		batchSaveMsg = new BatchSaveMsg(id);
		BatchSaveMsg old = publicSaveMsgs.putIfAbsent(id, batchSaveMsg);
		if (old != null) {
			batchSaveMsg = old;
		}
		return batchSaveMsg;
	}
	
	
	public static Response syncBatchData(DbServerClient client) {
		if (batchMark == 0) { // PLAYER
			Response resp = syncPlayerBatchData(client);
			batchMark = 1;
			return resp;
		} else if (batchMark == 1) { // PUBLIC
			Response resp = syncPublicBatchData(client);
			batchMark = 0;
			return resp;
		} else {
			log.error("save batch data error, mark={}", batchMark);
			throw new IllegalArgumentException("save batch data error, mark=" + batchMark);
		}
	}
	
	
	public static Response syncPlayerBatchData(DbServerClient client) {
		Iterator<BatchSaveMsg> it = playerSaveMsgs.values().iterator();
		CompositeReqMsg compositeMsg = new CompositeReqMsg();
		for ( ;it.hasNext(); ) {
			BatchSaveMsg batchSaveMsg = it.next();
			compositeMsg.addComponent(batchSaveMsg);
			if (compositeMsg.componentSize() == BATCH_COUNT) {
				client.request(compositeMsg);
				compositeMsg = new CompositeReqMsg();
			}
			it.remove();
		}
		if (compositeMsg.componentSize() > 0) {
			return client.request(compositeMsg);
		}
		return null; // 如果没有保存消息返回null
	}
	
	
	public static Response syncPublicBatchData(DbServerClient client) {
		Iterator<BatchSaveMsg> it = publicSaveMsgs.values().iterator();
		CompositeReqMsg compositeMsg = new CompositeReqMsg();
		for ( ;it.hasNext(); ) {
			BatchSaveMsg batchSaveMsg = it.next();
			compositeMsg.addComponent(batchSaveMsg);
			if (compositeMsg.componentSize() == BATCH_COUNT) {
				client.request(compositeMsg);
				compositeMsg = new CompositeReqMsg();
			}
			it.remove();
		}
		if (compositeMsg.componentSize() > 0) {
			return client.request(compositeMsg);
		}
		return null; // 如果没有保存消息返回null
	}
	
	
}
