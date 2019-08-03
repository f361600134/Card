package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCityResourcePo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 地图id */
	private java.lang.Integer mapId = null;
	/** 资源id */
	private java.lang.Integer resourceId = null;
	/** 地图资源创建时间 */
	private java.util.Date openTime = null;
	/** 拥有者角色ID,0表示没有占有者 */
	private java.lang.Integer playerOnwer = null;
	/** 最近开始计算资源时间 */
	private java.util.Date resourceTime = null;
	/** 总产出资源 */
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

	/** get 地图资源创建时间 */
	public java.util.Date getOpenTime() {
		return openTime;
	}

	/** set 地图资源创建时间 */
	public void setOpenTime(java.util.Date openTime) {
		this.openTime = openTime;
	}

	/** get 拥有者角色ID,0表示没有占有者 */
	public java.lang.Integer getPlayerOnwer() {
		return playerOnwer;
	}

	/** set 拥有者角色ID,0表示没有占有者 */
	public void setPlayerOnwer(java.lang.Integer playerOnwer) {
		this.playerOnwer = playerOnwer;
	}

	/** get 最近开始计算资源时间 */
	public java.util.Date getResourceTime() {
		return resourceTime;
	}

	/** set 最近开始计算资源时间 */
	public void setResourceTime(java.util.Date resourceTime) {
		this.resourceTime = resourceTime;
	}

	/** get 总产出资源 */
	public java.lang.Integer getResourceProgress() {
		return resourceProgress;
	}

	/** set 总产出资源 */
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
		return new String[] {"`curServerId`", "`mapId`", "`resourceId`", "`openTime`", "`playerOnwer`", "`resourceTime`", "`resourceProgress`", "`resourceNum`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getMapId(), getResourceId(), getOpenTime(), getPlayerOnwer(), getResourceTime(), getResourceProgress(), getResourceNum() };
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
