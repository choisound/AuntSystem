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
import com.cyx.pojo.BlackListContentInfo;
import com.cyx.pojo.BlackListInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ReturnJson1;
import com.cyx.service.IBlackListService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/blacklist")
public class BlackListController {
	@Resource
	IBlackListService blackListService;
	/**
	 * 添加黑名单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")//�û���Ӱ��̺����Ѿ���½��
	public @ResponseBody Object AddFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		
		String blacklist_id=GenerateIdUtil.generateId("bl");
		String user_id=request.getParameter("user_id");
		String aunt_id=request.getParameter("aunt_id");
		if(user_id==null||user_id==""||aunt_id==null||aunt_id==""){
			rj.setStatus(1);
			rj.setMsg("添加黑名单失败");

		}else{
			int res = blackListService.insert(new BlackListInfo(blacklist_id,user_id,aunt_id));
			if(res<0){
				rj.setStatus(1);
				rj.setMsg("添加黑名单失败");
			}else{
				rj.setStatus(0);
			}
			
		}
		
		return rj;
	}
	/**
	 * 移除黑名单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String user_id=request.getParameter("user_id");
		String aunt_id=request.getParameter("aunt_id");
		//��
		if(user_id==null||user_id==""||aunt_id==null||aunt_id=="")
		{
			rj.setStatus(1);
			rj.setMsg("移除黑名单 失败");
		}
		else{
			int res = blackListService.deleteByAuntUserId(user_id, aunt_id);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("移除黑名单 失败");
			}
			else
				rj.setStatus(0);
		}
		return rj;
	}
	/**
	 * 找到所有黑名单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findall",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindAll(HttpServletRequest request,Model model)
	{
		ReturnJson1 rj = new ReturnJson1();
		int page = Integer.valueOf(request.getParameter("page"));
		String user_id=request.getParameter("user_id");
		if(user_id==null||user_id=="")
		{
			rj.setStatus(1);
			rj.setMsg("查找黑名单失败");
		}
		else{
			int count=blackListService.selectByUserIdLimitCount(user_id);
			List<BlackListContentInfo> list = blackListService.selectByUserIdLimit(user_id, page*7, page*7+7);
			rj.setStatus(0);
			rj.setCount(count);
			rj.setData(list);
		}
		return rj;
	}
	/**
	 * 黑名单详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String user_id=request.getParameter("user_id");
		if(user_id==null||user_id=="")
		{
			rj.setStatus(1);
			rj.setMsg("查找黑名单失败");
		}
		else{
			List<BlackListContentInfo> list = blackListService.selectByUserId(user_id);
			System.out.println(list);
			rj.setStatus(0);
			rj.setData(list);
		}
		return rj;
	}
}
