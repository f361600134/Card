package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbHeroInfoPo extends BasePo {


	/** 武将ID */
	private java.lang.Integer id = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 配置ID */
	private java.lang.Integer configId = 0;
	/** 已激活天赋 */
	private java.lang.Integer talent = 0;
	/** 当前天赋已激活星格列表 */
	private java.lang.String starStates = null;
	/** 羁绊到武将ID,0=未羁绊 */
	private java.lang.Integer heroId = 0;
	/** 已激活羁绊:{羁绊id:[武将id,装备id]} */
	private java.lang.String relationGroups = null;
	/** 培养的消耗:{configId:num,configId:num} */
	private java.lang.String usedMaterial = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 武将ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 武将ID */
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

	/** get 已激活天赋 */
	public java.lang.Integer getTalent() {
		return talent;
	}

	/** set 已激活天赋 */
	public void setTalent(java.lang.Integer talent) {
		this.talent = talent;
	}

	/** get 当前天赋已激活星格列表 */
	public java.lang.String getStarStates() {
		return starStates;
	}

	/** set 当前天赋已激活星格列表 */
	public void setStarStates(java.lang.String starStates) {
		this.starStates = starStates;
	}

	/** get 羁绊到武将ID,0=未羁绊 */
	public java.lang.Integer getHeroId() {
		return heroId;
	}

	/** set 羁绊到武将ID,0=未羁绊 */
	public void setHeroId(java.lang.Integer heroId) {
		this.heroId = heroId;
	}

	/** get 已激活羁绊:{羁绊id:[武将id,装备id]} */
	public java.lang.String getRelationGroups() {
		return relationGroups;
	}

	/** set 已激活羁绊:{羁绊id:[武将id,装备id]} */
	public void setRelationGroups(java.lang.String relationGroups) {
		this.relationGroups = relationGroups;
	}

	/** get 培养的消耗:{configId:num,configId:num} */
	public java.lang.String getUsedMaterial() {
		return usedMaterial;
	}

	/** set 培养的消耗:{configId:num,configId:num} */
	public void setUsedMaterial(java.lang.String usedMaterial) {
		this.usedMaterial = usedMaterial;
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
		return new String[] {"`id`", "`playerId`", "`configId`", "`talent`", "`starStates`", "`heroId`", "`relationGroups`", "`usedMaterial`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getPlayerId(), getConfigId(), getTalent(), getStarStates(), getHeroId(), getRelationGroups(), getUsedMaterial(), getIsDel() };
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
