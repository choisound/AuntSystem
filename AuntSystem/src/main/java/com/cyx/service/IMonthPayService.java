package com.cyx.service;

import java.util.List;

import com.cyx.pojo.MonthlyPay;

public interface IMonthPayService {
	 int insert(MonthlyPay record);

	    int insertSelective(MonthlyPay record);

    List<String>selectmonthbyauntid(String auntId,String year);
}
