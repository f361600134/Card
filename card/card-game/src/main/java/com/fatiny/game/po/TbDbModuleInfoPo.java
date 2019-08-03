package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbModuleInfoPo extends BasePo {


	/** 服务器ID */
	private java.lang.Integer serverId = null;
	/** 模块ID */
	private java.lang.Integer moduleId = null;
	/** 模块内容 */
	private java.lang.String content = null;
	/** 更改时间1 */
	private java.util.Date updateTime1 = null;
	/** 更改时间2 */
	private java.util.Date updateTime2 = null;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 服务器ID */
	public java.lang.Integer getServerId() {
		return serverId;
	}

	/** set 服务器ID */
	public void setServerId(java.lang.Integer serverId) {
		this.serverId = serverId;
	}

	/** get 模块ID */
	public java.lang.Integer getModuleId() {
		return moduleId;
	}

	/** set 模块ID */
	public void setModuleId(java.lang.Integer moduleId) {
		this.moduleId = moduleId;
	}

	/** get 模块内容 */
	public java.lang.String getContent() {
		return content;
	}

	/** set 模块内容 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/** get 更改时间1 */
	public java.util.Date getUpdateTime1() {
		return updateTime1;
	}

	/** set 更改时间1 */
	public void setUpdateTime1(java.util.Date updateTime1) {
		this.updateTime1 = updateTime1;
	}

	/** get 更改时间2 */
	public java.util.Date getUpdateTime2() {
		return updateTime2;
	}

	/** set 更改时间2 */
	public void setUpdateTime2(java.util.Date updateTime2) {
		this.updateTime2 = updateTime2;
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
		return new String[] {"`serverId`", "`moduleId`", "`content`", "`updateTime1`", "`updateTime2`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getServerId(), getModuleId(), getContent(), getUpdateTime1(), getUpdateTime2(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`module_id`", "`server_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { moduleId, serverId };
	}
}
