package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbMissionInfoPo extends BasePo {


	/** 任务ID */
	private java.lang.Integer id = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取 */
	private java.lang.Integer state = null;
	/** 任务进度 */
	private java.lang.Short progress = null;
	/** 任务接取时间 */
	private java.util.Date recvTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 任务ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 任务ID */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取 */
	public java.lang.Integer getState() {
		return state;
	}

	/** set 任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取 */
	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	/** get 任务进度 */
	public java.lang.Short getProgress() {
		return progress;
	}

	/** set 任务进度 */
	public void setProgress(java.lang.Short progress) {
		this.progress = progress;
	}

	public java.lang.Short isProgress(){
		return progress;
	}

	/** get 任务接取时间 */
	public java.util.Date getRecvTime() {
		return recvTime;
	}

	/** set 任务接取时间 */
	public void setRecvTime(java.util.Date recvTime) {
		this.recvTime = recvTime;
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
		return new String[] {"`id`", "`playerId`", "`state`", "`progress`", "`recvTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getPlayerId(), getState(), getProgress(), getRecvTime(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { id, playerId };
	}
}
