package com.fatiny.cardloginplus.module.base;

import org.apache.commons.lang3.StringUtils;

import com.fatiny.cardloginplus.module.annotation.Filed;

/**
 * 抽出公共参数 参数抽象类
 * 乱世战纪游戏使用
 * @auth Jeremy
 * @date 2019年4月28日下午7:36:22
 */
public abstract class AbstractLoginParam {
	
	@Filed(isNull = true)
	private String channelUId;//账号id, 一般由渠道方生成
	private String channelUname; //账号, 一般由渠道方生成
	@Filed(isNull = true)
	private String inputUname;//玩家输入账号,用于记录
	
	private Integer ch;	//渠道
	private String os; //平台android, ios
	@Filed(isNull = true)
	private String pwd; //不是密码,是加密码，防止外部攻击
	
	public String getChannelUId() {
		return channelUId;
	}

	public void setChannelUId(String channelUId) {
		this.channelUId = channelUId;
	}

	public Integer getCh() {
		return ch;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public String getChannelUname() {
		return channelUname;
	}

	public void setChannelUname(String channelUname) {
		this.channelUname = channelUname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getInputUname() {
		if (!StringUtils.isBlank(inputUname)) {
			inputUname = getChannelUname();
		}
		return inputUname;
	}

	public void setInputUname(String inputUname) {
		this.inputUname = inputUname;
	}
	
}
