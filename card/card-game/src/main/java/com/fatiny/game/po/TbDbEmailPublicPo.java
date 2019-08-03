package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbEmailPublicPo extends BasePo {


	/** 服务器ID */
	private java.lang.Integer serverId = null;
	/** 邮件模板ID */
	private java.lang.Integer id = null;
	/** 邮件标题 */
	private java.lang.String title = null;
	/** 邮件内容 */
	private java.lang.String content = null;
	/** 奖励:{configId:num,configId:num} */
	private java.lang.String rewards = null;
	/** 邮件领取后保存天数 */
	private java.lang.Integer expiredDays = null;
	/** 有效开始时间 */
	private java.util.Date validStartTime = null;
	/** 有效结束时间 */
	private java.util.Date validEndTime = null;
	/** 注册开始时间 */
	private java.util.Date createStartTime = null;
	/** 注册结束时间 */
	private java.util.Date createEndTime = null;
	/** 最低等级 */
	private java.lang.Integer startLevel = null;
	/** 最高等级 */
	private java.lang.Integer endLevel = null;
	/** 阵营要求:0=所有;1=魏;2=蜀;3=吴 */
	private java.lang.Integer camp = null;
	/** 邮件模板创建时间 */
	private java.util.Date createTime = null;
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

	/** get 邮件模板ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 邮件模板ID */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/** get 邮件标题 */
	public java.lang.String getTitle() {
		return title;
	}

	/** set 邮件标题 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/** get 邮件内容 */
	public java.lang.String getContent() {
		return content;
	}

	/** set 邮件内容 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/** get 奖励:{configId:num,configId:num} */
	public java.lang.String getRewards() {
		return rewards;
	}

	/** set 奖励:{configId:num,configId:num} */
	public void setRewards(java.lang.String rewards) {
		this.rewards = rewards;
	}

	/** get 邮件领取后保存天数 */
	public java.lang.Integer getExpiredDays() {
		return expiredDays;
	}

	/** set 邮件领取后保存天数 */
	public void setExpiredDays(java.lang.Integer expiredDays) {
		this.expiredDays = expiredDays;
	}

	/** get 有效开始时间 */
	public java.util.Date getValidStartTime() {
		return validStartTime;
	}

	/** set 有效开始时间 */
	public void setValidStartTime(java.util.Date validStartTime) {
		this.validStartTime = validStartTime;
	}

	/** get 有效结束时间 */
	public java.util.Date getValidEndTime() {
		return validEndTime;
	}

	/** set 有效结束时间 */
	public void setValidEndTime(java.util.Date validEndTime) {
		this.validEndTime = validEndTime;
	}

	/** get 注册开始时间 */
	public java.util.Date getCreateStartTime() {
		return createStartTime;
	}

	/** set 注册开始时间 */
	public void setCreateStartTime(java.util.Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	/** get 注册结束时间 */
	public java.util.Date getCreateEndTime() {
		return createEndTime;
	}

	/** set 注册结束时间 */
	public void setCreateEndTime(java.util.Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	/** get 最低等级 */
	public java.lang.Integer getStartLevel() {
		return startLevel;
	}

	/** set 最低等级 */
	public void setStartLevel(java.lang.Integer startLevel) {
		this.startLevel = startLevel;
	}

	/** get 最高等级 */
	public java.lang.Integer getEndLevel() {
		return endLevel;
	}

	/** set 最高等级 */
	public void setEndLevel(java.lang.Integer endLevel) {
		this.endLevel = endLevel;
	}

	/** get 阵营要求:0=所有;1=魏;2=蜀;3=吴 */
	public java.lang.Integer getCamp() {
		return camp;
	}

	/** set 阵营要求:0=所有;1=魏;2=蜀;3=吴 */
	public void setCamp(java.lang.Integer camp) {
		this.camp = camp;
	}

	/** get 邮件模板创建时间 */
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/** set 邮件模板创建时间 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
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
		return new String[] {"`serverId`", "`id`", "`title`", "`content`", "`rewards`", "`expiredDays`", "`validStartTime`", "`validEndTime`", "`createStartTime`", "`createEndTime`", "`startLevel`", "`endLevel`", "`camp`", "`createTime`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getServerId(), getId(), getTitle(), getContent(), getRewards(), getExpiredDays(), getValidStartTime(), getValidEndTime(), getCreateStartTime(), getCreateEndTime(), getStartLevel(), getEndLevel(), getCamp(), getCreateTime(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`id`", "`server_id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { id, serverId };
	}
}
