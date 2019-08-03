package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbHeroMissionPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 武将ID */
	private java.lang.Integer heroId = null;
	/** 当前武将任务ID */
	private java.lang.Integer curMissionId = null;
	/** 当前任务进度 */
	private java.lang.Integer progress = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;
	/** 状态,0:未完成, 1:已完成未激活, 2:已激活 */
	private java.lang.Short state = null;
	/** 武将配置ID */
	private java.lang.Integer heroConfigId = null;

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 武将ID */
	public java.lang.Integer getHeroId() {
		return heroId;
	}

	/** set 武将ID */
	public void setHeroId(java.lang.Integer heroId) {
		this.heroId = heroId;
	}

	/** get 当前武将任务ID */
	public java.lang.Integer getCurMissionId() {
		return curMissionId;
	}

	/** set 当前武将任务ID */
	public void setCurMissionId(java.lang.Integer curMissionId) {
		this.curMissionId = curMissionId;
	}

	/** get 当前任务进度 */
	public java.lang.Integer getProgress() {
		return progress;
	}

	/** set 当前任务进度 */
	public void setProgress(java.lang.Integer progress) {
		this.progress = progress;
	}

	/** get 0=未删除;1=删除 */
	public java.lang.Boolean getIsDel() {
		return isDel;
	}

	/** set 0=未删除;1=删除 */
	public void setIsDel(java.lang.Boolean isDel) {
		this.isDel = isDel;
	}

	/** get 状态,0:未完成, 1:已完成未激活, 2:已激活 */
	public java.lang.Short getState() {
		return state;
	}

	/** set 状态,0:未完成, 1:已完成未激活, 2:已激活 */
	public void setState(java.lang.Short state) {
		this.state = state;
	}

	public java.lang.Short isState(){
		return state;
	}

	/** get 武将配置ID */
	public java.lang.Integer getHeroConfigId() {
		return heroConfigId;
	}

	/** set 武将配置ID */
	public void setHeroConfigId(java.lang.Integer heroConfigId) {
		this.heroConfigId = heroConfigId;
	}

	@Override
	public String[] props() {
		return new String[] {"`playerId`", "`heroId`", "`curMissionId`", "`progress`", "`isDel`", "`state`", "`heroConfigId`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getHeroId(), getCurMissionId(), getProgress(), getIsDel(), getState(), getHeroConfigId() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`hero_id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { heroId, playerId };
	}
}
