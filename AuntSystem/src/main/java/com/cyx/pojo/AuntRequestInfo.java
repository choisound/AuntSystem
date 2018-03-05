package com.cyx.pojo;

import java.util.Date;

public class AuntRequestInfo {
    private String requestId;

    private String orderId;

    private String auntId;

    private Date requestTime;

    private String requesContent;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequesContent() {
        return requesContent;
    }

    public void setRequesContent(String requesContent) {
        this.requesContent = requesContent == null ? null : requesContent.trim();
    }
}