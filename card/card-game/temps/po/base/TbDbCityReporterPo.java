package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbCityReporterPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 类型:0=防守;1=进攻 */
	private java.lang.Integer type = null;
	/** 类型:0输，1赢 */
	private java.lang.Integer result = null;
	/** 战斗对方角色ID */
	private java.lang.Integer opposePlayerId = null;
	/** 等级 */
	private java.lang.Short level = 1;
	/** 战力 */
	private java.lang.Integer fc = null;
	/** 创建时间 */
	private java.util.Date createTime = null;

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 类型:0=防守;1=进攻 */
	public java.lang.Integer getType() {
		return type;
	}

	/** set 类型:0=防守;1=进攻 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	/** get 类型:0输，1赢 */
	public java.lang.Integer getResult() {
		return result;
	}

	/** set 类型:0输，1赢 */
	public void setResult(java.lang.Integer result) {
		this.result = result;
	}

	/** get 战斗对方角色ID */
	public java.lang.Integer getOpposePlayerId() {
		return opposePlayerId;
	}

	/** set 战斗对方角色ID */
	public void setOpposePlayerId(java.lang.Integer opposePlayerId) {
		this.opposePlayerId = opposePlayerId;
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

	/** get 创建时间 */
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/** set 创建时间 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String[] props() {
		return new String[] {"`curServerId`", "`playerId`", "`type`", "`result`", "`opposePlayerId`", "`level`", "`fc`", "`createTime`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getPlayerId(), getType(), getResult(), getOpposePlayerId(), getLevel(), getFc(), getCreateTime() };
	}

	@Override
	public String[] ids() {
		return new String[] {"``"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] {  };
	}
}
