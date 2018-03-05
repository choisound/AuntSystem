package com.cyx.pojo;

import java.util.Date;

public class SystemShareInfo {
    private String shareInfoId;

    private String adminId;

    private Date shareTime;

    public SystemShareInfo(){
    	super();
    }
    
    public SystemShareInfo(String shareInfoId, String adminId, Date shareTime, String shareContent) {
		super();
		this.shareInfoId = shareInfoId;
		this.adminId = adminId;
		this.shareTime = shareTime;
		this.shareContent = shareContent;
	}

	private String shareContent;

    public String getShareInfoId() {
        return shareInfoId;
    }

    public void setShareInfoId(String shareInfoId) {
        this.shareInfoId = shareInfoId == null ? null : shareInfoId.trim();
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent == null ? null : shareContent.trim();
    }
}