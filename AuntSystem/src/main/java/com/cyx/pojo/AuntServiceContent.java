package com.cyx.pojo;

public class AuntServiceContent {
	private String serviceId;
    private String serviceName;
    private String servicePrice;
    private String serviceDesc;
    private String serviceRate;
	public String getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public String getServiceRate() {
		return serviceRate;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public void setServiceRate(String serviceRate) {
		this.serviceRate = serviceRate;
	}
	@Override
	public String toString() {
		return "AuntServiceContent [serviceId=" + serviceId + ", serviceName="
				+ serviceName + ", servicePrice=" + servicePrice
				+ ", serviceDesc=" + serviceDesc + ", serviceRate="
				+ serviceRate + "]";
	}
	public AuntServiceContent(String serviceId, String serviceName,
			String servicePrice, String serviceDesc, String serviceRate) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.serviceDesc = serviceDesc;
		this.serviceRate = serviceRate;
	}
	public AuntServiceContent() {
		super();
		// TODO Auto-generated constructor stub
	}
}
