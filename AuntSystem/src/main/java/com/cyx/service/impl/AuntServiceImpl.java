package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.AuntInfoMapper;
import com.cyx.pojo.AuntInfo;
import com.cyx.service.IAuntService;
@Service("auntService")
public class AuntServiceImpl implements IAuntService {
	@Resource
	AuntInfoMapper auntMapper;
	@Override
	public int deleteByPrimaryKey(String auntId) {
		// TODO Auto-generated method stub
		return auntMapper.deleteByPrimaryKey(auntId);
	}

	@Override
	public int insert(AuntInfo record) {
		// TODO Auto-generated method stub
		return auntMapper.insert(record);
	}

	@Override
	public int insertSelective(AuntInfo record) {
		// TODO Auto-generated method stub
		return auntMapper.insertSelective(record);
	}

	@Override
	public AuntInfo selectByPrimaryKey(String auntId) {
		// TODO Auto-generated method stub
		return auntMapper.selectByPrimaryKey(auntId);
	}

	@Override
	public int updateByPrimaryKeySelective(AuntInfo record) {
		// TODO Auto-generated method stub
		return auntMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AuntInfo record) {
		// TODO Auto-generated method stub
		return auntMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPhoneNoSelective(AuntInfo auntPhoneno) {
		// TODO Auto-generated method stub
		return auntMapper.updateByPhoneNoSelective(auntPhoneno);
	}

	@Override
	public AuntInfo selectByPhoneno(String phoneno) {
		// TODO Auto-generated method stub
		return auntMapper.selectByPhoneno(phoneno);
	}

	@Override
	public AuntInfo selectByPhonenoAndPassword(String phone, String password) {
		// TODO Auto-generated method stub
		return auntMapper.selectByPhonenoAndPassword(phone, password);
	}

	@Override
	public AuntInfo selectByIdentity(String identity) {
		// TODO Auto-generated method stub
		return auntMapper.selectByIdentity(identity);
	}

	@Override
	public List<AuntInfo> selectAllList(int index, int end) {
		// TODO Auto-generated method stub
		return auntMapper.selectAllList(index, end);
	}

	@Override
	public List<AuntInfo> selectResigerList(int index, int end) {
		// TODO Auto-generated method stub
		return auntMapper.selectResigerList(index, end);
	}

	@Override
	public int selectAllListCount() {
		// TODO Auto-generated method stub
		return auntMapper.selectAllListCount();
	}

	@Override
	public int selectResigerListCount() {
		// TODO Auto-generated method stub
		return auntMapper.selectResigerListCount();
	}

	@Override
	public List<AuntInfo> selectByAddress(String address) {
		// TODO Auto-generated method stub
		return auntMapper.selectByAddress(address);
	}

	@Override
	public List<AuntInfo> queryListByIds(List<String> lists) {
		// TODO Auto-generated method stub
		return auntMapper.queryListByIds(lists);
	}

	@Override
	public AuntInfo adminselectByPrimaryKey(String auntId) {
		// TODO Auto-generated method stub
		return auntMapper.adminselectByPrimaryKey(auntId);
	}

	
}
