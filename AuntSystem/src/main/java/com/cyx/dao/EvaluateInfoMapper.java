package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.EvaluateInfo;

public interface EvaluateInfoMapper {
    int deleteByPrimaryKey(String evaluateId);

    int insert(EvaluateInfo record);

    int insertSelective(EvaluateInfo record);

    EvaluateInfo selectByPrimaryKey(String evaluateId);
    List<EvaluateInfo>selectThreestar(String orderId);
    int updateByPrimaryKeySelective(EvaluateInfo record);
    int updateRateSelective(String orderId);
    int updateByPrimaryKeyWithBLOBs(EvaluateInfo record);
    EvaluateInfo selectByOrderid(String orderId);
    List<EvaluateInfo>selectAuntService(String service_id,String aunt_id);
    int updateByPrimaryKey(EvaluateInfo record);
}