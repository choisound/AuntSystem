package com.cyx.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.CustomerServiceInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.service.ICustomerService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/customerservice")
public class CustomerServiceController {
	@Resource
	ICustomerService customerService;
	/**
	 * 插入客服
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/insert",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object InsertCustomerService(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String customerserviceId = GenerateIdUtil.generateId("kefu");
	//	String customerserviceId=request.getParameter("customerservice_id");
		String customerservicePhoneno=request.getParameter("customerservice_phoneno");
		//null
		if(customerserviceId==""||customerservicePhoneno==""||customerserviceId==null||customerservicePhoneno==null){
			rj.setStatus(1);
			rj.setMsg("插入客服信息失败");
		}
		else{
			CustomerServiceInfo customerServices=new CustomerServiceInfo(customerserviceId,customerservicePhoneno);
			int res = customerService.insert(customerServices);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("插入客服信息失败");
			}
			else{
				rj.setStatus(0);
				rj.setData(customerServices);
			}
		}
		return rj;
	}
	
	/*
	 * 删除客服
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteCustomerService(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();

		String customerservicePhoneno=request.getParameter("customerservice_phoneno");
		//null
		if(customerservicePhoneno==""||customerservicePhoneno==null){
			rj.setStatus(1);
			rj.setMsg("删除客服信息失败");
		}
		else{
			int res = customerService.deleteByPhoneno(customerservicePhoneno);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除客服信息失败");
			}
			else
				rj.setStatus(0);
		}
		return rj;
	}
	
	/**
	 * 找到所有客服
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findall",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findCustomerService(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		List<CustomerServiceInfo> list = customerService.selectAll();
		if(list.size()==0||list==null){
			rj.setStatus(1);
			rj.setMsg("查找客服信息失败");
		}
		else{
			rj.setStatus(0);
			rj.setData(list);
		}
		return rj;
	}
}
