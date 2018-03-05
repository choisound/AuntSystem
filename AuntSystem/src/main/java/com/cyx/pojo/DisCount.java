package com.cyx.pojo;

import java.util.Date;

public class DisCount {
    private String discountId;

    private String userId;

    private String discountMoney;

    private Date discountTime;

    private String discountLimit;

    private Integer isuse;

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId == null ? null : discountId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(String discountMoney) {
        this.discountMoney = discountMoney == null ? null : discountMoney.trim();
    }

    public Date getDiscountTime() {
        return discountTime;
    }

    public void setDiscountTime(Date discountTime) {
        this.discountTime = discountTime;
    }

    public String getDiscountLimit() {
        return discountLimit;
    }

    public void setDiscountLimit(String discountLimit) {
        this.discountLimit = discountLimit == null ? null : discountLimit.trim();
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
}