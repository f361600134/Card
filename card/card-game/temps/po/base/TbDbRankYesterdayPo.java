package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbRankYesterdayPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 排行榜类型 */
	private java.lang.Integer rankType = null;
	/** 对象id */
	private java.lang.Integer objId = null;
	/** 用于排序值 */
	private java.lang.Integer objValue = null;
	/** 入榜时间 */
	private java.lang.Long createTime = null;

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 排行榜类型 */
	public java.lang.Integer getRankType() {
		return rankType;
	}

	/** set 排行榜类型 */
	public void setRankType(java.lang.Integer rankType) {
		this.rankType = rankType;
	}

	/** get 对象id */
	public java.lang.Integer getObjId() {
		return objId;
	}

	/** set 对象id */
	public void setObjId(java.lang.Integer objId) {
		this.objId = objId;
	}

	/** get 用于排序值 */
	public java.lang.Integer getObjValue() {
		return objValue;
	}

	/** set 用于排序值 */
	public void setObjValue(java.lang.Integer objValue) {
		this.objValue = objValue;
	}

	/** get 入榜时间 */
	public java.lang.Long getCreateTime() {
		return createTime;
	}

	/** set 入榜时间 */
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String[] props() {
		return new String[] {"`curServerId`", "`rankType`", "`objId`", "`objValue`", "`createTime`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getRankType(), getObjId(), getObjValue(), getCreateTime() };
	}

	@Override
	public String[] ids() {
		return new String[] {"``"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] {  };
	}
}
