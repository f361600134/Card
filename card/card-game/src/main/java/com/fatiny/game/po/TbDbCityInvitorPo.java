package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCityInvitorPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 地图id */
	private java.lang.Integer mapId = null;
	/** 创建时间 */
	private java.util.Date createTime = null;

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 地图id */
	public java.lang.Integer getMapId() {
		return mapId;
	}

	/** set 地图id */
	public void setMapId(java.lang.Integer mapId) {
		this.mapId = mapId;
	}

	/** get 创建时间 */
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/** set 创建时间 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String[] props() {
		return new String[] {"`curServerId`", "`playerId`", "`mapId`", "`createTime`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getMapId(), getCreateTime() };
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
