package com.cyx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.UserOrderContentInfo;
import com.cyx.pojo.UserOrderInfo;

public interface IUserOrderService {
    List<AuntInfo>selectByOrder(int index, OrderInfo record);
    List<UserOrderInfo> selectByUserId(int index,String auntId);
    List<UserOrderInfo> selectUserOrderByUserId(int index,String auntId);
    int deleteByPrimaryKey(String index,String orderId);
    int insertSelective(int index, OrderInfo record);
    int updateByPrimaryKeySelective(int index, OrderInfo record);
    UserOrderInfo selectByOrderId(int index,String orderId);
    double selectMoneyByOrderId(int index,String orderId);
}
