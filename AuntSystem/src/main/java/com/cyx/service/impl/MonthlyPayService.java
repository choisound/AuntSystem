package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.MonthlyPayMapper;
import com.cyx.pojo.MonthlyPay;
import com.cyx.service.IMonthPayService;
@Service("monthlyPayService")
public class MonthlyPayService implements IMonthPayService{
	@Resource MonthlyPayMapper monthlyPayMapper;
	/**
	 * 更新月结状态
	 */

	@Override
	public int insert(MonthlyPay record) {
		// TODO Auto-generated method stub
		return monthlyPayMapper.insert(record);
	}

	@Override
	public int insertSelective(MonthlyPay record) {
		// TODO Auto-generated method stub
		return monthlyPayMapper.insertSelective(record);
	}

	@Override
	public List<String> selectmonthbyauntid(String auntId, String year) {
		// TODO Auto-generated method stub
		return monthlyPayMapper.selectmonthbyauntid(auntId, year);
	}
		
}
