package com.cyx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.AuntOrderInfoMapper;
import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.OrderInfo;
import com.cyx.service.IAuntOrderService;
@Service("auntOrderService")
public class AuntOrderServiceImpl implements IAuntOrderService{
	@Resource
	AuntOrderInfoMapper auntOrderMapper;
	@Override
	public int deleteByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int insert(int index,OrderInfo record) {
		// TODO Auto-generated method stub
		return auntOrderMapper.insert(index, record);
	}

	@Override
	public int insertSelective(int index,OrderInfo record) {
		// TODO Auto-generated method stub
		return auntOrderMapper.insertSelective(index,record);
	}

	@Override
	public AuntOrderInfo selectByPrimaryKey(int index, String orderId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectByPrimaryKey(index, orderId);
	}

	@Override
	public int updateByPrimaryKeySelective(int index,OrderInfo record) {
		// TODO Auto-generated method stub
		return auntOrderMapper.updateByPrimaryKeySelective(index,record);
	}

	@Override
	public int updateByPrimaryKey(int index,OrderInfo record) {
		// TODO Auto-generated method stub
		return auntOrderMapper.updateByPrimaryKey(index, record);
	}

	@Override
	public List<AuntOrderInfo> selectByAuntId(int index, String auntId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectByAuntId(index, auntId);
	}

	@Override
	public List<AuntOrderInfo> selectByAuntIdL(int index, String auntId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectByAuntIdL(index, auntId);
	}

	@Override
	public int deleteByIndexAuntId(String index, String auntId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.deleteByIndexAuntId(index, auntId);
	}

	@Override
	public  CountSum selectMoneyEachMonth(int index, String auntId) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectMoneyEachMonth(index, auntId);
	}

	@Override
	public List<AuntOrderInfo> selectOrderEachMonth(int index, String auntId,String month) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectOrderEachMonth(index, auntId,month);
	}

	@Override
	public String selectOnlineMoneyEachMonth(int index, String auntId,String month) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectOnlineMoneyEachMonth(index, auntId,month);
	}

	@Override
	public String selectofflineMoneyEachMonth(int index, String auntId,String month) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectOfflineMoneyEachMonth(index, auntId,month);
	}

	@Override
	public List<AuntOrderInfo> selectOnlineOrderEachMonth(int index,
			String auntId, String month) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectOnlineOrderEachMonth(index, auntId, month);
	}

	@Override
	public List<AuntOrderInfo> selectOfflineOrderEachMonth(int index,
			String auntId, String month) {
		// TODO Auto-generated method stub
		return auntOrderMapper.selectOfflineOrderEachMonth(index, auntId, month);
	}


}
