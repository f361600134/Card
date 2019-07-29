package com.fatiny.cardlogin.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "channel_server")
public class ChannelServer {
	
	@EmbeddedId
	@Column(name = "channel")
    private Integer channel;

	@Column(name = "serverId")
    private Integer serverid;

    private static final long serialVersionUID = 1L;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getServerid() {
        return serverid;
    }

    public void setServerid(Integer serverid) {
        this.serverid = serverid;
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
        ChannelServer other = (ChannelServer) that;
        return (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getServerid() == null ? other.getServerid() == null : this.getServerid().equals(other.getServerid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getServerid() == null) ? 0 : getServerid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", channel=").append(channel);
        sb.append(", serverid=").append(serverid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}