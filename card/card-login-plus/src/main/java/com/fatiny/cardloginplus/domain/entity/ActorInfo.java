package com.fatiny.cardloginplus.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "actor_info")
public class ActorInfo {
	
	@Id
	@Column(name = "actorId")
    private Integer actorId;

	@Column(name = "userName")
    private String userName;

	@Column(name = "name")
    private String name;

	@Column(name = "actorType")
    private Integer actorType;

	@Column(name = "lastUpdate")
    private Date lastUpdate;

	@Column(name = "level")
    private Integer level;

	@Column(name = "serverId")
    private Integer serverId;

//    private static final long serialVersionUID = 1L;
//
//    public Integer getActorId() {
//        return actorId;
//    }
//
//    public void setActorId(Integer actorId) {
//        this.actorId = actorId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName == null ? null : userName.trim();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name == null ? null : name.trim();
//    }
//
//    public Integer getActorType() {
//        return actorType;
//    }
//
//    public void setActorType(Integer actorType) {
//        this.actorType = actorType;
//    }
//
//    public Date getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public void setLastUpdate(Date lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
//
//    public Integer getLevel() {
//        return level;
//    }
//
//    public void setLevel(Integer level) {
//        this.level = level;
//    }
//
//    public Integer getServerId() {
//        return serverId;
//    }
//
//    public void setServerId(Integer serverId) {
//        this.serverId = serverId;
//    }
//
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        ActorInfo other = (ActorInfo) that;
//        return (this.getActorId() == null ? other.getActorId() == null : this.getActorId().equals(other.getActorId()))
//            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
//            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
//            && (this.getActorType() == null ? other.getActorType() == null : this.getActorType().equals(other.getActorType()))
//            && (this.getLastUpdate() == null ? other.getLastUpdate() == null : this.getLastUpdate().equals(other.getLastUpdate()))
//            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
//            && (this.getServerId() == null ? other.getServerId() == null : this.getServerId().equals(other.getServerId()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getActorId() == null) ? 0 : getActorId().hashCode());
//        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
//        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
//        result = prime * result + ((getActorType() == null) ? 0 : getActorType().hashCode());
//        result = prime * result + ((getLastUpdate() == null) ? 0 : getLastUpdate().hashCode());
//        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
//        result = prime * result + ((getServerId() == null) ? 0 : getServerId().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", actorId=").append(actorId);
//        sb.append(", userName=").append(userName);
//        sb.append(", name=").append(name);
//        sb.append(", actorType=").append(actorType);
//        sb.append(", lastUpdate=").append(lastUpdate);
//        sb.append(", level=").append(level);
//        sb.append(", serverId=").append(serverId);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}