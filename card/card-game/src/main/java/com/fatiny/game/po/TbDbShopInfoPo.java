package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbShopInfoPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 秘宝商店今日已使用免费刷新次数,上限值100 */
	private java.lang.Integer treasureRefreshCount = 0;
	/** 秘宝商店今日已购买次数,上限值100 */
	private java.lang.Integer treasureBuyCount = 0;
	/** 秘宝商店购买记录:{id:count,id:count} */
	private java.lang.String treasureRecord = null;
	/** 秘宝物品列表:{id,id} */
	private java.lang.String treasureGoodIds = null;
	/** 秘宝物品放出比例:{id:rate,id:rate} */
	private java.lang.String treasureRateIds = null;
	/** 秘宝免费刷新CD开始倒计时时间 */
	private java.util.Date treasureCdTime = null;
	/** 秘宝最近重置时间 */
	private java.util.Date treasureResetTime = null;
	/** 军功商店购买记录:{id:count,id:count} */
	private java.lang.String militaryRecord = null;
	/** 军功最近重置时间 */
	private java.util.Date militaryResetTime = null;
	/** 战功商店购买记录:{id:count,id:count} */
	private java.lang.String battleRecord = null;
	/** 战功最近重置时间 */
	private java.util.Date battleResetTime = null;
	/** 玉璧商店购买记录:{id:count,id:count} */
	private java.lang.String jadeRecord = null;
	/** 战功最近重置时间 */
	private java.util.Date jadeResetTime = null;
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

	/** get 秘宝商店今日已使用免费刷新次数,上限值100 */
	public java.lang.Integer getTreasureRefreshCount() {
		return treasureRefreshCount;
	}

	/** set 秘宝商店今日已使用免费刷新次数,上限值100 */
	public void setTreasureRefreshCount(java.lang.Integer treasureRefreshCount) {
		this.treasureRefreshCount = treasureRefreshCount;
	}

	/** get 秘宝商店今日已购买次数,上限值100 */
	public java.lang.Integer getTreasureBuyCount() {
		return treasureBuyCount;
	}

	/** set 秘宝商店今日已购买次数,上限值100 */
	public void setTreasureBuyCount(java.lang.Integer treasureBuyCount) {
		this.treasureBuyCount = treasureBuyCount;
	}

	/** get 秘宝商店购买记录:{id:count,id:count} */
	public java.lang.String getTreasureRecord() {
		return treasureRecord;
	}

	/** set 秘宝商店购买记录:{id:count,id:count} */
	public void setTreasureRecord(java.lang.String treasureRecord) {
		this.treasureRecord = treasureRecord;
	}

	/** get 秘宝物品列表:{id,id} */
	public java.lang.String getTreasureGoodIds() {
		return treasureGoodIds;
	}

	/** set 秘宝物品列表:{id,id} */
	public void setTreasureGoodIds(java.lang.String treasureGoodIds) {
		this.treasureGoodIds = treasureGoodIds;
	}

	/** get 秘宝物品放出比例:{id:rate,id:rate} */
	public java.lang.String getTreasureRateIds() {
		return treasureRateIds;
	}

	/** set 秘宝物品放出比例:{id:rate,id:rate} */
	public void setTreasureRateIds(java.lang.String treasureRateIds) {
		this.treasureRateIds = treasureRateIds;
	}

	/** get 秘宝免费刷新CD开始倒计时时间 */
	public java.util.Date getTreasureCdTime() {
		return treasureCdTime;
	}

	/** set 秘宝免费刷新CD开始倒计时时间 */
	public void setTreasureCdTime(java.util.Date treasureCdTime) {
		this.treasureCdTime = treasureCdTime;
	}

	/** get 秘宝最近重置时间 */
	public java.util.Date getTreasureResetTime() {
		return treasureResetTime;
	}

	/** set 秘宝最近重置时间 */
	public void setTreasureResetTime(java.util.Date treasureResetTime) {
		this.treasureResetTime = treasureResetTime;
	}

	/** get 军功商店购买记录:{id:count,id:count} */
	public java.lang.String getMilitaryRecord() {
		return militaryRecord;
	}

	/** set 军功商店购买记录:{id:count,id:count} */
	public void setMilitaryRecord(java.lang.String militaryRecord) {
		this.militaryRecord = militaryRecord;
	}

	/** get 军功最近重置时间 */
	public java.util.Date getMilitaryResetTime() {
		return militaryResetTime;
	}

	/** set 军功最近重置时间 */
	public void setMilitaryResetTime(java.util.Date militaryResetTime) {
		this.militaryResetTime = militaryResetTime;
	}

	/** get 战功商店购买记录:{id:count,id:count} */
	public java.lang.String getBattleRecord() {
		return battleRecord;
	}

	/** set 战功商店购买记录:{id:count,id:count} */
	public void setBattleRecord(java.lang.String battleRecord) {
		this.battleRecord = battleRecord;
	}

	/** get 战功最近重置时间 */
	public java.util.Date getBattleResetTime() {
		return battleResetTime;
	}

	/** set 战功最近重置时间 */
	public void setBattleResetTime(java.util.Date battleResetTime) {
		this.battleResetTime = battleResetTime;
	}

	/** get 玉璧商店购买记录:{id:count,id:count} */
	public java.lang.String getJadeRecord() {
		return jadeRecord;
	}

	/** set 玉璧商店购买记录:{id:count,id:count} */
	public void setJadeRecord(java.lang.String jadeRecord) {
		this.jadeRecord = jadeRecord;
	}

	/** get 战功最近重置时间 */
	public java.util.Date getJadeResetTime() {
		return jadeResetTime;
	}

	/** set 战功最近重置时间 */
	public void setJadeResetTime(java.util.Date jadeResetTime) {
		this.jadeResetTime = jadeResetTime;
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
		return new String[] {"`playerId`", "`treasureRefreshCount`", "`treasureBuyCount`", "`treasureRecord`", "`treasureGoodIds`", "`treasureRateIds`", "`treasureCdTime`", "`treasureResetTime`", "`militaryRecord`", "`militaryResetTime`", "`battleRecord`", "`battleResetTime`", "`jadeRecord`", "`jadeResetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getTreasureRefreshCount(), getTreasureBuyCount(), getTreasureRecord(), getTreasureGoodIds(), getTreasureRateIds(), getTreasureCdTime(), getTreasureResetTime(), getMilitaryRecord(), getMilitaryResetTime(), getBattleRecord(), getBattleResetTime(), getJadeRecord(), getJadeResetTime(), getIsDel() };
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
