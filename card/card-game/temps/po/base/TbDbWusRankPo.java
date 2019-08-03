package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbWusRankPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 排名 */
	private java.lang.Integer rank = null;
	/** 历史最高副本id */
	private java.lang.Integer maxStageId = null;
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

	/** get 历史最高副本id */
	public java.lang.Integer getMaxStageId() {
		return maxStageId;
	}

	/** set 历史最高副本id */
	public void setMaxStageId(java.lang.Integer maxStageId) {
		this.maxStageId = maxStageId;
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
		return new String[] {"`curServerId`", "`playerId`", "`rank`", "`maxStageId`", "`createTime`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getRank(), getMaxStageId(), getCreateTime() };
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
