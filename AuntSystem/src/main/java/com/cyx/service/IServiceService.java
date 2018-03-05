package com.cyx.service;

import java.util.List;

import com.cyx.pojo.ServiceInfo;

public interface IServiceService {
	int deleteByPrimaryKey(String serviceId);
	 int deleteByName(String name);
    int insert(ServiceInfo record);

    int insertSelective(ServiceInfo record);

    ServiceInfo selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(ServiceInfo record);
    int updateByNameSelective(ServiceInfo record);
    int updateByPrimaryKeyWithBLOBs(ServiceInfo record);
    List<ServiceInfo> selectAll();
    int updateByPrimaryKey(ServiceInfo record);
}
