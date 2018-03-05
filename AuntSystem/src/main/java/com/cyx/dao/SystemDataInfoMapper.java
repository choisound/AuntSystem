package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.SystemDataInfo;

public interface SystemDataInfoMapper {
    int deleteByPrimaryKey(String systemdateId);

    int insert(SystemDataInfo record);

    int insertSelective(SystemDataInfo record);

    SystemDataInfo selectByPrimaryKey(String systemdateId);

    int updateByPrimaryKeySelective(SystemDataInfo record);

    int updateByPrimaryKey(SystemDataInfo record);
    

	SystemDataInfo selectByDataName(String dataName);

	List<SystemDataInfo> selectAll();
	
	
}