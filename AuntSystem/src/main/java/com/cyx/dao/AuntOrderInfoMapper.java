package com.cyx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.OrderInfo;

public interface AuntOrderInfoMapper {
    int deleteByPrimaryKey(String orderId);
    List<AuntOrderInfo>selectOnlineOrderEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    List<AuntOrderInfo>selectOfflineOrderEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    List<AuntOrderInfo>selectOrderEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    String selectOnlineMoneyEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    String selectOfflineMoneyEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    CountSum selectMoneyEachMonth(@Param("index")int index,@Param("auntId")String auntId);
    int insert(@Param("index")int index, @Param("auntorder")OrderInfo record);
    int insertSelective(@Param("index")int index, @Param("auntorder")OrderInfo record);
    List<AuntOrderInfo>selectByAuntId(@Param("index")int index,@Param("auntId")String auntId);
    List<AuntOrderInfo>selectByAuntIdL(@Param("index")int index,@Param("auntId")String auntId);
    int deleteByIndexAuntId(@Param("index")String index,@Param("orderId")String auntId);
    AuntOrderInfo selectByPrimaryKey(@Param("index")int index,@Param("orderId")String orderId);
    int updateByPrimaryKeySelective(@Param("index")int index, @Param("auntorder")OrderInfo record);
    int updateByPrimaryKey(@Param("index")int index, @Param("auntorder")OrderInfo record);
}