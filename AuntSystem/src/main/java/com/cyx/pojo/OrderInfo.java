package com.cyx.pojo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderInfo {
    public String orderId;

    private String userId;

    private String auntId;

    private String serviceId;
    private Date startTime;
    private Date endTime;

    private String orderAddress;

    private String orderMoney;

    private Integer orderState;
    private Date orderRes;
    private Date auntStarttime;

    private String orderDesc;
    private Date orderRoughtime;
    private String orderZwaddress;
    public String getOrderZwaddress() {
		return orderZwaddress;
	}

	public void setOrderZwaddress(String orderZwaddress) {
		this.orderZwaddress = orderZwaddress;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney == null ? null : orderMoney.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
    public Date getOrderRes() {
        return orderRes;
    }

    public void setOrderRes(Date orderRes) {
        this.orderRes = orderRes;
    }

    public Date getAuntStarttime() {
        return auntStarttime;
    }

    public void setAuntStarttime(Date auntStarttime) {
        this.auntStarttime = auntStarttime;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }
    public Date getOrderRoughtime() {
        return orderRoughtime;
    }

    public void setOrderRoughtime(Date orderRoughtime) {
        this.orderRoughtime = orderRoughtime;
    }

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", userId=" + userId
				+ ", auntId=" + auntId + ", serviceId=" + serviceId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", orderAddress=" + orderAddress + ", orderMoney="
				+ orderMoney + ", orderState=" + orderState + ", orderRes="
				+ orderRes + ", auntStarttime=" + auntStarttime
				+ ", orderDesc=" + orderDesc + ", orderRoughtime="
				+ orderRoughtime + ", orderZwaddress=" + orderZwaddress + "]";
	}
}