package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCityMapPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 地图id */
	private java.lang.Integer mapId = null;
	/** 资源id */
	private java.lang.Integer resourceId = null;
	/** 最近开始计算资源时间 */
	private java.util.Date resourceTime = null;
	/** 总计算资源时间 */
	private java.lang.Integer resourceProgress = null;
	/** 可领取生产的资源 */
	private java.lang.Integer resourceNum = null;

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

	/** get 资源id */
	public java.lang.Integer getResourceId() {
		return resourceId;
	}

	/** set 资源id */
	public void setResourceId(java.lang.Integer resourceId) {
		this.resourceId = resourceId;
	}

	/** get 最近开始计算资源时间 */
	public java.util.Date getResourceTime() {
		return resourceTime;
	}

	/** set 最近开始计算资源时间 */
	public void setResourceTime(java.util.Date resourceTime) {
		this.resourceTime = resourceTime;
	}

	/** get 总计算资源时间 */
	public java.lang.Integer getResourceProgress() {
		return resourceProgress;
	}

	/** set 总计算资源时间 */
	public void setResourceProgress(java.lang.Integer resourceProgress) {
		this.resourceProgress = resourceProgress;
	}

	/** get 可领取生产的资源 */
	public java.lang.Integer getResourceNum() {
		return resourceNum;
	}

	/** set 可领取生产的资源 */
	public void setResourceNum(java.lang.Integer resourceNum) {
		this.resourceNum = resourceNum;
	}

	@Override
	public String[] props() {
		return new String[] {"`curServerId`", "`playerId`", "`mapId`", "`resourceId`", "`resourceTime`", "`resourceProgress`", "`resourceNum`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getMapId(), getResourceId(), getResourceTime(), getResourceProgress(), getResourceNum() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { playerId };
	}
}
