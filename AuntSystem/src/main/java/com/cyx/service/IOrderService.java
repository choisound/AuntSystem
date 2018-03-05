package com.cyx.service;

import java.util.List;

import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.OrderInfo_YMS;

public interface IOrderService {
	int deleteByPrimaryKey(String orderId);
	 List<OrderInfo>selectAllOrder(int start,int end);
	    int selectAllOrderCount();
	    List<OrderInfo>selectSendingOrder(int start,int end);
	    int selectSendingOrderCount();
	    List<String>SelectTenDayUser(int start,int end);
	    List<String>SelectTenDayAunt(int start,int end);
    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderId);
    List<OrderInfo>selectAuntByAddress(String address,String RouteTime);
    int updateByPrimaryKeySelective(OrderInfo record);
    List<OrderInfo_YMS> selectAllOrderInfo();
    int updateByPrimaryKey(OrderInfo record);
}
