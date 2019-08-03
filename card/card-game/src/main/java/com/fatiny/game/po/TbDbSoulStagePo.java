package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbSoulStagePo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 伤害百分比 */
	private java.lang.Integer myHurt = null;
	/** 战斗时长 */
	private java.lang.Short myTime = null;
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

	/** get 伤害百分比 */
	public java.lang.Integer getMyHurt() {
		return myHurt;
	}

	/** set 伤害百分比 */
	public void setMyHurt(java.lang.Integer myHurt) {
		this.myHurt = myHurt;
	}

	/** get 战斗时长 */
	public java.lang.Short getMyTime() {
		return myTime;
	}

	/** set 战斗时长 */
	public void setMyTime(java.lang.Short myTime) {
		this.myTime = myTime;
	}

	public java.lang.Short isMyTime(){
		return myTime;
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
		return new String[] {"`playerId`", "`myHurt`", "`myTime`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getMyHurt(), getMyTime(), getResetTime(), getIsDel() };
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
