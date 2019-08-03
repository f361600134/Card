package com.fatiny.cardloginplus.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account{
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	// 用户id
	@Column(name = "userName")
    private String userName;

	@Column(name = "password")
    private String password;

	@Column(name = "userKey")
    private String userKey;

	@Column(name = "channel")
    private Integer channel;

	@Column(name = "subChannel")
    private Integer subChannel;

	@Column(name = "regTime")
    private Date regTime;

	@Column(name = "lastTime")
    private Date lastTime;

	@Column(name = "os")
    private Integer os;

	@Column(name = "handset")
    private String handset;

	@Column(name = "isSuper")
    private Boolean isSuper;

	@Column(name = "isBan")
    private Boolean isBan;

	@Column(name = "banTxt")
    private String banTxt;

	@Column(name = "lastServer")
    private Integer lastServer;

	@Column(name = "channelUid")
    private String channelUid;

	@Column(name = "channelUname")
    private String channelUname;

	@Column(name = "inputUname")
    private String inputUname;

    private static final long serialVersionUID = 1L;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey == null ? null : userKey.trim();
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(Integer subChannel) {
        this.subChannel = subChannel;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getHandset() {
        return handset;
    }

    public void setHandset(String handset) {
        this.handset = handset == null ? null : handset.trim();
    }

    public Boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Boolean isSuper) {
        this.isSuper = isSuper;
    }

    public Boolean getIsBan() {
        return isBan;
    }

    public void setIsBan(Boolean isBan) {
        this.isBan = isBan;
    }

    public String getBanTxt() {
        return banTxt;
    }

    public void setBanTxt(String banTxt) {
        this.banTxt = banTxt == null ? null : banTxt.trim();
    }

    public Integer getLastServer() {
        return lastServer;
    }

    public void setLastServer(Integer lastServer) {
        this.lastServer = lastServer;
    }

    public String getChannelUid() {
        return channelUid;
    }

    public void setChannelUid(String channelUid) {
        this.channelUid = channelUid == null ? null : channelUid.trim();
    }

    public String getChannelUname() {
        return channelUname;
    }

    public void setChannelUname(String channelUname) {
        this.channelUname = channelUname == null ? null : channelUname.trim();
    }

    public String getInputUname() {
        return inputUname;
    }

    public void setInputUname(String inputUname) {
        this.inputUname = inputUname == null ? null : inputUname.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Account other = (Account) that;
        return (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getUserKey() == null ? other.getUserKey() == null : this.getUserKey().equals(other.getUserKey()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getSubChannel() == null ? other.getSubChannel() == null : this.getSubChannel().equals(other.getSubChannel()))
            && (this.getRegTime() == null ? other.getRegTime() == null : this.getRegTime().equals(other.getRegTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
            && (this.getHandset() == null ? other.getHandset() == null : this.getHandset().equals(other.getHandset()))
            && (this.getIsSuper() == null ? other.getIsSuper() == null : this.getIsSuper().equals(other.getIsSuper()))
            && (this.getIsBan() == null ? other.getIsBan() == null : this.getIsBan().equals(other.getIsBan()))
            && (this.getBanTxt() == null ? other.getBanTxt() == null : this.getBanTxt().equals(other.getBanTxt()))
            && (this.getLastServer() == null ? other.getLastServer() == null : this.getLastServer().equals(other.getLastServer()))
            && (this.getChannelUid() == null ? other.getChannelUid() == null : this.getChannelUid().equals(other.getChannelUid()))
            && (this.getChannelUname() == null ? other.getChannelUname() == null : this.getChannelUname().equals(other.getChannelUname()))
            && (this.getInputUname() == null ? other.getInputUname() == null : this.getInputUname().equals(other.getInputUname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getUserKey() == null) ? 0 : getUserKey().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getSubChannel() == null) ? 0 : getSubChannel().hashCode());
        result = prime * result + ((getRegTime() == null) ? 0 : getRegTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getHandset() == null) ? 0 : getHandset().hashCode());
        result = prime * result + ((getIsSuper() == null) ? 0 : getIsSuper().hashCode());
        result = prime * result + ((getIsBan() == null) ? 0 : getIsBan().hashCode());
        result = prime * result + ((getBanTxt() == null) ? 0 : getBanTxt().hashCode());
        result = prime * result + ((getLastServer() == null) ? 0 : getLastServer().hashCode());
        result = prime * result + ((getChannelUid() == null) ? 0 : getChannelUid().hashCode());
        result = prime * result + ((getChannelUname() == null) ? 0 : getChannelUname().hashCode());
        result = prime * result + ((getInputUname() == null) ? 0 : getInputUname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", userKey=").append(userKey);
        sb.append(", channel=").append(channel);
        sb.append(", subChannel=").append(subChannel);
        sb.append(", regTime=").append(regTime);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", os=").append(os);
        sb.append(", handset=").append(handset);
        sb.append(", isSuper=").append(isSuper);
        sb.append(", isBan=").append(isBan);
        sb.append(", banTxt=").append(banTxt);
        sb.append(", lastServer=").append(lastServer);
        sb.append(", channelUid=").append(channelUid);
        sb.append(", channelUname=").append(channelUname);
        sb.append(", inputUname=").append(inputUname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}