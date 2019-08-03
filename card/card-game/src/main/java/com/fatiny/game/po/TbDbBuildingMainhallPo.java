package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbBuildingMainhallPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 主公殿等级 */
	private java.lang.Short level = 0;
	/** 主公殿状态(0=未解锁;1=待升级;2=升级中,可加速) */
	private java.lang.Integer state = 0;
	/** 开始升级时间 */
	private java.util.Date levelTime = null;
	/** 出战主公技(0=未设置):[skillId,0,skillId,0] */
	private java.lang.String fightSkill = null;
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

	/** get 主公殿等级 */
	public java.lang.Short getLevel() {
		return level;
	}

	/** set 主公殿等级 */
	public void setLevel(java.lang.Short level) {
		this.level = level;
	}

	public java.lang.Short isLevel(){
		return level;
	}

	/** get 主公殿状态(0=未解锁;1=待升级;2=升级中,可加速) */
	public java.lang.Integer getState() {
		return state;
	}

	/** set 主公殿状态(0=未解锁;1=待升级;2=升级中,可加速) */
	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	/** get 开始升级时间 */
	public java.util.Date getLevelTime() {
		return levelTime;
	}

	/** set 开始升级时间 */
	public void setLevelTime(java.util.Date levelTime) {
		this.levelTime = levelTime;
	}

	/** get 出战主公技(0=未设置):[skillId,0,skillId,0] */
	public java.lang.String getFightSkill() {
		return fightSkill;
	}

	/** set 出战主公技(0=未设置):[skillId,0,skillId,0] */
	public void setFightSkill(java.lang.String fightSkill) {
		this.fightSkill = fightSkill;
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
		return new String[] {"`playerId`", "`level`", "`state`", "`levelTime`", "`fightSkill`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getLevel(), getState(), getLevelTime(), getFightSkill(), getIsDel() };
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
