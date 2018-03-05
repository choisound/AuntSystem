package com.cyx.pojo;

import java.util.Date;

public class UserOrderContentInfo extends OrderInfo{
    private ServiceInfo serviceInfo;
    private AuntInfo auntInfo;
	public ServiceInfo getServiceInfo() {
		return serviceInfo;
	}
	public AuntInfo getAuntInfo() {
		return auntInfo;
	}
	public void setServiceInfo(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	public void setAuntInfo(AuntInfo auntInfo) {
		this.auntInfo = auntInfo;
	}
	@Override
	public String toString() {
		return "UserOrderContentInfo [serviceInfo=" + serviceInfo
				+ ", auntInfo=" + auntInfo + "]";
	}
}