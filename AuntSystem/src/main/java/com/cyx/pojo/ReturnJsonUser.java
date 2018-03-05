package com.cyx.pojo;

import java.util.List;

public class ReturnJsonUser {
	private int status;
	private String Msg;
	public int getStatus() {
		return status;
	}
	public String getMsg() {
		return Msg;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	private UserInfo userInfo;
	private List<BlackListInfo>bl;
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public List<BlackListInfo> getBl() {
		return bl;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setBl(List<BlackListInfo> bl) {
		this.bl = bl;
	}
	
}
