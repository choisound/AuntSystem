package com.cyx.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyx.pojo.MonthlyPay;
import com.cyx.pojo.ReturnJson;
import com.cyx.service.IMonthPayService;

@Controller
@RequestMapping("monthlypay")
public class MonthlyPayController {
	@Resource
	IMonthPayService monthPayService;
	/**
	 * 月结
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pay",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Pay(HttpServletRequest request,Model model)
	{
		String month=request.getParameter("month");
		String auntId=request.getParameter("auntId");
		ReturnJson rj=new ReturnJson();
		if(month==null||auntId==null||month==""||auntId==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		try{
			MonthlyPay monthlyPay=new MonthlyPay();
			monthlyPay.setAuntId(auntId);
			monthlyPay.setMonthlypayMouth(month);
			monthPayService.insertSelective(monthlyPay);
			rj.setStatus(0);
		}catch(Exception e){
			rj.setStatus(1);
			rj.setMsg("月结失败");
			e.printStackTrace();
		}
		return rj;
	}
	/**
	 * 查询月结数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/querymonthly",produces = "text/html;charset=UTF-8")
	public @ResponseBody Object QueryMonthly(HttpServletRequest request,Model model)
	{
		String year=request.getParameter("year");
		String auntId=request.getParameter("auntId");
		ReturnJson rj=new ReturnJson();
		if(year==null||auntId==null||year==""||auntId==""){
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		try{
			List<String> lists=monthPayService.selectmonthbyauntid(auntId, year);
			Map<String,Integer> m = new TreeMap<String,Integer>(new Comparator<String>(){ 
				   public int compare(String obj1,String obj2){ 
				    //降序排序 
				    return obj1.compareTo(obj2); 
				   } 
				  }); 
			int k=0;
			for(int i=1;i<13;i++){
				String month=year+"-";
				if(i<10){
					month+="0"+i;
				}else{
					month+=i;
				}
				if(k<lists.size()&&lists.get(k).equalsIgnoreCase(month)){
					k++;
					m.put(month, 1);
				}else{
					m.put(month, 0);
				}
			}
			List<Integer>res=new ArrayList<Integer>();
			for(Entry<String, Integer> item : m.entrySet()){
				res.add(item.getValue());
			}
			rj.setData(res);
			rj.setStatus(0);
		}catch(Exception e){
			rj.setStatus(1);
			rj.setMsg("月结失败");
			e.printStackTrace();
		}
		return rj;
	}
}
