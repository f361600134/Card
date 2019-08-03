package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbSoulStageRankPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 排名 */
	private java.lang.Integer rank = null;
	/** 伤害百分比 */
	private java.lang.Integer hurt = 0;
	/** 通关时长 */
	private java.lang.Short useTime = null;
	/** 入榜时间 */
	private java.lang.Long createTime = null;

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 排名 */
	public java.lang.Integer getRank() {
		return rank;
	}

	/** set 排名 */
	public void setRank(java.lang.Integer rank) {
		this.rank = rank;
	}

	/** get 伤害百分比 */
	public java.lang.Integer getHurt() {
		return hurt;
	}

	/** set 伤害百分比 */
	public void setHurt(java.lang.Integer hurt) {
		this.hurt = hurt;
	}

	/** get 通关时长 */
	public java.lang.Short getUseTime() {
		return useTime;
	}

	/** set 通关时长 */
	public void setUseTime(java.lang.Short useTime) {
		this.useTime = useTime;
	}

	public java.lang.Short isUseTime(){
		return useTime;
	}

	/** get 入榜时间 */
	public java.lang.Long getCreateTime() {
		return createTime;
	}

	/** set 入榜时间 */
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String[] props() {
		return new String[] {"`curServerId`", "`playerId`", "`rank`", "`hurt`", "`useTime`", "`createTime`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getRank(), getHurt(), getUseTime(), getCreateTime() };
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
