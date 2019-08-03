package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbAltarInfoPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 金币祭坛已使用免费次数 */
	private java.lang.Integer goldFreeusedTime = 0;
	/** 金币祭坛购买次数,上限值100 */
	private java.lang.Integer goldBuyTime = 0;
	/** 金币祭坛购买消耗元宝数 */
	private java.lang.Integer goldConsume = 0;
	/** 装备祭坛已使用免费次数 */
	private java.lang.Integer equipFreeusedTime = 0;
	/** 装备祭坛购买次数,上限值100 */
	private java.lang.Integer equipBuyTime = 0;
	/** 装备祭坛购买消耗元宝数 */
	private java.lang.Integer equipConsume = 0;
	/** 星魂祭坛已使用免费次数 */
	private java.lang.Integer ghostFreeusedTime = 0;
	/** 星魂祭坛购买次数,上限值100 */
	private java.lang.Integer ghostBuyTime = 0;
	/** 星魂祭坛购买消耗元宝数 */
	private java.lang.Integer ghostConsume = 0;
	/** 宝石祭坛正使用的组 */
	private java.lang.Integer gemGroupId = 0;
	/** 宝石祭坛已使用免费次数 */
	private java.lang.Integer gemFreeusedTime = 0;
	/** 宝石祭坛购买次数,上限值100 */
	private java.lang.Integer gemBuyTime = 0;
	/** 宝石祭坛购买消耗元宝数 */
	private java.lang.Integer gemConsume = 0;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
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

	/** get 金币祭坛已使用免费次数 */
	public java.lang.Integer getGoldFreeusedTime() {
		return goldFreeusedTime;
	}

	/** set 金币祭坛已使用免费次数 */
	public void setGoldFreeusedTime(java.lang.Integer goldFreeusedTime) {
		this.goldFreeusedTime = goldFreeusedTime;
	}

	/** get 金币祭坛购买次数,上限值100 */
	public java.lang.Integer getGoldBuyTime() {
		return goldBuyTime;
	}

	/** set 金币祭坛购买次数,上限值100 */
	public void setGoldBuyTime(java.lang.Integer goldBuyTime) {
		this.goldBuyTime = goldBuyTime;
	}

	/** get 金币祭坛购买消耗元宝数 */
	public java.lang.Integer getGoldConsume() {
		return goldConsume;
	}

	/** set 金币祭坛购买消耗元宝数 */
	public void setGoldConsume(java.lang.Integer goldConsume) {
		this.goldConsume = goldConsume;
	}

	/** get 装备祭坛已使用免费次数 */
	public java.lang.Integer getEquipFreeusedTime() {
		return equipFreeusedTime;
	}

	/** set 装备祭坛已使用免费次数 */
	public void setEquipFreeusedTime(java.lang.Integer equipFreeusedTime) {
		this.equipFreeusedTime = equipFreeusedTime;
	}

	/** get 装备祭坛购买次数,上限值100 */
	public java.lang.Integer getEquipBuyTime() {
		return equipBuyTime;
	}

	/** set 装备祭坛购买次数,上限值100 */
	public void setEquipBuyTime(java.lang.Integer equipBuyTime) {
		this.equipBuyTime = equipBuyTime;
	}

	/** get 装备祭坛购买消耗元宝数 */
	public java.lang.Integer getEquipConsume() {
		return equipConsume;
	}

	/** set 装备祭坛购买消耗元宝数 */
	public void setEquipConsume(java.lang.Integer equipConsume) {
		this.equipConsume = equipConsume;
	}

	/** get 星魂祭坛已使用免费次数 */
	public java.lang.Integer getGhostFreeusedTime() {
		return ghostFreeusedTime;
	}

	/** set 星魂祭坛已使用免费次数 */
	public void setGhostFreeusedTime(java.lang.Integer ghostFreeusedTime) {
		this.ghostFreeusedTime = ghostFreeusedTime;
	}

	/** get 星魂祭坛购买次数,上限值100 */
	public java.lang.Integer getGhostBuyTime() {
		return ghostBuyTime;
	}

	/** set 星魂祭坛购买次数,上限值100 */
	public void setGhostBuyTime(java.lang.Integer ghostBuyTime) {
		this.ghostBuyTime = ghostBuyTime;
	}

	/** get 星魂祭坛购买消耗元宝数 */
	public java.lang.Integer getGhostConsume() {
		return ghostConsume;
	}

	/** set 星魂祭坛购买消耗元宝数 */
	public void setGhostConsume(java.lang.Integer ghostConsume) {
		this.ghostConsume = ghostConsume;
	}

	/** get 宝石祭坛正使用的组 */
	public java.lang.Integer getGemGroupId() {
		return gemGroupId;
	}

	/** set 宝石祭坛正使用的组 */
	public void setGemGroupId(java.lang.Integer gemGroupId) {
		this.gemGroupId = gemGroupId;
	}

	/** get 宝石祭坛已使用免费次数 */
	public java.lang.Integer getGemFreeusedTime() {
		return gemFreeusedTime;
	}

	/** set 宝石祭坛已使用免费次数 */
	public void setGemFreeusedTime(java.lang.Integer gemFreeusedTime) {
		this.gemFreeusedTime = gemFreeusedTime;
	}

	/** get 宝石祭坛购买次数,上限值100 */
	public java.lang.Integer getGemBuyTime() {
		return gemBuyTime;
	}

	/** set 宝石祭坛购买次数,上限值100 */
	public void setGemBuyTime(java.lang.Integer gemBuyTime) {
		this.gemBuyTime = gemBuyTime;
	}

	/** get 宝石祭坛购买消耗元宝数 */
	public java.lang.Integer getGemConsume() {
		return gemConsume;
	}

	/** set 宝石祭坛购买消耗元宝数 */
	public void setGemConsume(java.lang.Integer gemConsume) {
		this.gemConsume = gemConsume;
	}

	/** get 最近重置时间 */
	public java.util.Date getResetTime() {
		return resetTime;
	}

	/** set 最近重置时间 */
	public void setResetTime(java.util.Date resetTime) {
		this.resetTime = resetTime;
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
		return new String[] {"`playerId`", "`goldFreeusedTime`", "`goldBuyTime`", "`goldConsume`", "`equipFreeusedTime`", "`equipBuyTime`", "`equipConsume`", "`ghostFreeusedTime`", "`ghostBuyTime`", "`ghostConsume`", "`gemGroupId`", "`gemFreeusedTime`", "`gemBuyTime`", "`gemConsume`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getGoldFreeusedTime(), getGoldBuyTime(), getGoldConsume(), getEquipFreeusedTime(), getEquipBuyTime(), getEquipConsume(), getGhostFreeusedTime(), getGhostBuyTime(), getGhostConsume(), getGemGroupId(), getGemFreeusedTime(), getGemBuyTime(), getGemConsume(), getResetTime(), getIsDel() };
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
