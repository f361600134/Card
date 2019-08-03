package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbPropsInfoPo extends BasePo {


	/** 道具ID */
	private java.lang.Integer id = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 配置ID */
	private java.lang.Integer configId = 0;
	/** 道具数量 */
	private java.lang.Integer countNum = 0;
	/** 镶嵌到装备ID,0=未镶嵌 */
	private java.lang.Integer equipId = 0;
	/** 镶嵌到装备的位置 */
	private java.lang.Integer equipIndex = 0;
	/** 宝石经验 */
	private java.lang.Integer exp = 0;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 道具ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 道具ID */
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

	/** get 道具数量 */
	public java.lang.Integer getCountNum() {
		return countNum;
	}

	/** set 道具数量 */
	public void setCountNum(java.lang.Integer countNum) {
		this.countNum = countNum;
	}

	/** get 镶嵌到装备ID,0=未镶嵌 */
	public java.lang.Integer getEquipId() {
		return equipId;
	}

	/** set 镶嵌到装备ID,0=未镶嵌 */
	public void setEquipId(java.lang.Integer equipId) {
		this.equipId = equipId;
	}

	/** get 镶嵌到装备的位置 */
	public java.lang.Integer getEquipIndex() {
		return equipIndex;
	}

	/** set 镶嵌到装备的位置 */
	public void setEquipIndex(java.lang.Integer equipIndex) {
		this.equipIndex = equipIndex;
	}

	/** get 宝石经验 */
	public java.lang.Integer getExp() {
		return exp;
	}

	/** set 宝石经验 */
	public void setExp(java.lang.Integer exp) {
		this.exp = exp;
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
		return new String[] {"`id`", "`playerId`", "`configId`", "`countNum`", "`equipId`", "`equipIndex`", "`exp`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getPlayerId(), getConfigId(), getCountNum(), getEquipId(), getEquipIndex(), getExp(), getIsDel() };
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
