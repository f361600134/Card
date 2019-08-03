package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbContestInfoPo extends BasePo {


	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 1=青龙;2=白虎;3=朱雀,4=天榜,5=地榜 */
	private java.lang.Integer grouping = 1;
	/** 拥有者角色ID */
	private java.lang.Integer playerId = null;
	/** 分数, 预赛时根据此分做排名, 决赛时次分作为名次 */
	private java.lang.Integer score = null;
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

	/** get 1=青龙;2=白虎;3=朱雀,4=天榜,5=地榜 */
	public java.lang.Integer getGrouping() {
		return grouping;
	}

	/** set 1=青龙;2=白虎;3=朱雀,4=天榜,5=地榜 */
	public void setGrouping(java.lang.Integer grouping) {
		this.grouping = grouping;
	}

	/** get 拥有者角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 拥有者角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get 分数, 预赛时根据此分做排名, 决赛时次分作为名次 */
	public java.lang.Integer getScore() {
		return score;
	}

	/** set 分数, 预赛时根据此分做排名, 决赛时次分作为名次 */
	public void setScore(java.lang.Integer score) {
		this.score = score;
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
		return new String[] {"`curServerId`", "`grouping`", "`playerId`", "`score`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getCurServerId(), getGrouping(), getPlayerId(), getScore(), getIsDel() };
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
