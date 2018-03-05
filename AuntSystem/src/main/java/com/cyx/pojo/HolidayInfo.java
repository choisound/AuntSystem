package com.cyx.pojo;

public class HolidayInfo {
    private String holidayId;

    private String auntId;

    private String holidayStarttime;

    @Override
	public String toString() {
		return "HolidayInfo [holidayId=" + holidayId + ", auntId=" + auntId
				+ ", holidayStarttime=" + holidayStarttime
				+ ", holidayEndtime=" + holidayEndtime + "]";
	}

	private String holidayEndtime;

    public HolidayInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HolidayInfo(String holidayId, String auntId,
			String holidayStarttime, String holidayEndtime) {
		super();
		this.holidayId = holidayId;
		this.auntId = auntId;
		this.holidayStarttime = holidayStarttime;
		this.holidayEndtime = holidayEndtime;
	}

	public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId == null ? null : holidayId.trim();
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public String getHolidayStarttime() {
        return holidayStarttime;
    }

    public void setHolidayStarttime(String holidayStarttime) {
        this.holidayStarttime = holidayStarttime;
    }

    public String getHolidayEndtime() {
        return holidayEndtime;
    }

    public void setHolidayEndtime(String holidayEndtime) {
        this.holidayEndtime = holidayEndtime;
    }
}