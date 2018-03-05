package com.cyx.pojo;

public class UserInfo {
    private String userId;
    private String resgiterId;
    private String userName;

    private String userAddress;

    private String userPhoneno;

    private String userSex;

    private String userCount;

    private String userPassword;

    private Integer userState;

    private String userJifen;

    public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResgiterId() {
		return resgiterId;
	}

	public void setResgiterId(String resgiterId) {
		this.resgiterId = resgiterId;
	}

	public UserInfo(String userId, String userName, String userAddress,
			String userPhoneno, String userSex, String userCount,
			String userPassword, Integer userState, String userJifen) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhoneno = userPhoneno;
		this.userSex = userSex;
		this.userCount = userCount;
		this.userPassword = userPassword;
		this.userState = userState;
		this.userJifen = userJifen;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserPhoneno() {
        return userPhoneno;
    }

    public void setUserPhoneno(String userPhoneno) {
        this.userPhoneno = userPhoneno == null ? null : userPhoneno.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount == null ? null : userCount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getUserJifen() {
        return userJifen;
    }

    public void setUserJifen(String userJifen) {
        this.userJifen = userJifen == null ? null : userJifen.trim();
    }

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", resgiterId=" + resgiterId
				+ ", userName=" + userName + ", userAddress=" + userAddress
				+ ", userPhoneno=" + userPhoneno + ", userSex=" + userSex
				+ ", userCount=" + userCount + ", userPassword=" + userPassword
				+ ", userState=" + userState + ", userJifen=" + userJifen + "]";
	}
}