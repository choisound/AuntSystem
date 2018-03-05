package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.AuntServiceInfoMapper;
import com.cyx.pojo.AuntServiceInfo;
import com.cyx.pojo.AuntServiceContent;
import com.cyx.service.IAuntServiceService;
@Service("auntServiceService")
public class AuntServiceServiceImpl implements IAuntServiceService {
	@Resource
	AuntServiceInfoMapper auntServiceMapper;
	@Override
	public int deleteByPrimaryKey(String auntserviceInfoId) {
		// TODO Auto-generated method stub
		return auntServiceMapper.deleteByPrimaryKey(auntserviceInfoId);
	}

	@Override
	public int insert(AuntServiceInfo record) {
		// TODO Auto-generated method stub
		return auntServiceMapper.insert(record);
	}

	@Override
	public int insertSelective(AuntServiceInfo record) {
		// TODO Auto-generated method stub
		return auntServiceMapper.insertSelective(record);
	}

	@Override
	public AuntServiceInfo selectByPrimaryKey(String auntserviceInfoId) {
		// TODO Auto-generated method stub
		return auntServiceMapper.selectByPrimaryKey(auntserviceInfoId);
	}

	@Override
	public int updateByPrimaryKeySelective(AuntServiceInfo record) {
		// TODO Auto-generated method stub
		return auntServiceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AuntServiceInfo record) {
		// TODO Auto-generated method stub
		return auntServiceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AuntServiceContent> selectByAuntId(String auntId) {
		// TODO Auto-generated method stub
		return auntServiceMapper.selectByAuntId(auntId);
	}

	@Override
	public int insertList(List<AuntServiceInfo> records) {
		// TODO Auto-generated method stub
		return auntServiceMapper.insertList(records);
	}

}
