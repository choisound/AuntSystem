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
import com.cyx.pojo.ComplaintContentInfo;
import com.cyx.pojo.ComplaintInfo;
import com.cyx.pojo.FeedbackInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ReturnJson1;
import com.cyx.service.IComplaintService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {
	@Resource
	IComplaintService complaintService;
	/**
	 * 添加投诉
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object addComplaint(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		
		String complaint_id=GenerateIdUtil.generateId("complaint");
		String user_id=request.getParameter("user_id");
		String order_id=request.getParameter("order_id");
		String complaint_content=request.getParameter("content");
		//��
		if(user_id==null||order_id==null||complaint_content==null||
				user_id==""||order_id==""||complaint_content==""){
			rj.setStatus(1);
			rj.setMsg("添加订单反馈失败");
		}
		//������ݴ���
		else{
			int res = complaintService.insert(
					new ComplaintInfo(complaint_id,user_id,order_id,complaint_content));
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("添加订单反馈失败");
			}
			else{
				rj.setStatus(0);
			}
		}	
		return rj;
	}
	/**
	 * 找到所有投诉
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findall",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findAll(HttpServletRequest request,Model model)
	{	
		
		ReturnJson1 rj = new ReturnJson1();
		 int page = Integer.valueOf(request.getParameter("page"));
		 int count = complaintService.selectAllCount();
		 List<ComplaintContentInfo> all = complaintService.selectAllLimit(page*20, page*20+20);
		 rj.setCount(count);
		 rj.setData(all);
		 rj.setStatus(0);
		return rj;
	}
	/**
	 * 通过id查找投诉信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findbyid",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findByid(HttpServletRequest request,Model model)
	{	
		
		ReturnJson1 rj = new ReturnJson1();
		String complaint_id = request.getParameter("complaint_id");
		System.out.println(complaint_id);
		 ComplaintInfo info = complaintService.selectByPrimaryKey(complaint_id);
		 rj.setData(info);
		 rj.setStatus(0);
		return rj;
	}
	/**
	 * 通过用户id找到投诉
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findFeedBackByUserId(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String userId=request.getParameter("user_id");
		if(userId==null||userId==""){
			rj.setStatus(1);
			rj.setMsg("查找订单反馈失败");
		}
		else{
			List<ComplaintInfo> list = complaintService.selectByUserId(userId);
			if(list==null||list.size()<=0){
				rj.setStatus(1);
				rj.setMsg("查找订单反馈失败");
			}
			else{
				rj.setStatus(0);
				rj.setData(list);
			}
		}
		return rj;
	}
	/**
	 * 删除投诉
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteFeedBack(HttpServletRequest request,Model model)
	{
		ReturnJson rj = new ReturnJson();
		String complaintId=request.getParameter("complaint_id");
		if(complaintId==null||complaintId==""){
			rj.setStatus(0);
			rj.setMsg("删除订单反馈失败");
		}
		else{
			int res = complaintService.deleteByPrimaryKey(complaintId);
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除订单反馈失败");
			}
			else{
				rj.setStatus(0);
			}
		}
		return rj;
	}
}
