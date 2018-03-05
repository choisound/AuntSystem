package com.cyx.pojo;

public class AuntServiceInfo {
    private String auntserviceNo;

    private String serviceId;

    private String auntId;

    private String serviceRate;

    public String getAuntserviceNo() {
        return auntserviceNo;
    }

    public void setAuntserviceNo(String auntserviceNo) {
        this.auntserviceNo = auntserviceNo == null ? null : auntserviceNo.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(String serviceRate) {
        this.serviceRate = serviceRate == null ? null : serviceRate.trim();
    }
}