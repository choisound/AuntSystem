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
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ServiceInfo;
import com.cyx.service.IAuntServiceService;
import com.cyx.service.IServiceService;
import com.cyx.service.impl.ServiceServiceImpl;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/service")
public class ServiceController {
	@Resource
	IServiceService serviceServiceImpl;
	@Resource
	IAuntServiceService auntServiceService;
	/**
	 * 添加服务类型
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object serviceAdd(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		ServiceInfo service=new ServiceInfo();
		service.setServiceId(GenerateIdUtil.generateId("service"));
		service.setServiceDesc(request.getParameter("servicedesc"));
		service.setServiceName(request.getParameter("servicename"));
		service.setServicePrice(request.getParameter("serviceprice"));
		try{
			serviceServiceImpl.insert(service);
		}catch(Exception e){
			rj.setStatus(1);
			rj.setMsg(request.getParameter("servicename")+"已存在");
			return rj;
		}
		rj.setData(service);
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 修改服务数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object serviceEdit(HttpServletRequest request,Model model){
		ServiceInfo service=new ServiceInfo();
		service.setServiceDesc(request.getParameter("servicedesc"));
		service.setServiceName(request.getParameter("servicename"));
		service.setServicePrice(request.getParameter("serviceprice"));
		serviceServiceImpl.updateByNameSelective(service);
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		rj.setData(service);
		return rj;
	}
	/**
	 * 删除服务
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteService(HttpServletRequest request,Model model){
		String name=request.getParameter("servicename");
		serviceServiceImpl.deleteByName(name);
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 通过阿姨id找到服务
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findservices", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object SeletServices(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		String auntId=request.getParameter("aunt_id");
		rj.setData(auntServiceService.selectByAuntId(auntId));
		return rj;
	}
	/**
	 * 找到系统的服务
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object SeletAllService(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		rj.setData(serviceServiceImpl.selectAll());
		return rj;
	}
}
