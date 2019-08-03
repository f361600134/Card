package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbBuildingMinefieldPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 矿场ID */
	private java.lang.Integer configId = null;
	/** 矿场等级 */
	private java.lang.Short level = 0;
	/** 矿场状态(0=未解锁;1=待升级;2=升级中,可加速) */
	private java.lang.Integer state = 0;
	/** 开始升级时间 */
	private java.util.Date levelTime = null;
	/** 开始生产时间 */
	private java.util.Date produceTime = null;
	/** 已生产资源 */
	private java.lang.Integer produceMine = 0;
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

	/** get 矿场ID */
	public java.lang.Integer getConfigId() {
		return configId;
	}

	/** set 矿场ID */
	public void setConfigId(java.lang.Integer configId) {
		this.configId = configId;
	}

	/** get 矿场等级 */
	public java.lang.Short getLevel() {
		return level;
	}

	/** set 矿场等级 */
	public void setLevel(java.lang.Short level) {
		this.level = level;
	}

	public java.lang.Short isLevel(){
		return level;
	}

	/** get 矿场状态(0=未解锁;1=待升级;2=升级中,可加速) */
	public java.lang.Integer getState() {
		return state;
	}

	/** set 矿场状态(0=未解锁;1=待升级;2=升级中,可加速) */
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

	/** get 开始生产时间 */
	public java.util.Date getProduceTime() {
		return produceTime;
	}

	/** set 开始生产时间 */
	public void setProduceTime(java.util.Date produceTime) {
		this.produceTime = produceTime;
	}

	/** get 已生产资源 */
	public java.lang.Integer getProduceMine() {
		return produceMine;
	}

	/** set 已生产资源 */
	public void setProduceMine(java.lang.Integer produceMine) {
		this.produceMine = produceMine;
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
		return new String[] {"`playerId`", "`configId`", "`level`", "`state`", "`levelTime`", "`produceTime`", "`produceMine`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getConfigId(), getLevel(), getState(), getLevelTime(), getProduceTime(), getProduceMine(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`config_id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { configId, playerId };
	}
}
