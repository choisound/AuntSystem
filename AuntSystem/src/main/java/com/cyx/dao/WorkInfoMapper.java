package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.WorkInfo;

public interface WorkInfoMapper {
    int deleteByPrimaryKey(String workId);

    int insert(WorkInfo record);

    int insertSelective(WorkInfo record);

    WorkInfo selectByPrimaryKey(String workId);

    int updateByPrimaryKeySelective(WorkInfo record);

    int updateByPrimaryKey(WorkInfo record);

	List<WorkInfo> selectByAuntId(String auntId);

	
	
}