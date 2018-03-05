package com.cyx.service;

import com.cyx.pojo.FeedbackInfo;

public interface IFeedbackService {
	int deleteByPrimaryKey(String feedbackId);

    int insert(FeedbackInfo record);
    FeedbackInfo selectByEvaluateId(String evaluateId);
    int insertSelective(FeedbackInfo record);

    FeedbackInfo selectByPrimaryKey(String feedbackId);

    int updateByPrimaryKeySelective(FeedbackInfo record);

    int updateByPrimaryKeyWithBLOBs(FeedbackInfo record);

    int updateByPrimaryKey(FeedbackInfo record);
}
