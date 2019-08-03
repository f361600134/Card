package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCheckinInfoPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 累计签到天数,上限值100 */
	private java.lang.Integer checkedDays = null;
	/** 已领取的连续登录奖励:[id,id] */
	private java.lang.String continueRewards = null;
	/** 最近签到时间 */
	private java.util.Date lastCheckinTime = null;
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

	/** get 累计签到天数,上限值100 */
	public java.lang.Integer getCheckedDays() {
		return checkedDays;
	}

	/** set 累计签到天数,上限值100 */
	public void setCheckedDays(java.lang.Integer checkedDays) {
		this.checkedDays = checkedDays;
	}

	/** get 已领取的连续登录奖励:[id,id] */
	public java.lang.String getContinueRewards() {
		return continueRewards;
	}

	/** set 已领取的连续登录奖励:[id,id] */
	public void setContinueRewards(java.lang.String continueRewards) {
		this.continueRewards = continueRewards;
	}

	/** get 最近签到时间 */
	public java.util.Date getLastCheckinTime() {
		return lastCheckinTime;
	}

	/** set 最近签到时间 */
	public void setLastCheckinTime(java.util.Date lastCheckinTime) {
		this.lastCheckinTime = lastCheckinTime;
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
		return new String[] {"`playerId`", "`checkedDays`", "`continueRewards`", "`lastCheckinTime`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getCheckedDays(), getContinueRewards(), getLastCheckinTime(), getResetTime(), getIsDel() };
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
