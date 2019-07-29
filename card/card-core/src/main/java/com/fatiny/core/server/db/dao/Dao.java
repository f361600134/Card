package com.fatiny.core.server.db.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.fatiny.core.data.BasePo;
import com.fatiny.core.data.PoRegister;
import com.fatiny.core.data.SqlHelper;
import com.fatiny.core.server.db.message.Command;
import com.fatiny.core.util.GameLog;


/**
 * 数据服dao层
 * 
 * @author huachp
 */
public class Dao<T extends BasePo> {

	private static final Map<Class<?>, Dao> daos = new HashMap<>();
	
	static final MapListHandler mapListHandler = new MapListHandler();

	private final Class<T> cls;
	private final BeanHandler<T> beanHandler;
	private final BeanListHandler<T> beanListHandler;
	private final TableObject tableObject;
	
	
	public static <T extends BasePo> Dao<T> getDao(Class<T> cls) {
		return daos.get(cls);
	}

	
	public static List<Map<String, Object>> query(DataSource ds, String sql, Object... params) throws SQLException {
		QueryRunner qr = new QueryRunner(ds);
		return qr.query(sql, mapListHandler, params);
	}
	
	
	public static int batchSave(DataSource ds, String[] batchSqls) throws SQLException {
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		Statement st = conn.createStatement();
		for (int i = 0; i < batchSqls.length; i++) {
			String sql = batchSqls[i];
			st.addBatch(sql);
		}
		try {
			st.executeBatch();
			conn.commit();
			st.close();
			conn.close();
		} catch (BatchUpdateException e) {
			conn.rollback(); // 回滚操作
			st.close();
			conn.close();
			int[] updateCounts = e.getUpdateCounts();
			handleSaveException(ds, batchSqls, updateCounts);
			throw e;
		} 
		return 0;
	}
	
	
	private static void handleSaveException(DataSource ds, String[] batchSqls, int[] updateCounts) {
		LinkedList<String> errorSqls = new LinkedList<>();
		for (int i = 0; i < batchSqls.length; i++) {
			int rsNum = updateCounts[i];
			if (rsNum == Statement.EXECUTE_FAILED) {
				errorSqls.add(batchSqls[i]);
				batchSqls[i] = null; // 置空
			}
		}
		GameLog.error("执行失败的sqls:{}", errorSqls);
		
		try {
			Connection conn = ds.getConnection();
			conn.setAutoCommit(false);
			Statement batchStatement = conn.createStatement();
			int remainBatchCount = 0;
			for (int i = 0; i < batchSqls.length; i++) {
				String sql = batchSqls[i];
				if (sql == null) {
					continue;
				}
				batchStatement.addBatch(sql);
				remainBatchCount ++;
			}
			
			if (remainBatchCount > 0) {
				batchStatement.executeBatch();
				conn.commit();
			}
			batchStatement.close();
			conn.close();
			GameLog.error("批量保存异常处理成功, 正确sql语句已执行完毕");
			
		} catch (Exception e) {
			GameLog.error("handleSaveException出现异常", e);
		} 
	}
	
	
	
	public Dao(Class<T> cls) {
		this.cls = cls;
		this.beanHandler = new BeanHandler<>(cls);
		this.beanListHandler = new BeanListHandler<>(cls);
		this.tableObject = PoRegister.getTableObject(cls);
		daos.put(cls, this);
	}
	
	
	public Dao(Class<T> cls, TableObject table) {
		this.cls = cls;
		this.beanHandler = new BeanHandler<>(cls);
		this.beanListHandler = new BeanListHandler<>(cls);
		this.tableObject = table;
	}
	
	
	public List<T> select(DataSource ds, long primary) throws SQLException {
		QueryRunner qr = new QueryRunner(ds);
		return qr.query(tableObject.selectByKey, beanListHandler, primary);
	}
	
	
	public int insert(DataSource ds, T t) throws SQLException {
		QueryRunner qr = new QueryRunner(ds);
		return qr.update(tableObject.insert, t.propValues());
	}
	
	
	public int update(DataSource ds, T t) throws SQLException {
		Object[] props = t.propValues();
		Object[] ids = t.idValues();
		Object[] objects = new Object[props.length + ids.length];

		System.arraycopy(props, 0, objects, 0, props.length);
		System.arraycopy(ids, 0, objects, props.length, ids.length);

		QueryRunner qr = new QueryRunner(ds);
		return qr.update(tableObject.update, objects);
	}
	
	
	public int delete(DataSource ds, T t) throws SQLException {
		QueryRunner qr = new QueryRunner(ds);
		return qr.update(tableObject.delete, t.idValues());
	}
	
	
	public int replace(DataSource ds, T t) throws SQLException {
		QueryRunner qr = new QueryRunner(ds);
		return qr.update(tableObject.replace, t.propValues());
	}
	
	
	public int deleteBySql(DataSource ds, String delSql, Object[] values) throws SQLException {
		String execSql = tableObject.deleteAll;
		if (delSql != null) {
			execSql = tableObject.deleteAll + delSql;
		}
		QueryRunner qr = new QueryRunner(ds);
		return qr.update(execSql, values);
	}
	
	
	public List<T> select(DataSource ds, String whereSql, Object[] values) throws SQLException {
		String execSql = tableObject.selectAll;
		if (whereSql != null) {
			execSql = tableObject.selectAll + whereSql;
		}
		QueryRunner qr = new QueryRunner(ds);
		return qr.query(execSql, beanListHandler, values);
	}
	
	
	public String getBatchSql(Command cmd, T t) {
		switch (cmd) {
			case INSERT: 
				return SqlHelper.spliceSql(tableObject.insert, "?", t.propValues());
				
			case UPDATE: 
				Object[] props = t.propValues();
				Object[] ids = t.idValues();
				Object[] objects = new Object[props.length + ids.length];

				System.arraycopy(props, 0, objects, 0, props.length);
				System.arraycopy(ids, 0, objects, props.length, ids.length);
				
				return SqlHelper.spliceSql(tableObject.update, "?", objects);
				
			case DELETE: 
				return SqlHelper.spliceSql(tableObject.delete, "?", t.idValues());
				
			default: return "";
		}
	}
	
}
