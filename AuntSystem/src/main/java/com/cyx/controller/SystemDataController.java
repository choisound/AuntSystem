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
import com.cyx.pojo.SystemBlacklistInfo;
import com.cyx.pojo.SystemDataInfo;
import com.cyx.service.ISystemDataService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/systemdata")
public class SystemDataController {
	@Resource
	ISystemDataService isds;
	/**
	 * 添加系统常量
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object insertSystemData(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String systemDataId = GenerateIdUtil.generateId("sysdata");
		String dataName = req.getParameter("date_name");
		String dataNum = req.getParameter("date_num");
		
		if(dataName==null||dataName==""){
			rj.setStatus(1);
			rj.setMsg("添加系统常量失败");
		}
		else{
			int res = isds.addSystemData(new SystemDataInfo(systemDataId,dataName,dataNum));
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("添加系统常量失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 移除系统常量
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteSystemData(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String key = req.getParameter("systemdate_id");
		
		if(key==null||key==""){
			rj.setStatus(1);
			rj.setMsg("删除系统常量失败");
		}
		else{
		
			int res = isds.deleteSystemData(key);
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除系统常量失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 更新系统常量
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object updateSystemData(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String systemdataId = req.getParameter("systemdate_id");
		String dataNum = req.getParameter("date_num");
		
		if(systemdataId==null||systemdataId==""||dataNum==null||dataNum==""){
			rj.setStatus(1);
			rj.setMsg("更新系统常量失败");
		}
		else{
			
			int res = isds.updateSystemData(new SystemDataInfo(systemdataId,null,dataNum));
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("更新系统常量失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 找到系统常量详细信息
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findSystemData(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String dataName = req.getParameter("date_name");
		
		if(dataName==null||dataName==""){
			rj.setStatus(1);
			rj.setMsg("查找系统常量失败");
		}
		else{
			SystemDataInfo sdi = isds.findSystemData(dataName);
			if(sdi==null){
				rj.setStatus(1);
				rj.setMsg("查找系统常量失败");
			}
			else{
				rj.setData(sdi);
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 找到所有系统常量
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findall",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findallSystemShareByauntId(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		List<SystemDataInfo> sdi = isds.findAll();
		if(sdi==null||sdi.size()<=0){
			rj.setStatus(1);
			rj.setMsg("查找系统常量失败");
		}
		else{
			rj.setData(sdi);
			rj.setStatus(0);
		}	
			return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
}
