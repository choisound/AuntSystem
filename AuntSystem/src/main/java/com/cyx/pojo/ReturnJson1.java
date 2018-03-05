package com.cyx.pojo;


public class ReturnJson1 {
	private int status;
	private String msg;
	private int count;
	private Object data;
	public int getStatus() {
		return status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ReturnJson1() {
		super();
	}
	public ReturnJson1(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
}
