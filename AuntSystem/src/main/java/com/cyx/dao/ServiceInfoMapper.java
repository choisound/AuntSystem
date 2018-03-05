package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.ServiceInfo;

public interface ServiceInfoMapper {
    int deleteByPrimaryKey(String serviceId);

    int insert(ServiceInfo record);

    int insertSelective(ServiceInfo record);
    int deleteByName(String name);
    ServiceInfo selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(ServiceInfo record);
    int updateByNameSelective(ServiceInfo record);
    int updateByPrimaryKeyWithBLOBs(ServiceInfo record);
    List<ServiceInfo> selectAll();
    int updateByPrimaryKey(ServiceInfo record);
}