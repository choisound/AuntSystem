package com.cyx.pojo;

public class ComplaintInfo {
    private String complaintId;

    private String userId;

    private String orderId;

    private String complaintContent;

    public String getComplaintId() {
        return complaintId;
    }

    public ComplaintInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComplaintInfo(String complaintId, String userId, String orderId,
			String complaintContent) {
		super();
		this.complaintId = complaintId;
		this.userId = userId;
		this.orderId = orderId;
		this.complaintContent = complaintContent;
	}

	public void setComplaintId(String complaintId) {
        this.complaintId = complaintId == null ? null : complaintId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent == null ? null : complaintContent.trim();
    }

	@Override
	public String toString() {
		return "ComplaintInfo [complaintId=" + complaintId + ", userId="
				+ userId + ", orderId=" + orderId + ", complaintContent="
				+ complaintContent + "]";
	}
}