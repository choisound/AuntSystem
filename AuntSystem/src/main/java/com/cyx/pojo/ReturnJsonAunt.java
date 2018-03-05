package com.cyx.pojo;

import java.util.List;
import java.util.Map;

public class ReturnJsonAunt {
	private List<Integer> moneyPay;
	private int status;
	private String msg;
	public int getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private AuntInfo auntInfo;
	private List<AuntServiceContent> auntservicecontents;
	private Double money;
	private Integer count;
	private List<HolidayInfo>holidays;
	public AuntInfo getAuntInfo() {
		return auntInfo;
	}
	public List<AuntServiceContent> getAuntservicecontents() {
		return auntservicecontents;
	}
	
	public List<Integer> getMoneyPay() {
		return moneyPay;
	}
	public void setMoneyPay(List<Integer> moneyPay) {
		this.moneyPay = moneyPay;
	}
	public Double getMoney() {
		return money;
	}
	public Integer getCount() {
		return count;
	}
	public List<HolidayInfo> getHolidays() {
		return holidays;
	}
	public void setAuntInfo(AuntInfo auntInfo) {
		this.auntInfo = auntInfo;
	}
	public void setAuntservicecontents(List<AuntServiceContent> auntservicecontents) {
		this.auntservicecontents = auntservicecontents;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setHolidays(List<HolidayInfo> holidays) {
		this.holidays = holidays;
	}
	@Override
	public String toString() {
		return "ReturnJsonAunt [auntInfo=" + auntInfo
				+ ", auntservicecontents=" + auntservicecontents + ", money="
				+ money + ", count=" + count + ", holidays=" + holidays + "]";
	}
	
}
