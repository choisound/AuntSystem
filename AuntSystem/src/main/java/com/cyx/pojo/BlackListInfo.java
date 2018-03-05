package com.cyx.pojo;

public class BlackListInfo {
    private String blacklistId;

    private String userId;

    private String auntId;

    public BlackListInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlackListInfo(String blacklistId, String userId, String auntId) {
		super();
		this.blacklistId = blacklistId;
		this.userId = userId;
		this.auntId = auntId;
	}

	public String getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(String blacklistId) {
        this.blacklistId = blacklistId == null ? null : blacklistId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }
}