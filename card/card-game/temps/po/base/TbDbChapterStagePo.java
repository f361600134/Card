package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbChapterStagePo extends BasePo {


	/** 章节配置ID */
	private java.lang.Integer chapterConfigId = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 章节的宝箱(0未达成,1未领取,2已领取) */
	private java.lang.Integer boxState = 0;
	/** 章节的副本:{stageConfigId:starNum,stageConfigId:starNum} */
	private java.lang.String stageInfo = null;
	/** 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	private java.lang.String stageFightCount = null;
	/** 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	private java.lang.String stageBuyCount = null;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 章节配置ID */
	public java.lang.Integer getChapterConfigId() {
		return chapterConfigId;
	}

	/** set 章节配置ID */
	public void setChapterConfigId(java.lang.Integer chapterConfigId) {
		this.chapterConfigId = chapterConfigId;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 章节的宝箱(0未达成,1未领取,2已领取) */
	public java.lang.Integer getBoxState() {
		return boxState;
	}

	/** set 章节的宝箱(0未达成,1未领取,2已领取) */
	public void setBoxState(java.lang.Integer boxState) {
		this.boxState = boxState;
	}

	/** get 章节的副本:{stageConfigId:starNum,stageConfigId:starNum} */
	public java.lang.String getStageInfo() {
		return stageInfo;
	}

	/** set 章节的副本:{stageConfigId:starNum,stageConfigId:starNum} */
	public void setStageInfo(java.lang.String stageInfo) {
		this.stageInfo = stageInfo;
	}

	/** get 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	public java.lang.String getStageFightCount() {
		return stageFightCount;
	}

	/** set 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	public void setStageFightCount(java.lang.String stageFightCount) {
		this.stageFightCount = stageFightCount;
	}

	/** get 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	public java.lang.String getStageBuyCount() {
		return stageBuyCount;
	}

	/** set 精英副本今日已战斗次数:{stageConfigId:fightCount,stageConfigId:fightCount} */
	public void setStageBuyCount(java.lang.String stageBuyCount) {
		this.stageBuyCount = stageBuyCount;
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
		return new String[] {"`chapterConfigId`", "`playerId`", "`boxState`", "`stageInfo`", "`stageFightCount`", "`stageBuyCount`", "`resetTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getChapterConfigId(), getPlayerId(), getBoxState(), getStageInfo(), getStageFightCount(), getStageBuyCount(), getResetTime(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`chapter_config_id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { chapterConfigId, playerId };
	}
}
