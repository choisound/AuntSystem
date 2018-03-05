package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.EvaluateInfoMapper;
import com.cyx.pojo.EvaluateInfo;
import com.cyx.service.IEvaluateService;
@Service("evaluateService")
public class EvaluateServiceImpl implements IEvaluateService {
	@Resource
	EvaluateInfoMapper evaluateMapper;
	@Override
	public int deleteByPrimaryKey(String evaluateId) {
		// TODO Auto-generated method stub
		return evaluateMapper.deleteByPrimaryKey(evaluateId);
	}

	@Override
	public int insert(EvaluateInfo record) {
		// TODO Auto-generated method stub
		return evaluateMapper.insert(record);
	}

	@Override
	public int insertSelective(EvaluateInfo record) {
		// TODO Auto-generated method stub
		return evaluateMapper.insertSelective(record);
	}

	@Override
	public EvaluateInfo selectByPrimaryKey(String evaluateId) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectByPrimaryKey(evaluateId);
	}

	@Override
	public int updateByPrimaryKeySelective(EvaluateInfo record) {
		// TODO Auto-generated method stub
		return evaluateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(EvaluateInfo record) {
		// TODO Auto-generated method stub
		return evaluateMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(EvaluateInfo record) {
		// TODO Auto-generated method stub
		return evaluateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateRateSelective(String orderId) {
		// TODO Auto-generated method stub
		return evaluateMapper.updateRateSelective(orderId);
	}

	@Override
	public List<EvaluateInfo> selectThreestar(String orderId) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectThreestar(orderId);
	}

	@Override
	public List<EvaluateInfo> selectAuntService(String service_id,
			String aunt_id) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectAuntService(service_id, aunt_id);
	}

	@Override
	public EvaluateInfo selectByOrderid(String orderId) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectByOrderid(orderId);
	}

}
