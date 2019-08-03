package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbWusStagePo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 今日当前副本id */
	private java.lang.Integer curStageId = null;
	/** 历史最高副本id */
	private java.lang.Integer maxStageId = null;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;
	/**  */
	private java.lang.Integer todayMaxStageId = null;

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 今日当前副本id */
	public java.lang.Integer getCurStageId() {
		return curStageId;
	}

	/** set 今日当前副本id */
	public void setCurStageId(java.lang.Integer curStageId) {
		this.curStageId = curStageId;
	}

	/** get 历史最高副本id */
	public java.lang.Integer getMaxStageId() {
		return maxStageId;
	}

	/** set 历史最高副本id */
	public void setMaxStageId(java.lang.Integer maxStageId) {
		this.maxStageId = maxStageId;
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

	/** get  */
	public java.lang.Integer getTodayMaxStageId() {
		return todayMaxStageId;
	}

	/** set  */
	public void setTodayMaxStageId(java.lang.Integer todayMaxStageId) {
		this.todayMaxStageId = todayMaxStageId;
	}

	@Override
	public String[] props() {
		return new String[] {"`playerId`", "`curStageId`", "`maxStageId`", "`resetTime`", "`isDel`", "`todayMaxStageId`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getCurStageId(), getMaxStageId(), getResetTime(), getIsDel(), getTodayMaxStageId() };
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
