package com.cyx.service;

import java.util.List;

import com.cyx.pojo.ComplaintContentInfo;
import com.cyx.pojo.ComplaintInfo;
import com.cyx.pojo.FeedbackInfo;

public interface IComplaintService {
	int deleteByPrimaryKey(String complaintId);
	ComplaintInfo selectByOrderIdAndUserId(String orderId,String userId);
    int insert(ComplaintInfo record);
    List<ComplaintContentInfo>selectAllLimit(int start,int end);
    int insertSelective(ComplaintInfo record);
    List <ComplaintInfo>selectAll(int start,int end);
    int selectAllCount();
    ComplaintInfo selectByPrimaryKey(String complaintId);

    int updateByPrimaryKeySelective(ComplaintInfo record);

    int updateByPrimaryKeyWithBLOBs(ComplaintInfo record);

    int updateByPrimaryKey(ComplaintInfo record);

	List<ComplaintInfo> selectByUserId(String userId);
	
}
