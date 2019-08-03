package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbEquipInfoPo extends BasePo {


	/** 装备ID */
	private java.lang.Integer id = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 配置ID */
	private java.lang.Integer configId = 0;
	/** 装备数量 */
	private java.lang.Integer countNum = 0;
	/** 依附到武将ID */
	private java.lang.Integer heroId = 0;
	/** 0=未穿戴;1=穿戴;2=羁绊 */
	private java.lang.Integer action = 0;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;
	/** 强化等级 */
	private java.lang.Short strengthenLevel = 0;

	/** get 装备ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 装备ID */
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

	/** get 配置ID */
	public java.lang.Integer getConfigId() {
		return configId;
	}

	/** set 配置ID */
	public void setConfigId(java.lang.Integer configId) {
		this.configId = configId;
	}

	/** get 装备数量 */
	public java.lang.Integer getCountNum() {
		return countNum;
	}

	/** set 装备数量 */
	public void setCountNum(java.lang.Integer countNum) {
		this.countNum = countNum;
	}

	/** get 依附到武将ID */
	public java.lang.Integer getHeroId() {
		return heroId;
	}

	/** set 依附到武将ID */
	public void setHeroId(java.lang.Integer heroId) {
		this.heroId = heroId;
	}

	/** get 0=未穿戴;1=穿戴;2=羁绊 */
	public java.lang.Integer getAction() {
		return action;
	}

	/** set 0=未穿戴;1=穿戴;2=羁绊 */
	public void setAction(java.lang.Integer action) {
		this.action = action;
	}

	/** get 0=未删除;1=删除 */
	public java.lang.Boolean getIsDel() {
		return isDel;
	}

	/** set 0=未删除;1=删除 */
	public void setIsDel(java.lang.Boolean isDel) {
		this.isDel = isDel;
	}

	/** get 强化等级 */
	public java.lang.Short getStrengthenLevel() {
		return strengthenLevel;
	}

	/** set 强化等级 */
	public void setStrengthenLevel(java.lang.Short strengthenLevel) {
		this.strengthenLevel = strengthenLevel;
	}

	public java.lang.Short isStrengthenLevel(){
		return strengthenLevel;
	}

	@Override
	public String[] props() {
		return new String[] {"`id`", "`playerId`", "`configId`", "`countNum`", "`heroId`", "`action`", "`isDel`", "`strengthenLevel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getPlayerId(), getConfigId(), getCountNum(), getHeroId(), getAction(), getIsDel(), getStrengthenLevel() };
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
