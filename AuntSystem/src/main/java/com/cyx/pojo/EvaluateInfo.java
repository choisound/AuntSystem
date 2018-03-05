package com.cyx.pojo;
import java.util.Date;
public class EvaluateInfo {
    private String evaluateId;

    private String orderId;

    private String userId;

    private Integer evaluateStar;

    private Date evaluateTime;

    private Integer isvilify;

    private String evaluateContent;

    public EvaluateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EvaluateInfo [evaluateId=" + evaluateId + ", orderId="
				+ orderId + ", userId=" + userId + ", evaluateStar="
				+ evaluateStar + ", evaluateTime=" + evaluateTime
				+ ", isvilify=" + isvilify + ", evaluateContent="
				+ evaluateContent + "]";
	}

	public EvaluateInfo(String evaluateId, String orderId, String userId,
			Integer evaluateStar, Date evaluateTime, Integer isvilify,
			String evaluateContent) {
		super();
		this.evaluateId = evaluateId;
		this.orderId = orderId;
		this.userId = userId;
		this.evaluateStar = evaluateStar;
		this.evaluateTime = evaluateTime;
		this.isvilify = isvilify;
		this.evaluateContent = evaluateContent;
	}

	public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId == null ? null : evaluateId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getEvaluateStar() {
        return evaluateStar;
    }

    public void setEvaluateStar(Integer evaluateStar) {
        this.evaluateStar = evaluateStar;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Integer getIsvilify() {
        return isvilify;
    }

    public void setIsvilify(Integer isvilify) {
        this.isvilify = isvilify;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }
}