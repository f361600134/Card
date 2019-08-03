package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbBannedPlayerPo extends BasePo {


	/** 服务器id */
	private java.lang.Integer curServerId = null;
	/** 玩家id */
	private java.lang.Integer playerId = null;
	/** 禁止状态, 1:禁止登陆, 2:禁止发言 */
	private java.lang.Short state = null;
	/** 禁止开始时间 */
	private java.lang.Long startTime = null;
	/** 禁止时长(s) */
	private java.lang.Long bannedTime = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 服务器id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 服务器id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 玩家id */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 玩家id */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 禁止状态, 1:禁止登陆, 2:禁止发言 */
	public java.lang.Short getState() {
		return state;
	}

	/** set 禁止状态, 1:禁止登陆, 2:禁止发言 */
	public void setState(java.lang.Short state) {
		this.state = state;
	}

	public java.lang.Short isState(){
		return state;
	}

	/** get 禁止开始时间 */
	public java.lang.Long getStartTime() {
		return startTime;
	}

	/** set 禁止开始时间 */
	public void setStartTime(java.lang.Long startTime) {
		this.startTime = startTime;
	}

	/** get 禁止时长(s) */
	public java.lang.Long getBannedTime() {
		return bannedTime;
	}

	/** set 禁止时长(s) */
	public void setBannedTime(java.lang.Long bannedTime) {
		this.bannedTime = bannedTime;
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
		return new String[] {"`curServerId`", "`playerId`", "`state`", "`startTime`", "`bannedTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getState(), getStartTime(), getBannedTime(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`cur_server_id`", "`player_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { curServerId, playerId };
	}
}
