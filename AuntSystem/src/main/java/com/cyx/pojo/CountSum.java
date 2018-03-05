package com.cyx.pojo;

public class CountSum {
	@Override
	public String toString() {
		return "CountSum [count=" + count + ", sum=" + sum + "]";
	}
	private String count;
	private String sum;
	public String getCount() {
		return count;
	}
	public String getSum() {
		return sum;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
}
