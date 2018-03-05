package com.cyx.pojo;

public class FeedbackInfo {
    private String feedbackId;

    private String evaluateId;

    private String feedbackContent;

    @Override
	public String toString() {
		return "FeedbackInfo [feedbackId=" + feedbackId + ", evaluateId="
				+ evaluateId + ", feedbackContent=" + feedbackContent + "]";
	}

	public FeedbackInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedbackInfo(String feedbackId, String evaluateId,
			String feedbackContent) {
		super();
		this.feedbackId = feedbackId;
		this.evaluateId = evaluateId;
		this.feedbackContent = feedbackContent;
	}

	public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId == null ? null : feedbackId.trim();
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId == null ? null : evaluateId.trim();
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }
}