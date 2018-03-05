package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.ComplaintContentInfo;
import com.cyx.pojo.ComplaintInfo;
import com.cyx.pojo.CustomerServiceInfo;
import com.cyx.pojo.FeedbackInfo;

public interface ComplaintInfoMapper {
    int deleteByPrimaryKey(String complaintId);

    int insert(ComplaintInfo record);
    List <ComplaintInfo>selectAll(int start,int end);
    List<ComplaintContentInfo>selectAllLimit(int start,int end);
    int selectAllCount();
    int insertSelective(ComplaintInfo record);
    ComplaintInfo selectByOrderIdAndUserId(String orderId,String userId);
    ComplaintInfo selectByPrimaryKey(String complaintId);

    int updateByPrimaryKeySelective(ComplaintInfo record);

    int updateByPrimaryKeyWithBLOBs(ComplaintInfo record);

    int updateByPrimaryKey(ComplaintInfo record);
    
    List<ComplaintInfo> selectByUserId(String userId);
}