package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCityStagePo extends BasePo {


	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 今日搜索次数 */
	private java.lang.Integer searchCount = null;
	/** 耐力 */
	private java.lang.Short sta = null;
	/** 搜索到的mapId */
	private java.lang.Short searchMapId = null;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 今日搜索次数 */
	public java.lang.Integer getSearchCount() {
		return searchCount;
	}

	/** set 今日搜索次数 */
	public void setSearchCount(java.lang.Integer searchCount) {
		this.searchCount = searchCount;
	}

	/** get 耐力 */
	public java.lang.Short getSta() {
		return sta;
	}

	/** set 耐力 */
	public void setSta(java.lang.Short sta) {
		this.sta = sta;
	}

	public java.lang.Short isSta(){
		return sta;
	}

	/** get 搜索到的mapId */
	public java.lang.Short getSearchMapId() {
		return searchMapId;
	}

	/** set 搜索到的mapId */
	public void setSearchMapId(java.lang.Short searchMapId) {
		this.searchMapId = searchMapId;
	}

	public java.lang.Short isSearchMapId(){
		return searchMapId;
	}

	/** get 最近重置时间 */
	public java.util.Date getResetTime() {
		return resetTime;
	}

	/** set 最近重置时间 */
	public void setResetTime(java.util.Date resetTime) {
		this.resetTime = resetTime;
	}

	/** get 0=未删除;1=删除 */
	public java.lang.Boolean getIsDel() {
		return isDel;
	}

	/** set 0=未删除;1=删除 */
	public void setIsDel(java.lang.Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String[] props() {
		return new String[] {"`playerId`", "`searchCount`", "`sta`", "`searchMapId`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getSearchCount(), getSta(), getSearchMapId(), getResetTime(), getIsDel() };
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
