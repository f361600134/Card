package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbOfflineHeroPo extends BasePo {


	/** 武将ID */
	private java.lang.Integer id = null;
	/** 服务器Id */
	private java.lang.Integer serverId = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 配置ID */
	private java.lang.Integer configId = 0;
	/** 已激活天赋 */
	private java.lang.Integer talentId = 0;
	/** 当前星级 */
	private java.lang.Integer starLevel = 0;
	/** 战力 */
	private java.lang.Integer fc = null;
	/** 战斗总属性 */
	private java.lang.String attrs = null;
	/** 当前天赋已激活星格列表[星格id,星格id] */
	private java.lang.String starStates = null;
	/** 进入缓存时间 */
	private java.lang.Long createTime = null;
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

	/** get 服务器Id */
	public java.lang.Integer getServerId() {
		return serverId;
	}

	/** set 服务器Id */
	public void setServerId(java.lang.Integer serverId) {
		this.serverId = serverId;
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
	public java.lang.Integer getTalentId() {
		return talentId;
	}

	/** set 已激活天赋 */
	public void setTalentId(java.lang.Integer talentId) {
		this.talentId = talentId;
	}

	/** get 当前星级 */
	public java.lang.Integer getStarLevel() {
		return starLevel;
	}

	/** set 当前星级 */
	public void setStarLevel(java.lang.Integer starLevel) {
		this.starLevel = starLevel;
	}

	/** get 战力 */
	public java.lang.Integer getFc() {
		return fc;
	}

	/** set 战力 */
	public void setFc(java.lang.Integer fc) {
		this.fc = fc;
	}

	/** get 战斗总属性 */
	public java.lang.String getAttrs() {
		return attrs;
	}

	/** set 战斗总属性 */
	public void setAttrs(java.lang.String attrs) {
		this.attrs = attrs;
	}

	/** get 当前天赋已激活星格列表[星格id,星格id] */
	public java.lang.String getStarStates() {
		return starStates;
	}

	/** set 当前天赋已激活星格列表[星格id,星格id] */
	public void setStarStates(java.lang.String starStates) {
		this.starStates = starStates;
	}

	/** get 进入缓存时间 */
	public java.lang.Long getCreateTime() {
		return createTime;
	}

	/** set 进入缓存时间 */
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
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
		return new String[] {"`id`", "`serverId`", "`playerId`", "`configId`", "`talentId`", "`starLevel`", "`fc`", "`attrs`", "`starStates`", "`createTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getServerId(), getPlayerId(), getConfigId(), getTalentId(), getStarLevel(), getFc(), getAttrs(), getStarStates(), getCreateTime(), getIsDel() };
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
