package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbPlayerOfficialPo extends BasePo {


	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 剩余挑战次数 */
	private java.lang.Integer remainFightCount = null;
	/** 购买的总次数, 次日不重置 */
	private java.lang.Integer totalBuyCount = null;
	/** 今日购买的次数, 次日重置 */
	private java.lang.Integer todayBuyCount = null;
	/** 重置时间 */
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

	/** get 剩余挑战次数 */
	public java.lang.Integer getRemainFightCount() {
		return remainFightCount;
	}

	/** set 剩余挑战次数 */
	public void setRemainFightCount(java.lang.Integer remainFightCount) {
		this.remainFightCount = remainFightCount;
	}

	/** get 购买的总次数, 次日不重置 */
	public java.lang.Integer getTotalBuyCount() {
		return totalBuyCount;
	}

	/** set 购买的总次数, 次日不重置 */
	public void setTotalBuyCount(java.lang.Integer totalBuyCount) {
		this.totalBuyCount = totalBuyCount;
	}

	/** get 今日购买的次数, 次日重置 */
	public java.lang.Integer getTodayBuyCount() {
		return todayBuyCount;
	}

	/** set 今日购买的次数, 次日重置 */
	public void setTodayBuyCount(java.lang.Integer todayBuyCount) {
		this.todayBuyCount = todayBuyCount;
	}

	/** get 重置时间 */
	public java.util.Date getResetTime() {
		return resetTime;
	}

	/** set 重置时间 */
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
		return new String[] {"`playerId`", "`remainFightCount`", "`totalBuyCount`", "`todayBuyCount`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getRemainFightCount(), getTotalBuyCount(), getTodayBuyCount(), getResetTime(), getIsDel() };
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
