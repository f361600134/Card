package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbArtifactInfoPo extends BasePo {


	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 神兵唯一id */
	private java.lang.Integer id = 0;
	/** 神兵配置id */
	private java.lang.Integer configId = 0;
	/** 所属英雄id */
	private java.lang.Integer heroId = 0;
	/** 等级 */
	private java.lang.Short level = 0;
	/** 状态,1=已打造未激活,2:可精炼,3:可突破 */
	private java.lang.Short state = 0;
	/** 培养的消耗:{configId:num,configId:num} */
	private java.lang.String usedMaterial = "";
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 神兵唯一id */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 神兵唯一id */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/** get 神兵配置id */
	public java.lang.Integer getConfigId() {
		return configId;
	}

	/** set 神兵配置id */
	public void setConfigId(java.lang.Integer configId) {
		this.configId = configId;
	}

	/** get 所属英雄id */
	public java.lang.Integer getHeroId() {
		return heroId;
	}

	/** set 所属英雄id */
	public void setHeroId(java.lang.Integer heroId) {
		this.heroId = heroId;
	}

	/** get 等级 */
	public java.lang.Short getLevel() {
		return level;
	}

	/** set 等级 */
	public void setLevel(java.lang.Short level) {
		this.level = level;
	}

	public java.lang.Short isLevel(){
		return level;
	}

	/** get 状态,1=已打造未激活,2:可精炼,3:可突破 */
	public java.lang.Short getState() {
		return state;
	}

	/** set 状态,1=已打造未激活,2:可精炼,3:可突破 */
	public void setState(java.lang.Short state) {
		this.state = state;
	}

	public java.lang.Short isState(){
		return state;
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
		return new String[] {"`playerId`", "`id`", "`configId`", "`heroId`", "`level`", "`state`", "`usedMaterial`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getId(), getConfigId(), getHeroId(), getLevel(), getState(), getUsedMaterial(), getIsDel() };
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
