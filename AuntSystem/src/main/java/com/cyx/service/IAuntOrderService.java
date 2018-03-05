package com.cyx.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.OrderInfo;

public interface IAuntOrderService {
	List<AuntOrderInfo>selectOnlineOrderEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    List<AuntOrderInfo>selectOfflineOrderEachMonth(@Param("index")int index,@Param("auntId")String auntId,@Param("month")String month);
    List<AuntOrderInfo>selectOrderEachMonth(int index,String auntId,String month);
    String selectOnlineMoneyEachMonth(int index,String auntId,String month);
    String selectofflineMoneyEachMonth(int index,String auntId,String month);
    int deleteByPrimaryKey(String orderId);
    List<AuntOrderInfo>selectByAuntId(int index,String auntId);
    int insert(int index, OrderInfo record);
    int insertSelective(int index,OrderInfo record);
    AuntOrderInfo selectByPrimaryKey(int index,String orderId);
    List<AuntOrderInfo>selectByAuntIdL(@Param("index")int index,@Param("auntId")String auntId);
    int updateByPrimaryKeySelective(int index,OrderInfo record);
    int deleteByIndexAuntId(String index,@Param("orderId")String orderId);
    int updateByPrimaryKey(int index,OrderInfo record);
    CountSum selectMoneyEachMonth(@Param("index")int index,@Param("auntId")String auntId);
}
