package com.fatiny.core.data;

import java.util.List;
import java.util.Map;

import com.fatiny.core.client.db.DbServerClient;
import com.fatiny.core.client.db.Response;
import com.fatiny.core.server.db.message.BatchLoadMsg;
import com.fatiny.core.server.db.message.Command;
import com.fatiny.core.server.db.message.DbServerReqMsg;

/**
 * 同步数据
 * 
 * 注意:
 * 	1.这里的同步是指数据同步
 * 	2.SyncData包含同步api和异步api
 * 	3.异步api后续会增加
 * 
 * @author huachp
 */
public class SyncData implements IData {
	
	private DbServerClient client;
	
	public void injectDbServerConnector(DbServerClient client) {
		this.client = client;
	}
	
	
	@Override
	public int delete(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.DELETE;
		DbServerReqMsg requestMsg = DbServerReqMsg.createSaveReq(t.key(), cmd, t);
		Response response = client.request(requestMsg);
		response.sync(3000L);
		return 1;
	}

	
	@Override
	public boolean update(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.UPDATE;
		DbServerReqMsg requestMsg = DbServerReqMsg.createSaveReq(t.key(), cmd, t);
		Response response = client.request(requestMsg);
//		response.sync(3000L);
		return true;
	}
	

	@Override
	public void insert(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.INSERT;
		DbServerReqMsg requestMsg = DbServerReqMsg.createSaveReq(t.key(), cmd, t);
		Response response = client.request(requestMsg);
		response.sync(3000L);
	}

	
	@Override
	public <T extends BasePo> List<T> getData(Class<? extends BasePo> cls, long primary) {
		DbServerReqMsg requestMsg = DbServerReqMsg.createLoadReq(primary, cls);
		Response response = client.request(requestMsg);
		response.sync(3000L);
		List<T> data = response.get();
		return data;
	}

	
	@Override
	public <T extends BasePo> List<T> getAllData(long key, Class<? extends BasePo> cls) {
		DbServerReqMsg requestMsg = DbServerReqMsg.createReq(key, cls, Command.LOAD_BY_PROPS, "");
		Response response = client.request(requestMsg);
		response.sync(3000L);
		List<T> data = response.get();
		return data;
	}

	
	@Override
	public List<Map<String, Object>> query(long key, String sql, Object... params) {
		DbServerReqMsg requestMsg = DbServerReqMsg.createSqlReq(key, sql, params);
		Response response = client.request(requestMsg);
		response.sync(3000L);
		List<Map<String, Object>> data = response.get();
		return data;
	}


	@Override
	public <T extends BasePo> List<T> getByProps(long key, Class<? extends BasePo> cls, String[] props, Object[] values) {
		StringBuilder builder = new StringBuilder();
		builder.append(" WHERE ");
		builder.append(props[0]).append("=").append("?");
		for (int i = 1; i < props.length; ++i) {
			builder.append(" and ").append(props[i]).append("=?");
		}
		String whereSql = builder.toString();
		
		DbServerReqMsg requestMsg = DbServerReqMsg.createReq(key, cls, Command.LOAD_BY_PROPS, whereSql, values);
		Response response = client.request(requestMsg);
		response.sync(3000L);
		List<T> data = response.get();
		return data;
	}


	@Override
	public int deleteBySql(long key, Class<? extends BasePo> cls, String[] props, Object[] values) {
		StringBuilder builder = new StringBuilder();
		builder.append(" WHERE ");
		builder.append(props[0]).append("=").append("?");
		for (int i = 1; i < props.length; ++i) {
			builder.append(" and ").append(props[i]).append("=?");
		}
		String whereSql = builder.toString();
		
		DbServerReqMsg requestMsg = DbServerReqMsg.createReq(key, cls, Command.DELETE_ALL, whereSql, values);
		Response response = client.request(requestMsg);
		response.sync(3000L);
		return 1;
	}
	
	
	@Override
	public void insertAsync(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.INSERT;
		BatchSaveData.save(t.key(), cmd, t);
	}


	@Override
	public void updateAsync(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.UPDATE;
		BatchSaveData.save(t.key(), cmd, t);
	}
	
	
	@Override
	public void deleteAync(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.DELETE;
		BatchSaveData.save(t.key(), cmd, t);
	}


	@Override
	public Response executeBatchSave() {
		return BatchSaveData.syncBatchData(client);
	}
	
	
	@Override
	public BatchLoadMsg executeBatchLoad(BatchLoadMsg batchLoadMsg) {
		Response response = client.request(batchLoadMsg);
		response.sync(3000L);
		BatchLoadMsg loadDataMsg = response.get();
		return loadDataMsg;
	}


	@Override
	public void replaceAsync(Class<? extends BasePo> cls, BasePo t) {
		Command cmd = Command.REPLACE;
		DbServerReqMsg requestMsg = DbServerReqMsg.createSaveReq(t.key(), cmd, t);
		client.request(requestMsg);
	}

	
}
