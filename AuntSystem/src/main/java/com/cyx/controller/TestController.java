package com.cyx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.AssetOfHash;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.SystemShareInfo;
import com.cyx.pojo.WorkInfo;
import com.cyx.service.IOrderService;
import com.cyx.service.IWorkService;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.JpushClientUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	IWorkService workService;
	@Resource
	IOrderService orderService;
	@RequestMapping(value="/test",produces = "text/html;charset=UTF-8")
	public @ResponseBody void insertWork(HttpServletRequest req,Model model)
	{	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date(0);
		try {
			d = df.parse("1990-10-09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AssetOfHash.Map_auntDate.put("11111", d);
		List<AuntInfo>l=new ArrayList<AuntInfo>();
		AuntInfo auntInfo=new AuntInfo();
		auntInfo.setAuntId("aunt_20174201836573208b49d0b174c098cbe7f4b30");
		auntInfo.setResgiterId("13065ffa4e3871d2459");
		l.add(auntInfo);
		AssetOfHash.Map_auntList.put("11111", l);
		OrderInfo o=orderService.selectByPrimaryKey("order_201745164756134932aa5f82af4bcabca49bef9e648dc1");
		AssetOfHash.Map_OrderInfo.put("11111",o );
		AssetOfHash.Map_userResId.put("user_2017420164829209cf49ea940220944819dcd10", "160a3797c83756d0479");
	}
	@RequestMapping(value="/test1",produces = "text/html;charset=UTF-8")
	public @ResponseBody void insertWork1(HttpServletRequest req,Model model)
	{	
		JpushClientUtil.sendToRegistrationId_user
		(AssetOfHash.Map_userResId.get("11111"), "您的订单已经被蔡阿姨接收", "您的订单已经被阿姨接收,详情请查看订单详情", "您的订单已经被阿姨接收", 0);
		AssetOfHash.Map_auntDate.remove("11111");
		AssetOfHash.Map_auntList.remove("11111");
		AssetOfHash.Map_OrderInfo.remove("11111");
		AssetOfHash.Map_userResId.remove("11111");
	}
}
