package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbPubInfoPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 首次抽卡0=否;1=是 */
	private java.lang.Boolean normalFirst = false;
	/** 良将免费计时开始时间 */
	private java.util.Date normalFreeStart = null;
	/** 良将抽高品质卡高概率剩余次数 */
	private java.lang.Integer normalRemainCount = 0;
	/** 首次抽卡0=否;1=是 */
	private java.lang.Boolean famousFirst = false;
	/** 名将免费计时开始时间 */
	private java.util.Date famousFreeStart = null;
	/** 名将抽高品质卡高概率剩余次数 */
	private java.lang.Integer famousRemainCount = 0;
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

	/** get 首次抽卡0=否;1=是 */
	public java.lang.Boolean getNormalFirst() {
		return normalFirst;
	}

	/** set 首次抽卡0=否;1=是 */
	public void setNormalFirst(java.lang.Boolean normalFirst) {
		this.normalFirst = normalFirst;
	}

	/** get 良将免费计时开始时间 */
	public java.util.Date getNormalFreeStart() {
		return normalFreeStart;
	}

	/** set 良将免费计时开始时间 */
	public void setNormalFreeStart(java.util.Date normalFreeStart) {
		this.normalFreeStart = normalFreeStart;
	}

	/** get 良将抽高品质卡高概率剩余次数 */
	public java.lang.Integer getNormalRemainCount() {
		return normalRemainCount;
	}

	/** set 良将抽高品质卡高概率剩余次数 */
	public void setNormalRemainCount(java.lang.Integer normalRemainCount) {
		this.normalRemainCount = normalRemainCount;
	}

	/** get 首次抽卡0=否;1=是 */
	public java.lang.Boolean getFamousFirst() {
		return famousFirst;
	}

	/** set 首次抽卡0=否;1=是 */
	public void setFamousFirst(java.lang.Boolean famousFirst) {
		this.famousFirst = famousFirst;
	}

	/** get 名将免费计时开始时间 */
	public java.util.Date getFamousFreeStart() {
		return famousFreeStart;
	}

	/** set 名将免费计时开始时间 */
	public void setFamousFreeStart(java.util.Date famousFreeStart) {
		this.famousFreeStart = famousFreeStart;
	}

	/** get 名将抽高品质卡高概率剩余次数 */
	public java.lang.Integer getFamousRemainCount() {
		return famousRemainCount;
	}

	/** set 名将抽高品质卡高概率剩余次数 */
	public void setFamousRemainCount(java.lang.Integer famousRemainCount) {
		this.famousRemainCount = famousRemainCount;
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
		return new String[] {"`playerId`", "`normalFirst`", "`normalFreeStart`", "`normalRemainCount`", "`famousFirst`", "`famousFreeStart`", "`famousRemainCount`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getPlayerId(), getNormalFirst(), getNormalFreeStart(), getNormalRemainCount(), getFamousFirst(), getFamousFreeStart(), getFamousRemainCount(), getIsDel() };
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
