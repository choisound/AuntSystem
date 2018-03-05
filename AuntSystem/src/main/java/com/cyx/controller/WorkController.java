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
import com.cyx.pojo.WorkInfo;
import com.cyx.service.IWorkService;
import com.cyx.util.GenerateIdUtil;

@Controller
@RequestMapping("/work")
public class WorkController {
	@Resource
	IWorkService workService;
	/**
	 * 添加工作表
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object insertWork(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String workId = GenerateIdUtil.generateId("work");
		String auntId = req.getParameter("aunt_id");
		String work_starttime = req.getParameter("work_starttime");
		String work_endtime = req.getParameter("work_endtime");
		
		if(auntId==null||auntId==""||work_starttime==null||work_starttime==""||work_endtime==""||work_endtime==null){
			rj.setStatus(1);
			rj.setMsg("添加工作表失败");
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			int res = 0;
			try {
				res = workService.insertWork(new WorkInfo(workId,auntId,sdf.parse(work_starttime),sdf.parse(work_endtime)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("添加工作表失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	}
	/**
	 * 移除工作表
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object deleteWork(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String workId = req.getParameter("work_id");
		
		if(workId==null||workId==""){
			rj.setStatus(1);
			rj.setMsg("删除工作表失败");
		}
		else{
		
			int res = workService.deleteWorkByPrimaryKey(workId);
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("删除工作表失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	}
	/**
	 * 找到工作表
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findWork(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String auntId = req.getParameter("aunt_id");
		if(auntId==null||auntId==""){
			rj.setStatus(1);
			rj.setMsg("查找工作表失败");
		}
		else{
			List<WorkInfo> list = workService.selectByAuntId(auntId);
			if(list==null||list.size()<=0){
				rj.setStatus(1);
				rj.setMsg("查找工作表失败");
			}
			else{
				rj.setData(list);
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 更新工作表
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object updateWork(HttpServletRequest req,Model model)
	{	
		ReturnJson rj = new ReturnJson();
		
		String workId = req.getParameter("work_id");
		String auntId = req.getParameter("aunt_id");
		String work_starttime = req.getParameter("work_starttime");
		String work_endtime = req.getParameter("work_endtime");
		
		if(workId==null||workId==""||work_starttime==null||work_starttime==""||work_endtime==""||work_endtime==null){
			rj.setStatus(1);
			rj.setMsg("更新工作表失败");
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			int res = 0;
			try {
				res = workService.updateWork(new WorkInfo(workId,auntId,sdf.parse(work_starttime),sdf.parse(work_endtime)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(res<=0){
				rj.setStatus(1);
				rj.setMsg("更新工作表失败");
			}
			else{
				rj.setStatus(0);
			}	
		}
		return rj;
	//	return JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue);
	}
}
