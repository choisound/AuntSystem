package com.cyx.service;

import java.util.List;

import com.cyx.pojo.AuntServiceContent;
import com.cyx.pojo.AuntServiceInfo;

public interface IAuntServiceService {
	int deleteByPrimaryKey(String auntserviceInfoId);
    int insert(AuntServiceInfo record);
    int insertList(List<AuntServiceInfo> records);
    int insertSelective(AuntServiceInfo record);
    List<AuntServiceContent> selectByAuntId(String auntId);
    AuntServiceInfo selectByPrimaryKey(String auntserviceInfoId);

    int updateByPrimaryKeySelective(AuntServiceInfo record);

    int updateByPrimaryKey(AuntServiceInfo record);
}
