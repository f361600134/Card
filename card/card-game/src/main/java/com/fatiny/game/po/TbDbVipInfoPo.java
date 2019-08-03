package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbVipInfoPo extends BasePo {


	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 特权信息, 特权id:剩余次数{id:count} */
	private java.lang.String privilegeInfo = null;
	/** 特权重置时间 */
	private java.util.Date resetTime = null;
	/** 专属礼包，vip:领取状态, {id:state} */
	private java.lang.String exclusiveReward = null;
	/** 双周循环礼包发送时间 */
	private java.util.Date cycleRewardTime = null;
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

	/** get 特权信息, 特权id:剩余次数{id:count} */
	public java.lang.String getPrivilegeInfo() {
		return privilegeInfo;
	}

	/** set 特权信息, 特权id:剩余次数{id:count} */
	public void setPrivilegeInfo(java.lang.String privilegeInfo) {
		this.privilegeInfo = privilegeInfo;
	}

	/** get 特权重置时间 */
	public java.util.Date getResetTime() {
		return resetTime;
	}

	/** set 特权重置时间 */
	public void setResetTime(java.util.Date resetTime) {
		this.resetTime = resetTime;
	}

	/** get 专属礼包，vip:领取状态, {id:state} */
	public java.lang.String getExclusiveReward() {
		return exclusiveReward;
	}

	/** set 专属礼包，vip:领取状态, {id:state} */
	public void setExclusiveReward(java.lang.String exclusiveReward) {
		this.exclusiveReward = exclusiveReward;
	}

	/** get 双周循环礼包发送时间 */
	public java.util.Date getCycleRewardTime() {
		return cycleRewardTime;
	}

	/** set 双周循环礼包发送时间 */
	public void setCycleRewardTime(java.util.Date cycleRewardTime) {
		this.cycleRewardTime = cycleRewardTime;
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
		return new String[] {"`playerId`", "`privilegeInfo`", "`resetTime`", "`exclusiveReward`", "`cycleRewardTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getPrivilegeInfo(), getResetTime(), getExclusiveReward(), getCycleRewardTime(), getIsDel() };
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
