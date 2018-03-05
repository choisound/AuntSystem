package com.cyx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.cyx.pojo.EvaluateInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.UserOrderInfo;
import com.cyx.service.IAuntOrderService;
import com.cyx.service.IAuntService;
import com.cyx.service.IEvaluateService;
import com.cyx.service.IOrderService;
import com.cyx.service.IUserOrderService;
import com.cyx.util.CheckJz;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
	@Resource
	IEvaluateService evaluateService;
	@Resource
	IOrderService orderService;
	@Resource
	IUserOrderService userOrderService;
	@Resource
	IAuntOrderService auntOrderService;
	/**
	 * 插入评价
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/insert",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findCustomerService(HttpServletRequest request,Model model)
	{
//	eval_2017818234137304672	order_20178182259417007111	user_20175401656653610	那里很满意！	5	2017-08-18 23:04:14	0
		ReturnJson rj = new ReturnJson();
		String evaluate_id=GenerateIdUtil.generateId("eval");
		String order_id=request.getParameter("order_id");
		String user_id=request.getParameter("user_id");
		String evaluate_content=request.getParameter("evaluate_content");
		String evaluate_star=request.getParameter("evaluate_star");
		int isvilify=1;
		if(CheckJz.check(evaluate_content)){
			isvilify=0;
		}
		if(evaluate_id==null||evaluate_id==""||order_id==null||order_id==""||user_id==null||
				user_id==""||evaluate_content==null||evaluate_star==null){
			rj.setStatus(1);
			rj.setMsg("插入评价失败");
		}
		else{
			int res=0;
			res = evaluateService.insert(new EvaluateInfo(evaluate_id, order_id, user_id, new Integer(evaluate_star), new Date(),new Integer(isvilify), evaluate_content));
			if(res<0){
				rj.setStatus(1);
				rj.setMsg("插入评价失败");
			}
			else{
				int index = Integer.valueOf(user_id.substring(user_id
						.length() - 2)) % 5;
				OrderInfo order=new OrderInfo();
				order.setOrderId(order_id);
				order.setOrderState(5);
				UserOrderInfo userOrderInfo=userOrderService.selectByOrderId(index, order_id);
				String auntId=userOrderInfo.getAuntId();
			    if(auntId!=null){
			    	int i=userOrderService.updateByPrimaryKeySelective(index, order);
					int j=orderService.updateByPrimaryKeySelective(order);
					int k=evaluateService.updateRateSelective(order_id);
			    	index = Integer.valueOf(auntId.substring(auntId
			    			.length() - 2)) % 5;
			    	int state=auntOrderService.updateByPrimaryKeySelective(index, order);
			    	System.out.println(i+"  "+j+"  "+k);
			    	System.out.println(state);
			    }
				rj.setStatus(0);
			}
		}
		return rj;
	}
	/**
	 * 通过订单id找到三星以上评价
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findthreestar",produces = "text/html;charset=UTF-8")//??������ʲô��˼
	public @ResponseBody Object findthreestar(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String order_id=request.getParameter("order_id");
		if(order_id==null||order_id==""){
			rj.setStatus(1);
			rj.setMsg("查找三星及其以上评价失败");
		}
		else{
			List<EvaluateInfo> list = evaluateService.selectThreestar(order_id);
			if(list==null||list.size()<=0){
				rj.setStatus(1);
				rj.setMsg("查找三星及其以上评价失败");
			}
			else{
				rj.setData(list);
				rj.setStatus(0);
			}
		}
		return rj;
		
	}
	/**
	 * 通过阿姨id查找三星以上评价
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findauntstar",produces = "text/html;charset=UTF-8")//??������ʲô��˼
	public @ResponseBody Object findAuntstar(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String aunt_id=request.getParameter("aunt_id");
		String service_id=request.getParameter("service_id");
		if(aunt_id==null||aunt_id==""||service_id==null||service_id==""){
			rj.setStatus(1);
			rj.setMsg("查找三星及其以上评价失败");
		}
		else{
			List<EvaluateInfo> list = evaluateService.selectAuntService(service_id, aunt_id);
			rj.setData(list);
			rj.setStatus(0);
		}
		return rj;
		
	}
	
	/**
	 * 删除评价
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deleteevaluate",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteevaluate(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String evaluate_id=request.getParameter("evaluate_id");
		if(evaluate_id==null||evaluate_id=="")
		{
			rj.setStatus(1);
			rj.setMsg("删除评价失败");
		}
		else{
			int res = evaluateService.deleteByPrimaryKey(evaluate_id);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除评价失败");
			}
			else{
				EvaluateInfo ei=evaluateService.selectByPrimaryKey(evaluate_id);
				evaluateService.updateRateSelective(ei.getOrderId());
				rj.setStatus(0);
			}
		}
		return rj;
	}
}
