package com.cyx.pojo;

public class SystemBlacklistInfo {
    private String systemBlacklistId;

    private String auntId;

    public String getSystemBlacklistId() {
        return systemBlacklistId;
    }
    
    
    public SystemBlacklistInfo() {
		super();
	}




	public SystemBlacklistInfo(String systemBlacklistId2, String auntId2) {
		// TODO Auto-generated constructor stub
		super();
		this.systemBlacklistId = systemBlacklistId;
		this.auntId = auntId;
	}


	public void setSystemBlacklistId(String systemBlacklistId) {
        this.systemBlacklistId = systemBlacklistId == null ? null : systemBlacklistId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }
}