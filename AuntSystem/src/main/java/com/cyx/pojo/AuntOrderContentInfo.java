package com.cyx.pojo;

import java.util.List;

public class AuntOrderContentInfo{
	private List<AuntOrderInfo> onLineOrderLists;
	private List<AuntOrderInfo> offLineOrderLists;
	private String onlinemoney;
	private String offlinemoney;
	
	@Override
	public String toString() {
		return "AuntOrderContentInfo [onLineOrderLists=" + onLineOrderLists
				+ ", offLineOrderLists=" + offLineOrderLists + ", onlinemoney="
				+ onlinemoney + ", offlinemoney=" + offlinemoney + "]";
	}
	public List<AuntOrderInfo> getOnLineOrderLists() {
		return onLineOrderLists;
	}
	public List<AuntOrderInfo> getOffLineOrderLists() {
		return offLineOrderLists;
	}
	public void setOnLineOrderLists(List<AuntOrderInfo> onLineOrderLists) {
		this.onLineOrderLists = onLineOrderLists;
	}
	public void setOffLineOrderLists(List<AuntOrderInfo> offLineOrderLists) {
		this.offLineOrderLists = offLineOrderLists;
	}
	public String getOnlinemoney() {
		return onlinemoney;
	}
	public String getOfflinemoney() {
		return offlinemoney;
	}

	public void setOnlinemoney(String onlinemoney) {
		this.onlinemoney = onlinemoney;
	}
	public void setOfflinemoney(String offlinemoney) {
		this.offlinemoney = offlinemoney;
	}
	
	
}
