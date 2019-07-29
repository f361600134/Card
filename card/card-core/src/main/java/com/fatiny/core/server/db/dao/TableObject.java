package com.fatiny.core.server.db.dao;

import com.fatiny.core.data.BasePo;
import com.fatiny.core.data.PO;
import com.fatiny.core.data.SqlHelper;
import com.fatiny.core.util.GameLog;

/**
 * 数据库表对象
 * @author chao
 */
public class TableObject {

	public final Class<?> cls;
	public final String tbName;
	public final String selectAll;
	public final String select;
	public final String selectByKey;
	public final String deleteAll;
	public final String delete;
	public final String update;
	public final String insert;
	public final String replace;

	public TableObject(Class<? extends BasePo> cls) {
		this.cls = cls;
		PO po = cls.getAnnotation(PO.class);
		this.tbName = po.value();

		BasePo ins = null;
		try {
			ins = cls.newInstance();
		} catch (Exception e) {
			GameLog.error("BasePo对象反射出错:{}", po, e);
		}
		
		this.selectAll = SqlHelper.selectAll(tbName);
		this.select = SqlHelper.select(tbName, ins.ids());
		this.selectByKey = SqlHelper.select(tbName, new String[] { ins.keyColumn() });
		this.deleteAll = SqlHelper.deleteAll(tbName);
		this.delete = SqlHelper.delete(tbName, ins.ids());

		this.update = SqlHelper.update(tbName, ins.props(), ins.ids());
		this.insert = SqlHelper.insert(tbName, ins.props());
		this.replace = SqlHelper.replace(tbName, ins.props());
	}

}
