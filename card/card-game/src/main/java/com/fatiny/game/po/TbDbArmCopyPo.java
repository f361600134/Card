package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbArmCopyPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 今日剩余次数 */
	private java.lang.Integer remainCount = null;
	/** 已通关副本id列表:[id,id] */
	private java.lang.String stageInfo = null;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 今日剩余次数 */
	public java.lang.Integer getRemainCount() {
		return remainCount;
	}

	/** set 今日剩余次数 */
	public void setRemainCount(java.lang.Integer remainCount) {
		this.remainCount = remainCount;
	}

	/** get 已通关副本id列表:[id,id] */
	public java.lang.String getStageInfo() {
		return stageInfo;
	}

	/** set 已通关副本id列表:[id,id] */
	public void setStageInfo(java.lang.String stageInfo) {
		this.stageInfo = stageInfo;
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
		return new String[] {"`playerId`", "`remainCount`", "`stageInfo`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getRemainCount(), getStageInfo(), getResetTime(), getIsDel() };
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
