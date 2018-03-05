package com.cyx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.cyx.pojo.ReturnJson1;
import com.cyx.pojo.SystemBlackListContentInfo;
import com.cyx.pojo.SystemBlacklistInfo;
import com.cyx.pojo.SystemShareInfo;
import com.cyx.service.ISystemBlacklistService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/systemblacklist")
public class SystemBlacklistController {
	@Resource
	ISystemBlacklistService systemBlacklistService;
	/**
	 * 添加系统黑名单
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object insertSystemShare(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String systemBlacklistId = GenerateIdUtil.generateId("sysbl");
		String auntId = req.getParameter("aunt_id");
		
		if(auntId==null||auntId==""){
			rj.setStatus(1);
			rj.setMsg("添加系统黑名单失败");
		}
		else{
			SystemBlacklistInfo syssinfo=new SystemBlacklistInfo();
			syssinfo.setAuntId(auntId);
			syssinfo.setSystemBlacklistId(systemBlacklistId);
			int res = systemBlacklistService.addSystemBlacklist(syssinfo);
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("添加系统黑名单失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 找到所有系统黑名单
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findalllimit",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindAll(HttpServletRequest req,Model model)
	{	
		ReturnJson1 rj = new ReturnJson1();
		int page = Integer.valueOf(req.getParameter("page"));
		int count=systemBlacklistService.selectAllCount();
		List<SystemBlackListContentInfo> all = systemBlacklistService.selectAll(page*20, page*20+20);
		rj.setCount(count);
		rj.setStatus(0);
		rj.setData(all);
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 删除系统黑名单
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteSystemShare(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		String auntId = req.getParameter("aunt_id");
		if(auntId==null||auntId==""){
			rj.setStatus(1);
			rj.setMsg("移除系统黑名单失败");
		}
		else{
		
			int res = systemBlacklistService.deleteSystemBlacklist(auntId);
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("移除系统黑名单失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 找到系统黑名单
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findSystemShareByauntId(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String auntId = req.getParameter("aunt_id");
		
		if(auntId==null||auntId==""){
			rj.setStatus(1);
			rj.setMsg("查找系统黑名单失败");
		}
		else{
			SystemBlacklistInfo sbi = systemBlacklistService.findSystemBlacklist(auntId);
			if(sbi==null){
				rj.setStatus(1);
				rj.setMsg("查找系统黑名单失败");
			}
			else{
				rj.setData(sbi);
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 找到所有系统黑名单
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findall",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findallSystemShareByauntId(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		List<SystemBlacklistInfo> sbi = systemBlacklistService.findallSystemBlacklist();
		if(sbi==null||sbi.size()<=0){
			rj.setStatus(1);
			rj.setMsg("查找系统黑名单失败");
		}
		else{
			rj.setData(sbi);
			rj.setStatus(0);
		}	
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
}
