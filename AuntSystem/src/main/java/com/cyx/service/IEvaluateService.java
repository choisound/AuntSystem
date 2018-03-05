package com.cyx.service;

import java.util.List;

import com.cyx.pojo.EvaluateInfo;

public interface IEvaluateService {
	int deleteByPrimaryKey(String evaluateId);

    int insert(EvaluateInfo record);
    EvaluateInfo selectByOrderid(String orderId);
    int insertSelective(EvaluateInfo record);
    List<EvaluateInfo>selectAuntService(String service_id,String aunt_id);
    EvaluateInfo selectByPrimaryKey(String evaluateId);
    List<EvaluateInfo>selectThreestar(String orderId);
    int updateByPrimaryKeySelective(EvaluateInfo record);
    int updateRateSelective(String orderId);
    int updateByPrimaryKeyWithBLOBs(EvaluateInfo record);

    int updateByPrimaryKey(EvaluateInfo record);
}
