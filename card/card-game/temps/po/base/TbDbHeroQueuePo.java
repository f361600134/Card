package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbHeroQueuePo extends BasePo {


	/** 阵型配置ID */
	private java.lang.Integer configId = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** {位置(0/1/2/3/4):武将ID} */
	private java.lang.String heroIds = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 阵型配置ID */
	public java.lang.Integer getConfigId() {
		return configId;
	}

	/** set 阵型配置ID */
	public void setConfigId(java.lang.Integer configId) {
		this.configId = configId;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
	}

	/** get {位置(0/1/2/3/4):武将ID} */
	public java.lang.String getHeroIds() {
		return heroIds;
	}

	/** set {位置(0/1/2/3/4):武将ID} */
	public void setHeroIds(java.lang.String heroIds) {
		this.heroIds = heroIds;
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
		return new String[] {"`configId`", "`playerId`", "`heroIds`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getConfigId(), getPlayerId(), getHeroIds(), getIsDel() };
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
