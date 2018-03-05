package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.MonthlyPay;

public interface MonthlyPayMapper {
    int deleteByPrimaryKey(Integer monthlypayId);

    int insert(MonthlyPay record);

    int insertSelective(MonthlyPay record);

    MonthlyPay selectByPrimaryKey(Integer monthlypayId);
    List<String>selectmonthbyauntid(String auntId,String year);
    int updateByPrimaryKeySelective(MonthlyPay record);

    int updateByMonthlySelective(MonthlyPay record);

    int updateByPrimaryKey(MonthlyPay record);
}