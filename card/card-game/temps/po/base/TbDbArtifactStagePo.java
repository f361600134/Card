package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbArtifactStagePo extends BasePo {


	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 已通关副本id列表:[id,id] */
	private java.lang.String stageInfo = null;
	/** 剩余挑战次数 */
	private java.lang.Short remainCount = 0;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;
	/** 已经购买次数 */
	private java.lang.Short alreadBuyCount = null;

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 已通关副本id列表:[id,id] */
	public java.lang.String getStageInfo() {
		return stageInfo;
	}

	/** set 已通关副本id列表:[id,id] */
	public void setStageInfo(java.lang.String stageInfo) {
		this.stageInfo = stageInfo;
	}

	/** get 剩余挑战次数 */
	public java.lang.Short getRemainCount() {
		return remainCount;
	}

	/** set 剩余挑战次数 */
	public void setRemainCount(java.lang.Short remainCount) {
		this.remainCount = remainCount;
	}

	public java.lang.Short isRemainCount(){
		return remainCount;
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

	/** get 已经购买次数 */
	public java.lang.Short getAlreadBuyCount() {
		return alreadBuyCount;
	}

	/** set 已经购买次数 */
	public void setAlreadBuyCount(java.lang.Short alreadBuyCount) {
		this.alreadBuyCount = alreadBuyCount;
	}

	public java.lang.Short isAlreadBuyCount(){
		return alreadBuyCount;
	}

	@Override
	public String[] props() {
		return new String[] {"`playerId`", "`stageInfo`", "`remainCount`", "`resetTime`", "`isDel`", "`alreadBuyCount`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getStageInfo(), getRemainCount(), getResetTime(), getIsDel(), getAlreadBuyCount() };
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
