package com.cyx.pojo;

public class MonthlyPay {
    private Integer monthlypayId;

    private String monthlypayMouth;

    private Integer monthlypayState;

    private String auntId;

    public Integer getMonthlypayId() {
        return monthlypayId;
    }

    public void setMonthlypayId(Integer monthlypayId) {
        this.monthlypayId = monthlypayId;
    }

    public String getMonthlypayMouth() {
        return monthlypayMouth;
    }

    public void setMonthlypayMouth(String monthlypayMouth) {
        this.monthlypayMouth = monthlypayMouth == null ? null : monthlypayMouth.trim();
    }

    public Integer getMonthlypayState() {
        return monthlypayState;
    }

    public void setMonthlypayState(Integer monthlypayState) {
        this.monthlypayState = monthlypayState;
    }

    public String getAuntId() {
        return auntId;
    }

    public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }
}