package com.cyx.pojo;

import java.util.List;
import java.util.Map;

public class ReturnJson2 {
		private int status;
		private EvaluateInfo evaluateInfo;
		private FeedbackInfo feedbackInfo;
		private List<AuntInfo> auntlists;
		private OrderInfo orderInfo;
		private ComplaintInfo complaintInfo;
		private Map<String,Integer> monthlyPay;
		public Map<String,Integer> getMonthlyPay() {
			return monthlyPay;
		}
		public void setMonthlyPay(Map<String,Integer> monthlyPay) {
			this.monthlyPay = monthlyPay;
		}
		public ComplaintInfo getComplaintInfo() {
			return complaintInfo;
		}
		public void setComplaintInfo(ComplaintInfo complaintInfo) {
			this.complaintInfo = complaintInfo;
		}
		public int getStatus() {
			return status;
		}
		public EvaluateInfo getEvaluateInfo() {
			return evaluateInfo;
		}
		public FeedbackInfo getFeedbackInfo() {
			return feedbackInfo;
		}
		public List<AuntInfo> getAuntlists() {
			return auntlists;
		}
		public OrderInfo getOrderInfo() {
			return orderInfo;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public void setEvaluateInfo(EvaluateInfo evaluateInfo) {
			this.evaluateInfo = evaluateInfo;
		}
		public void setFeedbackInfo(FeedbackInfo feedbackInfo) {
			this.feedbackInfo = feedbackInfo;
		}
		public void setAuntlists(List<AuntInfo> auntlists) {
			this.auntlists = auntlists;
		}
		public void setOrderInfo(OrderInfo orderInfo) {
			this.orderInfo = orderInfo;
		}
}
