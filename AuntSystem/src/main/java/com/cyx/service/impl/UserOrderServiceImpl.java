package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.UserOrderInfoMapper;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.UserOrderContentInfo;
import com.cyx.pojo.UserOrderInfo;
import com.cyx.service.IUserOrderService;
@Service("userOrderService")
public class UserOrderServiceImpl implements IUserOrderService{
	@Resource
	UserOrderInfoMapper userOrderInfoMapper;
	
	@Override
	public List<AuntInfo> selectByOrder(int index, OrderInfo record) {
		List<AuntInfo> als=userOrderInfoMapper.selectByOrder(index, record);
		String address=record.getOrderAddress();
		return als;
	}
	@Override
	public List<UserOrderInfo> selectByUserId(int index, String auntId) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.selectByUserId(index, auntId);
	}
	@Override
	public int deleteByPrimaryKey(String index,String orderId) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.deleteByPrimaryKey(index,orderId);
	}
	@Override
	public int insertSelective(int index, OrderInfo record) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.insertSelective(index, record);
	}
	@Override
	public int updateByPrimaryKeySelective(int index, OrderInfo record) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.updateByPrimaryKeySelective(index, record);
	}
	@Override
	public UserOrderInfo selectByOrderId(int index, String orderId) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.selectByOrderId(index, orderId);
	}
	@Override
	public double selectMoneyByOrderId(int index, String orderId) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.selectMoneyByOrderId(index, orderId);
	}
	@Override
	public List<UserOrderInfo> selectUserOrderByUserId(int index, String auntId) {
		// TODO Auto-generated method stub
		return userOrderInfoMapper.selectUserOrderId(index, auntId);
	}

}
