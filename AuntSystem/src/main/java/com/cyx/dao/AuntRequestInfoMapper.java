package com.cyx.dao;

import com.cyx.pojo.AuntRequestInfo;

public interface AuntRequestInfoMapper {
    int deleteByPrimaryKey(String requestId);

    int insert(AuntRequestInfo record);

    int insertSelective(AuntRequestInfo record);

    AuntRequestInfo selectByPrimaryKey(String requestId);

    int updateByPrimaryKeySelective(AuntRequestInfo record);

    int updateByPrimaryKey(AuntRequestInfo record);
}