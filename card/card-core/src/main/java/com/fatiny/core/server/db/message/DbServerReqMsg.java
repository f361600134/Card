package com.fatiny.core.server.db.message;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.fatiny.core.data.BasePo;
import com.fatiny.core.data.Cacheable;

/**
 * 数据服请求协议
 * 
 */
public class DbServerReqMsg extends AbstractDbServerMsg {

	/** 业务实体ID */
	private long id;
	/** 协议cmd */
	private Command cmd;
	/** 需要更新的实体类对象 */
	private BasePo po;
	/** 实体类信息 */
	private Class poCls;
	/** 查询sql */
	private String querySql;
	/** 参数 */
	private Object[] params;
	
	
	public static DbServerReqMsg createLoadReq(long key, Class poCls) {
		DbServerReqMsg reqMsg = new DbServerReqMsg(key);
		reqMsg.poCls = poCls;
		reqMsg.cmd = Command.LOAD_DATA;
		return reqMsg;
	}
	
	
	public static DbServerReqMsg createReq(long key, Class poCls, 
			Command cmd, String whereSql, Object... params) {
		DbServerReqMsg reqMsg = new DbServerReqMsg(key);
		reqMsg.cmd = cmd;
		reqMsg.poCls = poCls;
		reqMsg.querySql = whereSql;
		reqMsg.params = params;
		return reqMsg;
	}
	
	
	public static DbServerReqMsg createSaveReq(long key, Command cmd, BasePo po) {
		DbServerReqMsg reqMsg = new DbServerReqMsg(key);
		reqMsg.cmd = cmd;
		reqMsg.po = po;
		reqMsg.poCls = po.getClass();
		return reqMsg;
	}
	
	
	public static DbServerReqMsg createSqlReq(long key, String sql, Object... params) {
		DbServerReqMsg reqMsg = new DbServerReqMsg(key);
		reqMsg.cmd = Command.QUERY;
		reqMsg.querySql = sql;
		reqMsg.params = params;
		return reqMsg;
	}
		
	
	public DbServerReqMsg() {
		
	}
	
	public DbServerReqMsg(long id) {
		this.id = id;
	}

	public BasePo getPo() {
		return po;
	}

	public Class getPoCls() {
		return poCls;
	}

	public String getQuerySql() {
		return querySql;
	}

	public Object[] getParams() {
		return params;
	}
	
	public boolean isCacheable() {
		if (id <= 0) {
			return false;
		}
		if (poCls == null) {
			return false;
		}
		if (!StringUtils.isBlank(querySql)) {
			return false;
		}
		Class<?> cls = poCls;
		Cacheable cacheable = cls.getAnnotation(Cacheable.class);
		if (cacheable == null) {
			return false;
		}
		return true;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public Command getCmd() {
		return cmd;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbServerReqMsg [");
		builder.append("id=").append(id).append(", cmd=").append(cmd.name());
		if (poCls != null) {
			builder.append(", po=").append(poCls);
		}
		if (querySql != null) {
			builder.append(", sql=").append(querySql);
		}
		if (!ArrayUtils.isEmpty(params)) {
			builder.append(", params=").append(params);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
