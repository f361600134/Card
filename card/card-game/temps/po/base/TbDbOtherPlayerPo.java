package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbOtherPlayerPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 角色类型 */
	private java.lang.Integer roleType = 108;
	/** 昵称 */
	private java.lang.String name = null;
	/** 1=魏;2=蜀;3=吴 */
	private java.lang.Integer camp = 1;
	/** 皇城官位 */
	private java.lang.Integer gmLevel = 1;
	/** 等级 */
	private java.lang.Short level = 1;
	/** 战力 */
	private java.lang.Integer fc = null;
	/** [武将ID] */
	private java.lang.String heroIds = null;
	/** 进入缓存时间 */
	private java.lang.Long createTime = null;
	/** vip等级 */
	private java.lang.Integer vipLevel = null;
	/** 最后登录时间 */
	private java.util.Date lastLoginTime = null;
	/** 主公技能列表 */
	private java.lang.String fightSkills = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 角色类型 */
	public java.lang.Integer getRoleType() {
		return roleType;
	}

	/** set 角色类型 */
	public void setRoleType(java.lang.Integer roleType) {
		this.roleType = roleType;
	}

	/** get 昵称 */
	public java.lang.String getName() {
		return name;
	}

	/** set 昵称 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/** get 1=魏;2=蜀;3=吴 */
	public java.lang.Integer getCamp() {
		return camp;
	}

	/** set 1=魏;2=蜀;3=吴 */
	public void setCamp(java.lang.Integer camp) {
		this.camp = camp;
	}

	/** get 皇城官位 */
	public java.lang.Integer getGmLevel() {
		return gmLevel;
	}

	/** set 皇城官位 */
	public void setGmLevel(java.lang.Integer gmLevel) {
		this.gmLevel = gmLevel;
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

	/** get 战力 */
	public java.lang.Integer getFc() {
		return fc;
	}

	/** set 战力 */
	public void setFc(java.lang.Integer fc) {
		this.fc = fc;
	}

	/** get [武将ID] */
	public java.lang.String getHeroIds() {
		return heroIds;
	}

	/** set [武将ID] */
	public void setHeroIds(java.lang.String heroIds) {
		this.heroIds = heroIds;
	}

	/** get 进入缓存时间 */
	public java.lang.Long getCreateTime() {
		return createTime;
	}

	/** set 进入缓存时间 */
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	/** get vip等级 */
	public java.lang.Integer getVipLevel() {
		return vipLevel;
	}

	/** set vip等级 */
	public void setVipLevel(java.lang.Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	/** get 最后登录时间 */
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	/** set 最后登录时间 */
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/** get 主公技能列表 */
	public java.lang.String getFightSkills() {
		return fightSkills;
	}

	/** set 主公技能列表 */
	public void setFightSkills(java.lang.String fightSkills) {
		this.fightSkills = fightSkills;
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
		return new String[] {"`curServerId`", "`playerId`", "`roleType`", "`name`", "`camp`", "`gmLevel`", "`level`", "`fc`", "`heroIds`", "`createTime`", "`vipLevel`", "`lastLoginTime`", "`fightSkills`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getRoleType(), getName(), getCamp(), getGmLevel(), getLevel(), getFc(), getHeroIds(), getCreateTime(), getVipLevel(), getLastLoginTime(), getFightSkills(), getIsDel() };
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
