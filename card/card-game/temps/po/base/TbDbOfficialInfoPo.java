package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbOfficialInfoPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 1=魏;2=蜀;3=吴 */
	private java.lang.Integer camp = 1;
	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 官位id */
	private java.lang.Integer officialId = null;
	/** 进入缓存时间 */
	private java.lang.Long createTime = null;
	/** 总收益 */
	private java.lang.Integer totalIncome = null;
	/** 收益刷新时间 */
	private java.lang.Long incomeTime = null;
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

	/** get 1=魏;2=蜀;3=吴 */
	public java.lang.Integer getCamp() {
		return camp;
	}

	/** set 1=魏;2=蜀;3=吴 */
	public void setCamp(java.lang.Integer camp) {
		this.camp = camp;
	}

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 官位id */
	public java.lang.Integer getOfficialId() {
		return officialId;
	}

	/** set 官位id */
	public void setOfficialId(java.lang.Integer officialId) {
		this.officialId = officialId;
	}

	/** get 进入缓存时间 */
	public java.lang.Long getCreateTime() {
		return createTime;
	}

	/** set 进入缓存时间 */
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	/** get 总收益 */
	public java.lang.Integer getTotalIncome() {
		return totalIncome;
	}

	/** set 总收益 */
	public void setTotalIncome(java.lang.Integer totalIncome) {
		this.totalIncome = totalIncome;
	}

	/** get 收益刷新时间 */
	public java.lang.Long getIncomeTime() {
		return incomeTime;
	}

	/** set 收益刷新时间 */
	public void setIncomeTime(java.lang.Long incomeTime) {
		this.incomeTime = incomeTime;
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
		return new String[] {"`curServerId`", "`camp`", "`playerId`", "`officialId`", "`createTime`", "`totalIncome`", "`incomeTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getCamp(), getPlayerId(), getOfficialId(), getCreateTime(), getTotalIncome(), getIncomeTime(), getIsDel() };
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
