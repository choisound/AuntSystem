package com.cyx.pojo;

import java.util.Date;

public class OrderInfo_YMS {
	 private Date startTime;
	    private String orderZwaddress;
	 public String getOrderZwaddress() {
			return orderZwaddress;
		}
		public void setOrderZwaddress(String orderZwaddress) {
			this.orderZwaddress = orderZwaddress;
		}
	private String orderMoney;
	 private Integer orderState;
	public Date getStartTime() {
		return startTime;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	@Override
	public String toString() {
		return "OrderInfo_YMS [startTime=" + startTime + ", orderZwaddress="
				+ orderZwaddress + ", orderMoney=" + orderMoney
				+ ", orderState=" + orderState + "]";
	}
}
