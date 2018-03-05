package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.OrderInfoMapper;
import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.OrderInfo_YMS;
import com.cyx.service.IOrderService;
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Resource
	OrderInfoMapper orderMapper;
	@Override
	public int deleteByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return orderMapper.deleteByPrimaryKey(orderId);
	}
	@Override
	public List<OrderInfo> selectAllOrder(int start, int end) {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrder(start, end);
	}

	@Override
	public int selectAllOrderCount() {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrderCount();
	}

	@Override
	public List<OrderInfo> selectSendingOrder(int start, int end) {
		// TODO Auto-generated method stub
		return orderMapper.selectSendingOrder(start, end);
	}

	@Override
	public int selectSendingOrderCount() {
		// TODO Auto-generated method stub
		return orderMapper.selectSendingOrderCount();
	}

	@Override
	public int insert(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderMapper.insertSelective(record);
	}

	@Override
	public OrderInfo selectByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderInfo record) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<OrderInfo> selectAuntByAddress(String address, String RouteTime) {
		// TODO Auto-generated method stub
		return orderMapper.selectAuntByAddress(address, RouteTime);
	}
	@Override
	public List<String> SelectTenDayUser(int start,int end) {
		// TODO Auto-generated method stub
		return orderMapper.SelectTenDayUser(start,end);
	}
	@Override
	public List<String> SelectTenDayAunt(int start,int end) {
		// TODO Auto-generated method stub
		return orderMapper.SelectTenDayAunt(start,end);
	}
	@Override
	public List<OrderInfo_YMS> selectAllOrderInfo() {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrderInfo();
	}

}
