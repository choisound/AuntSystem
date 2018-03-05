package com.cyx.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.AdminInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.service.IAdminService;

@Controller
@RequestMapping(value="/admin", produces = "text/html;charset=UTF-8")
public class AdminController {
	@Resource
	IAdminService adminService;
	/**
	 * 管理员登录
	 * @param adminPhoneno  电话号码
	 * @param adminPassword 密码
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody Object login(HttpServletRequest request,Model model){
		String adminPhoneno=request.getParameter("adminPhoneno");
		String adminPassword=request.getParameter("adminPassword");
		System.out.println(adminPassword);
		System.out.println(adminPhoneno);
		ReturnJson rj=new ReturnJson();
		AdminInfo a=adminService.selectByPhoneno(adminPhoneno);
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("该账户不存在");
			return rj;
		}
		a=adminService.selectByPhoneAndPassword(adminPhoneno, adminPassword);
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("账户密码不存在");
			return rj;
		}
		request.getSession().setAttribute("AdminLogin", a);
		a.setAdminPassword(null);
		rj.setData(a);
		rj.setStatus(0);
		return rj;
	}
	@RequestMapping("/datetime")
	public @ResponseBody Object DateTime(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		rj.setStatus(0);
		rj.setData(sdf.format(new Date()));
		return rj;
	}
}
