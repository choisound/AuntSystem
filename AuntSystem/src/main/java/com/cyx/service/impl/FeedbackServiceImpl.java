package com.cyx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.FeedbackInfoMapper;
import com.cyx.pojo.FeedbackInfo;
import com.cyx.service.IFeedbackService;
@Service("feedbackService")
public class FeedbackServiceImpl implements IFeedbackService {
	@Resource
	FeedbackInfoMapper feedbackMapper;
	@Override
	public int deleteByPrimaryKey(String feedbackId) {
		// TODO Auto-generated method stub
		return feedbackMapper.deleteByPrimaryKey(feedbackId);
	}

	@Override
	public int insert(FeedbackInfo record) {
		// TODO Auto-generated method stub
		return feedbackMapper.insert(record);
	}

	@Override
	public int insertSelective(FeedbackInfo record) {
		// TODO Auto-generated method stub
		return feedbackMapper.insertSelective(record);
	}

	@Override
	public FeedbackInfo selectByPrimaryKey(String feedbackId) {
		// TODO Auto-generated method stub
		return feedbackMapper.selectByPrimaryKey(feedbackId);
	}

	@Override
	public int updateByPrimaryKeySelective(FeedbackInfo record) {
		// TODO Auto-generated method stub
		return feedbackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(FeedbackInfo record) {
		// TODO Auto-generated method stub
		return feedbackMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(FeedbackInfo record) {
		// TODO Auto-generated method stub
		return feedbackMapper.updateByPrimaryKey(record);
	}

	@Override
	public FeedbackInfo selectByEvaluateId(String evaluateId) {
		// TODO Auto-generated method stub
		return feedbackMapper.selectByEvaluateId(evaluateId);
	}

}
