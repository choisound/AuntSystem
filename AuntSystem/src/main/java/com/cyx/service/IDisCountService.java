package com.cyx.service;

import java.util.List;

import com.cyx.pojo.AdminInfo;
import com.cyx.pojo.DisCount;

public interface IDisCountService {
	int insert(DisCount record);
    List<DisCount>selectDiscountByUserId(String userId);
    int insertSelective(DisCount record);
    int updateByPrimaryKeySelective(DisCount record);
}
