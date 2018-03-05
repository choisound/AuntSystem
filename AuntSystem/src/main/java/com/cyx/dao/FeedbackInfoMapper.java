package com.cyx.dao;

import com.cyx.pojo.FeedbackInfo;

public interface FeedbackInfoMapper {
    int deleteByPrimaryKey(String feedbackId);

    int insert(FeedbackInfo record);

    int insertSelective(FeedbackInfo record);

    FeedbackInfo selectByPrimaryKey(String feedbackId);
    
    FeedbackInfo selectByEvaluateId(String evaluateId);
    
    int updateByPrimaryKeySelective(FeedbackInfo record);

    int updateByPrimaryKeyWithBLOBs(FeedbackInfo record);

    int updateByPrimaryKey(FeedbackInfo record);
}