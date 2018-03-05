package com.cyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.UserOrderContentInfo;
import com.cyx.pojo.UserOrderInfo;

public interface UserOrderInfoMapper {
    int deleteByPrimaryKey(@Param("index")String index,@Param("orderId")String orderId);
    List<AuntInfo>selectByOrder(@Param("index")int index, @Param("userorder")OrderInfo record);
    
    int insert(@Param("index")int index, @Param("userorder")OrderInfo record);
    List<UserOrderInfo> selectByUserId(@Param("index")int index,@Param("userId")String auntId);
    List<UserOrderInfo> selectUserOrderId(@Param("index")int index,@Param("userId")String auntId);
    UserOrderInfo selectByOrderId(@Param("index")int index,@Param("orderId")String orderId);
    int insertSelective(@Param("index")int index, @Param("userorder")OrderInfo record);
    UserOrderInfo selectByPrimaryKey(String index,String orderId);
    int updateByPrimaryKeySelective(@Param("index")int index, @Param("userorder")OrderInfo record);
    int updateByPrimaryKey(@Param("index")int index, @Param("userorder")OrderInfo record);
    double selectMoneyByOrderId(@Param("index")int index,@Param("orderId")String orderId);
}