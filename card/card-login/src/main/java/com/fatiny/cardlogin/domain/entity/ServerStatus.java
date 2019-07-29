package com.fatiny.cardlogin.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "server_status")
public class ServerStatus implements Serializable {
	
	@Column(name = "id")
    private Integer id;

	@Column(name = "name")
    private String name;

	@Column(name = "showId")
    private Integer showId;

	@Column(name = "ip")
    private String ip;

	@Column(name = "innerIp")
    private String innerIp;

	@Column(name = "port")
    private Integer port;

	@Column(name = "status")
    private Short status;

	@Column(name = "isTest")
    private Boolean isTest;

	@Column(name = "isRecommand")
    private Boolean isRecommand;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getInnerIp() {
        return innerIp;
    }

    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp == null ? null : innerIp.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }

    public Boolean getIsRecommand() {
        return isRecommand;
    }

    public void setIsRecommand(Boolean isRecommand) {
        this.isRecommand = isRecommand;
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
        ServerStatus other = (ServerStatus) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getShowId() == null ? other.getShowId() == null : this.getShowId().equals(other.getShowId()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getInnerIp() == null ? other.getInnerIp() == null : this.getInnerIp().equals(other.getInnerIp()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsTest() == null ? other.getIsTest() == null : this.getIsTest().equals(other.getIsTest()))
            && (this.getIsRecommand() == null ? other.getIsRecommand() == null : this.getIsRecommand().equals(other.getIsRecommand()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getShowId() == null) ? 0 : getShowId().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getInnerIp() == null) ? 0 : getInnerIp().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsTest() == null) ? 0 : getIsTest().hashCode());
        result = prime * result + ((getIsRecommand() == null) ? 0 : getIsRecommand().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", showId=").append(showId);
        sb.append(", ip=").append(ip);
        sb.append(", innerIp=").append(innerIp);
        sb.append(", port=").append(port);
        sb.append(", status=").append(status);
        sb.append(", isTest=").append(isTest);
        sb.append(", isRecommand=").append(isRecommand);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}