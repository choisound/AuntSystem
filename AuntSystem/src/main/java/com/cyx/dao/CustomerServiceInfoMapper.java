package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.CustomerServiceInfo;

public interface CustomerServiceInfoMapper {
    int deleteByPrimaryKey(String customerserviceId);

    int insert(CustomerServiceInfo record);

    int insertSelective(CustomerServiceInfo record);

    CustomerServiceInfo selectByPrimaryKey(String customerserviceId);
    List<CustomerServiceInfo> selectAll();
    int updateByPrimaryKeySelective(CustomerServiceInfo record);

    int updateByPrimaryKey(CustomerServiceInfo record);

	int deleteByPhoneno(String customerservicePhoneno);
}