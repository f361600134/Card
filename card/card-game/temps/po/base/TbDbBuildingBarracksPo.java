package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbBuildingBarracksPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 兵营等级 */
	private java.lang.Short level = 0;
	/** 兵营状态(0=未解锁;1=待升级;2=升级中,可加速) */
	private java.lang.Integer state = 0;
	/** 开始升级时间 */
	private java.util.Date levelTime = null;
	/** 兵营科技:{configId:level} */
	private java.lang.String technologyInfo = null;
	/** 科技升级结束时间 */
	private java.util.Date technologyTime = null;
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

	/** get 兵营等级 */
	public java.lang.Short getLevel() {
		return level;
	}

	/** set 兵营等级 */
	public void setLevel(java.lang.Short level) {
		this.level = level;
	}

	public java.lang.Short isLevel(){
		return level;
	}

	/** get 兵营状态(0=未解锁;1=待升级;2=升级中,可加速) */
	public java.lang.Integer getState() {
		return state;
	}

	/** set 兵营状态(0=未解锁;1=待升级;2=升级中,可加速) */
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

	/** get 兵营科技:{configId:level} */
	public java.lang.String getTechnologyInfo() {
		return technologyInfo;
	}

	/** set 兵营科技:{configId:level} */
	public void setTechnologyInfo(java.lang.String technologyInfo) {
		this.technologyInfo = technologyInfo;
	}

	/** get 科技升级结束时间 */
	public java.util.Date getTechnologyTime() {
		return technologyTime;
	}

	/** set 科技升级结束时间 */
	public void setTechnologyTime(java.util.Date technologyTime) {
		this.technologyTime = technologyTime;
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
		return new String[] {"`playerId`", "`level`", "`state`", "`levelTime`", "`technologyInfo`", "`technologyTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getLevel(), getState(), getLevelTime(), getTechnologyInfo(), getTechnologyTime(), getIsDel() };
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
