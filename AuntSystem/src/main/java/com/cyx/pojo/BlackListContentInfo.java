package com.cyx.pojo;

public class BlackListContentInfo extends BlackListInfo{
	private AuntInfo auntInfo;

	public AuntInfo getAuntInfo() {
		return auntInfo;
	}

	public void setAuntInfo(AuntInfo auntInfo) {
		this.auntInfo = auntInfo;
	}

	@Override
	public String toString() {
		return "BlackListContentInfo [auntInfo=" + auntInfo + "]";
	}
	
}
