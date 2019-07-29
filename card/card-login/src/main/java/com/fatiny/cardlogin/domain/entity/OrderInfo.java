package com.fatiny.cardlogin.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fatiny.cardlogin.domain.OrderStatusEnum;

@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {
	
	@Column(name = "orderId")
    private Long orderId;

	@Column(name = "userName")
    private String userName;

	@Column(name = "itemId")
    private Integer itemId;

	@Column(name = "actorId")
    private Integer actorId;

	@Column(name = "serverId")
    private Integer serverId;

	@Column(name = "flags")
    private Byte flags;

	@Column(name = "amount")
    private Integer amount;

	@Column(name = "gameMoney")
    private Integer gameMoney;

	@Column(name = "state")
    private Short state;

	@Column(name = "descrption")
    private String descrption;

	@Column(name = "traces")
    private String traces;

	@Column(name = "channelOrderId")
    private String channelOrderId;

	@Column(name = "createTime")
    private Date createTime;

	@Column(name = "updateTime")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Byte getFlags() {
        return flags;
    }

    public void setFlags(Byte flags) {
        this.flags = flags;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGameMoney() {
        return gameMoney;
    }

    public void setGameMoney(Integer gameMoney) {
        this.gameMoney = gameMoney;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption == null ? null : descrption.trim();
    }

    public String getTraces() {
        return traces;
    }

    public void setTraces(String traces) {
        this.traces = traces == null ? null : traces.trim();
    }

    public String getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId == null ? null : channelOrderId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        OrderInfo other = (OrderInfo) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getActorId() == null ? other.getActorId() == null : this.getActorId().equals(other.getActorId()))
            && (this.getServerId() == null ? other.getServerId() == null : this.getServerId().equals(other.getServerId()))
            && (this.getFlags() == null ? other.getFlags() == null : this.getFlags().equals(other.getFlags()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getGameMoney() == null ? other.getGameMoney() == null : this.getGameMoney().equals(other.getGameMoney()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDescrption() == null ? other.getDescrption() == null : this.getDescrption().equals(other.getDescrption()))
            && (this.getTraces() == null ? other.getTraces() == null : this.getTraces().equals(other.getTraces()))
            && (this.getChannelOrderId() == null ? other.getChannelOrderId() == null : this.getChannelOrderId().equals(other.getChannelOrderId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getActorId() == null) ? 0 : getActorId().hashCode());
        result = prime * result + ((getServerId() == null) ? 0 : getServerId().hashCode());
        result = prime * result + ((getFlags() == null) ? 0 : getFlags().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getGameMoney() == null) ? 0 : getGameMoney().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDescrption() == null) ? 0 : getDescrption().hashCode());
        result = prime * result + ((getTraces() == null) ? 0 : getTraces().hashCode());
        result = prime * result + ((getChannelOrderId() == null) ? 0 : getChannelOrderId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userName=").append(userName);
        sb.append(", itemId=").append(itemId);
        sb.append(", actorId=").append(actorId);
        sb.append(", serverId=").append(serverId);
        sb.append(", flags=").append(flags);
        sb.append(", amount=").append(amount);
        sb.append(", gameMoney=").append(gameMoney);
        sb.append(", state=").append(state);
        sb.append(", descrption=").append(descrption);
        sb.append(", traces=").append(traces);
        sb.append(", channelOrderId=").append(channelOrderId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    public void setState(OrderStatusEnum stateEnum) {
		setState((short)stateEnum.getStatus());
		setUpdateTime(new Date());
		setDescrption(stateEnum.getDesc());
		//这里如果叠加描述的话, 太长. 遂砍掉, 叠加state
		String traces = getTraces() == null ? "" : getTraces();
		//setTraces(traces+getDescrption());
		setTraces(traces.concat("|").concat(String.valueOf(getState())));
	}
}