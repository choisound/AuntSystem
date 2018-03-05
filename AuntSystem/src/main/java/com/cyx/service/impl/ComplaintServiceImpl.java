package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.ComplaintInfoMapper;
import com.cyx.pojo.ComplaintContentInfo;
import com.cyx.pojo.ComplaintInfo;
import com.cyx.pojo.FeedbackInfo;
import com.cyx.service.IComplaintService;
@Service("complaintService")
public class ComplaintServiceImpl implements IComplaintService {
	@Resource
	ComplaintInfoMapper complaintMapper;
	@Override
	public int deleteByPrimaryKey(String complaintId) {
		// TODO Auto-generated method stub
		return complaintMapper.deleteByPrimaryKey(complaintId);
	}

	@Override
	public int insert(ComplaintInfo record) {
		// TODO Auto-generated method stub
		return complaintMapper.insert(record);
	}
	
	public List<ComplaintInfo> selectByUserId(String userId){
		return complaintMapper.selectByUserId(userId);
	}
	
	@Override
	public int insertSelective(ComplaintInfo record) {
		// TODO Auto-generated method stub
		return complaintMapper.insertSelective(record);
	}

	@Override
	public ComplaintInfo selectByPrimaryKey(String complaintId) {
		// TODO Auto-generated method stub
		return complaintMapper.selectByPrimaryKey(complaintId);
	}

	@Override
	public int updateByPrimaryKeySelective(ComplaintInfo record) {
		// TODO Auto-generated method stub
		return complaintMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ComplaintInfo record) {
		// TODO Auto-generated method stub
		return complaintMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(ComplaintInfo record) {
		// TODO Auto-generated method stub
		return complaintMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ComplaintInfo> selectAll(int start, int end) {
		// TODO Auto-generated method stub
		return complaintMapper.selectAll(start, end);
	}

	@Override
	public int selectAllCount() {
		// TODO Auto-generated method stub
		return complaintMapper.selectAllCount();
	}

	@Override
	public List<ComplaintContentInfo> selectAllLimit(int start, int end) {
		// TODO Auto-generated method stub
		return complaintMapper.selectAllLimit(start, end);
	}

	@Override
	public ComplaintInfo selectByOrderIdAndUserId(String orderId, String userId) {
		// TODO Auto-generated method stub
		return complaintMapper.selectByOrderIdAndUserId(orderId, userId);
	}

}
