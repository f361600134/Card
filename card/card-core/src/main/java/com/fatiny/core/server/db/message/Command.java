package com.fatiny.core.server.db.message;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.sql.DataSource;

import com.fatiny.core.data.BasePo;
import com.fatiny.core.server.db.dao.Dao;
import com.fatiny.core.server.db.message.BatchSaveMsg.PoInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库执行命令
 */
@Slf4j
public enum Command implements Constant {
	
	
	LOAD_DATA(READ_TYPE) {
		
		@Override
		public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			long primaryId = dbServerReqMsg.getId();
			return Dao.getDao(cls).select(ds, primaryId);
		}

	},
	
	LOAD_ALL_DATA(READ_TYPE) {

		@Override
		public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			String whereSql = dbServerReqMsg.getQuerySql();
			Object[] params = dbServerReqMsg.getParams();
			return Dao.getDao(cls).select(ds, whereSql, params);
		}

	},
	
	INSERT(WRITE_TYPE) {

		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			BasePo basePo = dbServerReqMsg.getPo();
			Dao.getDao(cls).insert(ds, basePo);
			return true;
		}
	},
	
	UPDATE(WRITE_TYPE) {

		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			BasePo basePo = dbServerReqMsg.getPo();
			Dao.getDao(cls).update(ds, basePo);
			return true;
		}
	},
	
	DELETE(WRITE_TYPE) {

		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			BasePo basePo = dbServerReqMsg.getPo();
			Dao.getDao(cls).delete(ds, basePo);
			return true;
		}
	},
	
	QUERY(READ_TYPE) {

		@Override
		public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			String sql = dbServerReqMsg.getQuerySql();
			Object[] params = dbServerReqMsg.getParams();
			return Dao.query(ds, sql, params);
		}

	},
	
	LOAD_BY_PROPS(READ_TYPE) {

		@Override
		public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			String whereSql = dbServerReqMsg.getQuerySql();
			Object[] params = dbServerReqMsg.getParams();
			return Dao.getDao(cls).select(ds, whereSql, params);
		}

	},
	
	DELETE_ALL(WRITE_TYPE) {

		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			String whereSql = dbServerReqMsg.getQuerySql();
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			Object[] params = dbServerReqMsg.getParams();
			Dao.getDao(cls).deleteBySql(ds, whereSql, params);
			return true;
		}
		
	},
	
	REPLACE(WRITE_TYPE) {

		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			DbServerReqMsg dbServerReqMsg = (DbServerReqMsg) requestMsg;
			Class<BasePo> cls = dbServerReqMsg.getPoCls();
			BasePo basePo = dbServerReqMsg.getPo();
			Dao.getDao(cls).replace(ds, basePo);
			return true;
		}
		
	},
	
	BATCH_SAVE(WRITE_TYPE) {
		
		@Override
		public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			BatchSaveMsg batchSaveMsg = (BatchSaveMsg) requestMsg;
			Iterator<PoInfo> poIterator = batchSaveMsg.getComponents();
			
			String[] batchSqls = new String[batchSaveMsg.size()];
			int index = 0;
			for (; poIterator.hasNext(); index++) {
				PoInfo poInfo = poIterator.next();
				Command cmd = poInfo.getCmd();
				BasePo po = poInfo.getPo();
				Class cls = po.getClass();
				String finalSql = Dao.getDao(cls).getBatchSql(cmd, po);
				batchSqls[index] = finalSql;
			}
			
			Dao.batchSave(ds, batchSqls);
			
			return true;
		}
		
	},
	
	BATCH_LOAD(READ_TYPE) {

		@Override
		public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
			BatchLoadMsg batchLoadMsg = (BatchLoadMsg) requestMsg;
			LinkedHashSet<Class> classes = batchLoadMsg.getComponents();
			
			long primaryId = batchLoadMsg.getId();
			for (Iterator<Class> it = classes.iterator(); it.hasNext(); ) {
				Class<BasePo> cls = it.next();
				List data = Dao.getDao(cls).select(ds, primaryId);
				batchLoadMsg.addData(cls, data);
				it.remove(); // 删除已加载成功的class
				log.debug("批量加载对象{}数据", cls);
			}
			return Collections.emptyList();
		}
		
	}
	
	;
	
	private String operation;
	
	private Command(String sqlType) {
		this.operation = sqlType;
	}
	
	
	public boolean isReadType() {
		return operation == READ_TYPE;
	}
	
	public boolean isWriteType() {
		return operation == WRITE_TYPE;
	}
	
	
	public List execLoad(DataSource ds, DbServerMsg requestMsg) throws SQLException {
		return null;
	}
	
	public boolean execSave(DataSource ds, DbServerMsg requestMsg) throws SQLException {
		return false;
	}
	
}


interface Constant {
	
	String WRITE_TYPE = "WRITE";
	String READ_TYPE = "READ";
}

