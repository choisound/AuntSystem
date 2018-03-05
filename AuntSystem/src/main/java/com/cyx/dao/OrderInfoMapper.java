package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.OrderInfo_YMS;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(String orderId);
    List<OrderInfo>selectAllOrder(int start,int end);
    int selectAllOrderCount();
    List<OrderInfo_YMS> selectAllOrderInfo();
    List<String>SelectTenDayUser(int start,int end);
    List<String>SelectTenDayAunt(int start,int end);
    List<OrderInfo>selectSendingOrder(int start,int end);
    int selectSendingOrderCount();
    int insert(OrderInfo record);
    List<OrderInfo>selectAuntByAddress(String address,String RouteTime);
    int insertSelective(OrderInfo record);
    OrderInfo selectByPrimaryKey(String orderId);
    int updateByPrimaryKeySelective(OrderInfo record);
    int updateByPrimaryKey(OrderInfo record);
}