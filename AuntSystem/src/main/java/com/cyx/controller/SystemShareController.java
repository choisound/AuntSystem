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
import com.cyx.pojo.SystemShareInfo;
import com.cyx.service.ISystemShareService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/systemshare")
public class SystemShareController {
	@Resource
	ISystemShareService iss;
	/**
	 * 系统管理员分享
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object insertSystemShare(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String systemShareId = GenerateIdUtil.generateId("share");
		String adminId = req.getParameter("admin_id");
		String shareTime = req.getParameter("share_time");
		String shareContent = req.getParameter("share_content");
		
		if(adminId==null||adminId==""||shareTime==null||shareTime==""||shareContent==""||shareContent==null){
			rj.setStatus(1);
			rj.setMsg("添加系统分享失败");
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			int res = 0;
			try {
				res = iss.insert(new SystemShareInfo(systemShareId,adminId,sdf.parse(shareTime),shareContent));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(res<0){
				rj.setStatus(1);
				rj.setMsg("添加系统分享失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 系统管理员删除分享
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteSystemShare(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String shareInfoId = req.getParameter("share_info_id");
		
		if(shareInfoId==null||shareInfoId==""){
			rj.setStatus(1);
			rj.setMsg("删除系统分享失败");
		}
		else{
		
			int res = iss.deleteByPrimaryKey(shareInfoId);
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除系统分享失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/*
	 * 找到分享
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findSystemShareByAdminId(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String adminId = req.getParameter("admin_id");
		
		if(adminId==null||adminId==""){
			rj.setStatus(1);
			rj.setMsg("查找系统分享失败");
		}
		else{
			List<SystemShareInfo> list = iss.findByAdminId(adminId);
			if(list==null||list.size()<=0){
				rj.setStatus(1);
				rj.setMsg("查找系统分享失败");
			}
			else{
				rj.setData(list);
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
}
