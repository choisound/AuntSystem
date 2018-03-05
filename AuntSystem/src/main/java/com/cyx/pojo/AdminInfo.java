package com.cyx.pojo;

public class AdminInfo {
    private String adminId;

    private String adminCount;

    private String adminPassword;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    @Override
	public String toString() {
		return "AdminInfo [adminId=" + adminId + ", adminCount=" + adminCount
				+ ", adminPassword=" + adminPassword + "]";
	}

	public String getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(String adminCount) {
        this.adminCount = adminCount == null ? null : adminCount.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }
}