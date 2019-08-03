package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbEmailTempPo extends BasePo {


	/** 邮件ID */
	private java.lang.Integer id = null;
	/** 角色ID */
	private java.lang.Integer playerId = null;
	/** 邮件标题 */
	private java.lang.String title = null;
	/** 邮件内容 */
	private java.lang.String content = null;
	/** 奖励:{configId:num,configId:num} */
	private java.lang.String rewards = null;
	/** 邮件时间 */
	private java.util.Date emailTime = null;
	/** 邮件过期时间 */
	private java.util.Date endTime = null;
	/** 0=未读取;1=已读 */
	private java.lang.Boolean isRead = false;
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 邮件ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 邮件ID */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/** get 角色ID */
	public java.lang.Integer getPlayerId() {
		return playerId;
	}

	/** set 角色ID */
	public void setPlayerId(java.lang.Integer playerId) {
		this.playerId = playerId;
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

	/** get 邮件时间 */
	public java.util.Date getEmailTime() {
		return emailTime;
	}

	/** set 邮件时间 */
	public void setEmailTime(java.util.Date emailTime) {
		this.emailTime = emailTime;
	}

	/** get 邮件过期时间 */
	public java.util.Date getEndTime() {
		return endTime;
	}

	/** set 邮件过期时间 */
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	/** get 0=未读取;1=已读 */
	public java.lang.Boolean getIsRead() {
		return isRead;
	}

	/** set 0=未读取;1=已读 */
	public void setIsRead(java.lang.Boolean isRead) {
		this.isRead = isRead;
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
		return new String[] {"`id`", "`playerId`", "`title`", "`content`", "`rewards`", "`emailTime`", "`endTime`", "`isRead`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getPlayerId(), getTitle(), getContent(), getRewards(), getEmailTime(), getEndTime(), getIsRead(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { id };
	}
}
