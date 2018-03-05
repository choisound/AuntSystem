package com.cyx.pojo;

import java.util.Date;

public class WorkInfo {
    private String workId;

    private String auntId;

    private Date workStarttime;

    private Date wordEndtime;

    public String getWorkId() {
        return workId;
    }
    public WorkInfo(){
    	super();
    }
    public WorkInfo(String workId, String auntId, Date workStarttime, Date wordEndtime) {
		super();
		this.workId = workId;
		this.auntId = auntId;
		this.workStarttime = workStarttime;
		this.wordEndtime = wordEndtime;
	}

	public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public Date getWorkStarttime() {
        return workStarttime;
    }

    public void setWorkStarttime(Date workStarttime) {
        this.workStarttime = workStarttime;
    }

    public Date getWordEndtime() {
        return wordEndtime;
    }

    public void setWordEndtime(Date wordEndtime) {
        this.wordEndtime = wordEndtime;
    }
}