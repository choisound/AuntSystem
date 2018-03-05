package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.AuntServiceContent;
import com.cyx.pojo.AuntServiceInfo;

public interface AuntServiceInfoMapper {
    int deleteByPrimaryKey(String auntserviceNo);

    int insert(AuntServiceInfo record);

    int insertSelective(AuntServiceInfo record);
    int insertList(List<AuntServiceInfo> records);
    AuntServiceInfo selectByPrimaryKey(String auntserviceNo);
    List<AuntServiceContent> selectByAuntId(String auntId);
    int updateByPrimaryKeySelective(AuntServiceInfo record);

    int updateByPrimaryKey(AuntServiceInfo record);
}