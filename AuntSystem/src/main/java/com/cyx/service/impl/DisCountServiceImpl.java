package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.DisCountMapper;
import com.cyx.pojo.DisCount;
import com.cyx.service.IDisCountService;
@Service("discountService")
public class DisCountServiceImpl implements IDisCountService {
	@Resource
	DisCountMapper discountMapper;

	@Override
	public int insert(DisCount record) {
		// TODO Auto-generated method stub
		return discountMapper.insert(record);
	}

	@Override
	public List<DisCount> selectDiscountByUserId(String userId) {
		// TODO Auto-generated method stub
		return discountMapper.selectDiscountByUserId(userId);
	}

	@Override
	public int insertSelective(DisCount record) {
		// TODO Auto-generated method stub
		return discountMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(DisCount record) {
		// TODO Auto-generated method stub
		return discountMapper.updateByPrimaryKeySelective(record);
	}
	
}
