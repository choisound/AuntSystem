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
import com.cyx.pojo.FeedbackInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.service.IFeedbackService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
	
	@Resource
	IFeedbackService feedbackService;
	/**
	 * 添加反馈
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object AddFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String feedback_id=GenerateIdUtil.generateId("fb");//����
	//	String feedback_id=request.getParameter("feedback_id");
		String evaluate_id=request.getParameter("evaluate_id");
		String feedback_content=request.getParameter("feedback_content");
		//null
		if(feedback_id==""||evaluate_id==""||feedback_content==""||feedback_id==null||evaluate_id==null||feedback_content==null){
			rj.setMsg("�ظ�ʧ�ܣ����Ժ��ԡ�");
			rj.setStatus(1);
		}
		else{
			int res = feedbackService.insert(new FeedbackInfo(feedback_id,evaluate_id,feedback_content));
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("�ظ�ʧ�ܣ������³��ԡ�");
			}
		}
		return rj;
		
	}
	
	/**
	 * 删除反馈
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		
		String feedback_id=request.getParameter("feedback_id");
		if(feedback_id==null||feedback_id==""){
			rj.setStatus(1);
			rj.setMsg("删除评价反馈失败");
		}
		else{
			int res = feedbackService.deleteByPrimaryKey(feedback_id);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除评价反馈失败");
			}
			else{
				rj.setStatus(0);
			}			
		}
		return rj;
	}
}
