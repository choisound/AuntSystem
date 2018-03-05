package com.cyx.pojo;

public class SystemDataInfo {
    private String systemdateId;

    private String dateName;

    private String dateNum;

    public String getSystemdateId() {
        return systemdateId;
    }

    public SystemDataInfo() {
		super();
	}

	public SystemDataInfo(String systemdateId, String dateName, String dateNum) {
		super();
		this.systemdateId = systemdateId;
		this.dateName = dateName;
		this.dateNum = dateNum;
	}

	public void setSystemdateId(String systemdateId) {
        this.systemdateId = systemdateId == null ? null : systemdateId.trim();
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName == null ? null : dateName.trim();
    }

    public String getDateNum() {
        return dateNum;
    }

    public void setDateNum(String dateNum) {
        this.dateNum = dateNum == null ? null : dateNum.trim();
    }
}