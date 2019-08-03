package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbActivityInfoPo extends BasePo {


	/** 玩家id */
	private java.lang.Integer playerId = null;
	/** 活动id */
	private java.lang.Short activityId = null;
	/** 活动信息, 根据活动去组装 */
	private java.lang.String activityInfo = null;
	/** 活动版本 */
	private java.lang.Short version = null;
	/** 领取信息, {id:count} */
	private java.lang.String rewardStateInfo = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 玩家id */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 玩家id */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 活动id */
	public java.lang.Short getActivityId() {
		return activityId;
	}

	/** set 活动id */
	public void setActivityId(java.lang.Short activityId) {
		this.activityId = activityId;
	}

	public java.lang.Short isActivityId(){
		return activityId;
	}

	/** get 活动信息, 根据活动去组装 */
	public java.lang.String getActivityInfo() {
		return activityInfo;
	}

	/** set 活动信息, 根据活动去组装 */
	public void setActivityInfo(java.lang.String activityInfo) {
		this.activityInfo = activityInfo;
	}

	/** get 活动版本 */
	public java.lang.Short getVersion() {
		return version;
	}

	/** set 活动版本 */
	public void setVersion(java.lang.Short version) {
		this.version = version;
	}

	public java.lang.Short isVersion(){
		return version;
	}

	/** get 领取信息, {id:count} */
	public java.lang.String getRewardStateInfo() {
		return rewardStateInfo;
	}

	/** set 领取信息, {id:count} */
	public void setRewardStateInfo(java.lang.String rewardStateInfo) {
		this.rewardStateInfo = rewardStateInfo;
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
		return new String[] {"`playerId`", "`activityId`", "`activityInfo`", "`version`", "`rewardStateInfo`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getActivityId(), getActivityInfo(), getVersion(), getRewardStateInfo(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`activity_id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { activityId, playerId };
	}
}
