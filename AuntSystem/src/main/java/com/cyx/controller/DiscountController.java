package com.cyx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.DisCount;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.UserInfo;
import com.cyx.service.IAuntOrderService;
import com.cyx.service.IDisCountService;
import com.cyx.service.IOrderService;
import com.cyx.service.IUserOrderService;
import com.cyx.service.IUserService;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.JpushClientUtil;

@Controller
@RequestMapping(value="/discount" , produces = "text/html;charset=UTF-8")
public class DiscountController {
	@Resource
	IOrderService orderService;
	@Resource
	IAuntOrderService auntOrderService;
	@Resource
	IUserOrderService userOrderService;
	@Resource 
	IDisCountService discountService;
	@Resource
	IUserService userService;
	/**
	 * 通过用户id拿到优惠券
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getdiscounts",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findCustomerService(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String userId=request.getParameter("userId");
		if(userId==null||userId==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		System.out.println(userId);
		try{
			List<DisCount> list = discountService.selectDiscountByUserId(userId);
			System.out.println(list);
			rj.setData(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 使用优惠券
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usediscounts",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object UserDiscount(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String discountId=request.getParameter("discountId");
		String userId=request.getParameter("userId");
		String orderId=request.getParameter("orderId");
		String auntId=request.getParameter("auntId");
		if(auntId==null||auntId==""||userId==null||userId==""||orderId==null||orderId==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setOrderState(4);
		int index1 = Integer.valueOf(auntId.substring(auntId
				.length() - 2)) % 5;
		int i=orderService.updateByPrimaryKeySelective(orderInfo);
		int j=auntOrderService.updateByPrimaryKeySelective(index1,orderInfo);
		index1 = Integer.valueOf(userId.substring(userId
				.length() - 2)) % 5;
		int k=userOrderService.updateByPrimaryKeySelective(index1, orderInfo);
		if(discountId!=null&&discountId!=""){
			DisCount dc=new DisCount();
			dc.setDiscountId(discountId);
			dc.setIsuse(1);
			discountService.updateByPrimaryKeySelective(dc);
		}
		rj.setStatus(0);
		return rj;
	}	
	/**
	 * 分享获取优惠券
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/share",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Share(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String discountId=GenerateIdUtil.generateId("discount");
		String phone=request.getParameter("phone");
		if(phone==null||phone==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		UserInfo userinfo=userService.selectByPhoneno(phone);
		if(userinfo==null){
			rj.setStatus(1);
			rj.setMsg("该手机号码不存在");
		}
		String userId=userinfo.getUserId();
		double d=Math.random()*100;
		d=d%50;
		d=(int)(d*100);
		d/=100;
		String discountMoney=String.valueOf(d);
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)+10);
		Date date=curr.getTime();
		String discountLimit=String.valueOf((int)(d*6));
		DisCount dis=new DisCount();
		dis.setDiscountId(discountId);
		dis.setDiscountLimit(discountLimit);
		dis.setUserId(userId);
		dis.setDiscountMoney(String.valueOf(d));
		dis.setDiscountTime(date);
		dis.setIsuse(0);
		dis.setDiscountId(discountId);
		discountService.insert(dis);
		System.out.println(userinfo);
		JpushClientUtil.sendToRegistrationId_share(userinfo.getResgiterId(), "恭喜您获得一张价值"+discountMoney+"元的优惠券", "恭喜您获得一张价值"+discountMoney+"元的优惠券", "恭喜您获得一张价值"+discountMoney+"元的优惠券", null);
		rj.setStatus(0);
		rj.setData(dis);
		return rj;
	}
	/**
	 * 添加优惠券
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adddiscounts",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object AddDiscount(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String discountId=GenerateIdUtil.generateId("discount");
		String userId=request.getParameter("userId");
		if(userId==null||userId==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		double d=Math.random()*100;
		d=d%20;
		d=(int)(d*100);
		d/=100;
		String discountMoney=String.valueOf(d);
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date=df.parse(String.valueOf((new Date(date.getTime() + (long)10 * 24 * 60 * 60 * 1000))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String discountLimit=String.valueOf((int)(d*10));
		DisCount dis=new DisCount();
		dis.setDiscountId(discountId);
		dis.setDiscountLimit(discountLimit);
		dis.setUserId(userId);
		dis.setDiscountMoney(String.valueOf(d));
		dis.setDiscountTime(date);
		dis.setIsuse(0);
		dis.setDiscountId(discountId);
		discountService.insert(dis);
		UserInfo userinfo=userService.selectByPrimaryKey(userId);
		JpushClientUtil.sendToRegistrationId_user(userinfo.getResgiterId(), "恭喜您获得一张价值"+discountMoney+"元的优惠券", "恭喜您获得一张价值"+discountMoney+"元的优惠券", "恭喜您获得一张价值"+discountMoney+"元的优惠券", "恭喜您获得一张价值"+discountMoney+"元的优惠券");
		rj.setStatus(0);
		rj.setData(dis);
		return rj;
	}
}
