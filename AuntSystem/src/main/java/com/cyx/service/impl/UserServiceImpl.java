package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.UserInfoMapper;
import com.cyx.pojo.UserInfo;
import com.cyx.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	UserInfoMapper userMapper;
	@Override
	public int deleteByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(UserInfo record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(UserInfo record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public UserInfo selectByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserInfo selectByPhoneno(String phoneno) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhoneno(phoneno);
	}

	@Override
	public UserInfo selectByPhonenoAndPassword(String phone, String password) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhonenoAndPassword(phone, password);
	}

	@Override
	public int updateByPhoneNoSelective(UserInfo user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPhoneNoSelective(user);
	}

	@Override
	public int selectAllUserCount() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUserCount();
	}

	@Override
	public List<UserInfo> selectAllList(int start, int end) {
		// TODO Auto-generated method stub
		return userMapper.selectAllList(start, end);
	}

	@Override
	public UserInfo adminselectByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return userMapper.adminselectByPrimaryKey(userId);
	}

}
