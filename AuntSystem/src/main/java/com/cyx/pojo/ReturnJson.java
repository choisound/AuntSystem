package com.cyx.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ReturnJson {
	private int status;
	private String msg;
	private Object data;
	public int getStatus() {
		return status;
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
	public ReturnJson() {
		super();
	}
	public ReturnJson(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
}
