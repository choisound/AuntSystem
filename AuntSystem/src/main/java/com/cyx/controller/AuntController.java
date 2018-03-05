package com.cyx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.geohash.GeoHash;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.AuntServiceContent;
import com.cyx.pojo.AuntServiceInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.HolidayInfo;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ReturnJson1;
import com.cyx.pojo.ReturnJsonAunt;
import com.cyx.service.IAuntOrderService;
import com.cyx.service.IAuntService;
import com.cyx.service.IAuntServiceService;
import com.cyx.service.IHolidayService;
import com.cyx.service.IMonthPayService;
import com.cyx.service.IUserService;
import com.cyx.util.CheckJz;
import com.cyx.util.FileUtilImpl;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.IdentifyingCode;

@Controller
@RequestMapping("/aunt")
public class AuntController {
	@Resource
	IMonthPayService monthPayService;
	@Resource
	private IAuntService auntService;
	@Resource
	private IAuntOrderService auntOrderService;
	@Resource
	private IHolidayService holidayService;
	@Resource
	private IAuntServiceService auntServiceService;
	@Value("${filePath}")
	private String filePath=null;
	/**
	 * 注册手机号
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resiger_phoneinsert", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object PhoneInsert(HttpServletRequest request,Model model){
		AuntInfo a=auntService.selectByPhoneno(request.getParameter("auntPhoneno"));
		ReturnJson rj=new ReturnJson();
		String resgiterId=request.getParameter("resgiterId");
		String smsId=request.getParameter("smsId");
		String Code=request.getParameter("code");
		String data=(String) request.getSession().getAttribute(smsId);
		if(data==null){
			rj.setStatus(1);
			rj.setMsg("验证码不正确");
			return rj;
		}else{
			String ds[]=data.split(";");
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d1=null;
			try {
				d1 = inputFormat.parse(ds[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date d2=new Date();
			if(!Code.equalsIgnoreCase(ds[0])||d1==null||d2.getTime()-d1.getTime()>1000*60*5){
				rj.setStatus(1);
				rj.setMsg("验证码不正确");
				return rj;
			}
		}
		if(a!=null){
			rj.setStatus(1);
			rj.setMsg("该用户已存在");
			return rj;
		}
		
		AuntInfo aunt=new AuntInfo();
		aunt.setAuntId(GenerateIdUtil.generateId("aunt"));
		aunt.setResgiterId(resgiterId);
		aunt.setAuntPhoneno(request.getParameter("auntPhoneno"));
		aunt.setAuntPassword(request.getParameter("auntPassword"));
		aunt.setAuntState(0);
		int s=auntService.insertSelective(aunt);
		rj.setStatus(0);
		aunt.setAuntPassword(null);
		rj.setData(aunt);
		request.getSession().removeAttribute(smsId);;
		return rj;
	}
	/**
	 * 重置密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resiger_resetpasswd" , produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Resetpasswd(HttpServletRequest request,Model model){
		AuntInfo aunt=new AuntInfo();
		ReturnJson rj=new ReturnJson();
		String smsId=request.getParameter("smsId");
		String Code=request.getParameter("code");
		String auntPhoneno=request.getParameter("auntPhoneno");
		String auntPassword=request.getParameter("auntPassword");
		String data=(String) request.getSession().getAttribute(smsId);
		if(data==null){
			rj.setStatus(1);
			rj.setMsg("验证码不正确");
			return rj;
		}else{
			String ds[]=data.split(";");
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d1=null;
			try {
				d1 = inputFormat.parse(ds[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date d2=new Date();
			if(!Code.equalsIgnoreCase(ds[0])||d1==null||d2.getTime()-d1.getTime()>1000*60*5){
				rj.setStatus(1);
				rj.setMsg("验证码不正确");
				return rj;
			}
		}
		aunt.setAuntPhoneno(auntPhoneno);
		aunt.setAuntPassword(auntPassword);
		int res=auntService.updateByPhoneNoSelective(aunt);
		request.getSession().removeAttribute(smsId);
		if(res<0){
			rj.setStatus(1);
			rj.setMsg("重置密码失败");
			return rj;
		}else{
			rj.setStatus(0);
			aunt.setAuntPassword(null);
			return rj;
		}
	}
	/**
	 * 完善信息
	 * @param myfiles1
	 * @param myfiles
	 * @param request
	 * @param ex
	 * @return
	 */
	@RequestMapping(value ="/resiger_addinfo", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody Object Addinfo(@RequestParam CommonsMultipartFile myfiles1,@RequestParam CommonsMultipartFile myfiles,HttpServletRequest request,Exception ex){
		ReturnJson rj=new ReturnJson();
	 	FileUtilImpl fileUtilImpl=new FileUtilImpl(); 
	 	AuntInfo aunt=new AuntInfo();
		String auntName=request.getParameter("auntName");
		if(auntName!=null)
			aunt.setAuntName(auntName);
		String auntSex=request.getParameter("auntSex");
		if(auntSex!=null)
			aunt.setAuntSex(auntSex);
		String auntAddress=request.getParameter("auntAddress");
		String auntAddress1=CheckJz.LongLatExchange(auntAddress);
        String auntPhoneno=request.getParameter("auntPhoneno");
        String auntPositon=auntAddress;
		if(auntPositon!=null)
			aunt.setAuntPositon(auntPositon);
        if(auntPhoneno!=null)
        	aunt.setAuntPhoneno(auntPhoneno);
        if(auntAddress1!=null)
        	aunt.setAuntAddress(auntAddress1);
		String auntIdentity=request.getParameter("auntIdentity");
		aunt.setAuntIdentity(auntIdentity);
		aunt.setAuntState(1);
		AuntInfo a=auntService.selectByIdentity(auntIdentity);
		if(a!=null&&!a.getAuntPhoneno().equalsIgnoreCase(auntPhoneno)){
			rj.setStatus(1);
			rj.setMsg("该身份证已被注册");
			return rj;
		}
		String auntIdentityimage=fileUtilImpl.uploadFile(myfiles,filePath);
		if(auntIdentityimage!=null)
			aunt.setAuntIdentityimage(auntIdentityimage);
		String auntIdentityibackamge=fileUtilImpl.uploadFile(myfiles1,filePath);
		if(auntIdentityibackamge!=null)
			aunt.setAuntIdentityibackamge(auntIdentityibackamge);
		String auntCount=request.getParameter("auntCount");
		if(auntCount!=null)
			aunt.setAuntCount(auntCount);
		Integer auntParttimejob=0;
		if(request.getParameter("auntParttimejob")!=null){
			try{
				auntParttimejob=Integer.parseInt(request.getParameter("auntParttimejob"));
			}catch(Exception e){
				System.out.println(e);
			}
		}
		aunt.setAuntParttimejob(auntParttimejob);
		String phono=request.getParameter("auntPhoneno");
		a=auntService.selectByPhoneno(phono);
		System.out.println(a);
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("该用户不存在");
			return rj;
		}
		String serviceIds[]={"0","1","2","3"};
		List<AuntServiceInfo> lists=new ArrayList<AuntServiceInfo>();
		AuntServiceInfo auntServiceinfo;
		for(int i=0;i<serviceIds.length;i++){
			auntServiceinfo=new AuntServiceInfo();
			auntServiceinfo.setAuntId(a.getAuntId());
			auntServiceinfo.setServiceId(serviceIds[i]);
			auntServiceinfo.setAuntserviceNo(GenerateIdUtil.generateId("auntservice"));
			lists.add(auntServiceinfo);
		}
		try{
			auntServiceService.insertList(lists);
		}catch(Exception e){
			e.printStackTrace();
		}
		aunt.setAuntId(a.getAuntId());
		aunt.setResgiterId(a.getResgiterId());
		aunt.setAuntState(1);
		int ress=auntService.updateByPhoneNoSelective(aunt);
		if(ress>=0){
			rj.setStatus(0);
			aunt.setAuntAddress(auntAddress);
			rj.setData(aunt);
			return rj;
		}else{
			rj.setStatus(1);
			rj.setMsg("添加信息失败");
			return rj;
		}
	}
	/**
	 * 管理员审核，更新状态
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updatestate", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object updateState(HttpServletRequest request,Model model)
	{
		 int status = Integer.parseInt(request.getParameter("aunt_status"));
		 String aunt_id=request.getParameter("aunt_id");
		 System.out.println(aunt_id);
		 System.out.println(status);
		AuntInfo aunt=new AuntInfo();
		aunt.setAuntState(status);
		aunt.setAuntId(aunt_id);
		int i=auntService.updateByPrimaryKeySelective(aunt);
		ReturnJson1 rj= new ReturnJson1();
		if(i>=0){
			rj.setStatus(0);
		}else{
			rj.setStatus(1);
		}
		return rj;
	}
	/**
	 * android通过阿姨id找到阿姨
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findauntbyId", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindAuntById(HttpServletRequest request,Model model)
	{
		String aunt_id=request.getParameter("aunt_id");
		AuntInfo info = auntService.selectByPrimaryKey(aunt_id);
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		rj.setData(info);
		return rj;
	}
	/**
	 * 管理员通过阿姨id找到阿姨
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminfindauntbyId", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindAunt1ById(HttpServletRequest request,Model model)
	{
		String aunt_id=request.getParameter("aunt_id");
		AuntInfo info = auntService.adminselectByPrimaryKey(aunt_id);
		List<AuntServiceContent> auntservicecontents = auntServiceService.selectByAuntId(aunt_id);
		aunt_id=aunt_id.replace("\"", "");
		int index = Integer.valueOf(aunt_id
				.substring(aunt_id.length() - 2)) % 5;
		System.out.println(index);
		Calendar c=Calendar.getInstance();
		String year=String.valueOf(c.get(Calendar.YEAR));
		List<String> list = monthPayService.selectmonthbyauntid(aunt_id, year);
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
			if(k<list.size()&&list.get(k).equalsIgnoreCase(month)){
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
		CountSum ls=auntOrderService.selectMoneyEachMonth(index, aunt_id);
		ReturnJsonAunt rj=new ReturnJsonAunt();
		rj.setAuntservicecontents(auntservicecontents);
		rj.setAuntInfo(info);
		rj.setMoneyPay(res);
		if(ls.getSum()!=null)
			rj.setMoney(Double.valueOf(ls.getSum()));
		else
			rj.setMoney(0.0);
		rj.setCount(Integer.parseInt(ls.getCount()));
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 找到所有阿姨
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findallaunt", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object selectAllList(HttpServletRequest request,Model model)
	{
		int page = Integer.valueOf(request.getParameter("page"));
		List<AuntInfo> auntservicecontents = auntService.selectAllList(page*20,page*20+20);
		int count=auntService.selectAllListCount();
		ReturnJson1 rj=new ReturnJson1();
		rj.setStatus(0);
		rj.setCount(count);
		rj.setData(auntservicecontents);
		return rj;
	}
	/**
	 * 找到待审核阿姨
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findcheckaunt", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object selectCheckedAunt(HttpServletRequest request,Model model)
	{
		int page = Integer.valueOf(request.getParameter("page"));
		List<AuntInfo> auntservicecontents = auntService.selectResigerList(page*20,page*20+20);
		int count=auntService.selectResigerListCount();
		ReturnJson1 rj=new ReturnJson1();
		rj.setStatus(0);
		rj.setCount(count);
		rj.setData(auntservicecontents);
		return rj;
	}
	/**
	 * 找到阿姨所能服务的类别
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findservice", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object findService(HttpServletRequest request,Model model)
	{
		String auntId=request.getParameter("auntId");
		List<AuntServiceContent> auntservicecontents = auntServiceService.selectByAuntId(auntId);
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		rj.setData(auntservicecontents);
		return rj;
	}
	/**
	 * 删除阿姨
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Delete(HttpServletRequest request,Model model)
	{
		ReturnJson1 rj=new ReturnJson1();
		String auntId=request.getParameter("aunt_id");
		int state=auntService.deleteByPrimaryKey(auntId);
		if(state>=0){
			rj.setStatus(0);
		}else{
			rj.setStatus(1);
		}
		return rj;
	}
	/**
	 * 给阿姨添加服务
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addservice", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object AddService(HttpServletRequest request,Model model)
	{
		ReturnJson rj=new ReturnJson();
		String auntId=request.getParameter("auntId");
//		String serviceId=request.getParameter("serviceId");
		String serviceId=request.getParameter("serviceId");
		String[] serviceIds=serviceId.split(",");
		AuntServiceInfo auntService;
		System.out.println(serviceIds.toString());
		List<AuntServiceInfo> lists=new ArrayList<AuntServiceInfo>();
		for(int i=0;i<serviceIds.length;i++){
			auntService=new AuntServiceInfo();
			auntService.setAuntId(auntId);
			auntService.setServiceId(serviceIds[i]);
			auntService.setAuntserviceNo(GenerateIdUtil.generateId("auntservice"));
			lists.add(auntService);
		}
		String res="";
		int rc=1;
		try{
			rc=auntServiceService.insertList(lists);
		}catch(Exception e){
			rj.setStatus(1);
			rj.setMsg("添加服务失败");
			return rj;
		}
		if(rc>0){
			rj.setStatus(0);
			return rj;
		}else{
			rj.setStatus(1);
			rj.setMsg("添加服务失败");
			return rj;
		}
	}
	/**
	 * 阿姨登录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object login(HttpServletRequest request,Model model){
		
		String auntPhoneno=request.getParameter("auntPhoneno");
		String auntPassword=request.getParameter("auntPassword");
		Object o=request.getSession().getAttribute("time"+auntPhoneno);
		if(o!=null&&(int)o>=5){
			request.getSession().setAttribute(auntPhoneno, new Date());
		}
		Date d=(Date)request.getSession().getAttribute(auntPhoneno);
		if(d!=null&&(new Date().getTime()-d.getTime()<1000*60*30)){
			request.getSession().removeAttribute("time"+auntPhoneno);
			ReturnJson rj=new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("错误超过五次，该账号已被锁定，请半小时后登录");
			return rj;
		}
		//判断手机号码是否存在
		AuntInfo a=auntService.selectByPhoneno(auntPhoneno);
		ReturnJson rj=new ReturnJson();
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("该手机号码不存在");
			return rj;
		}
		//判断账号密码是否匹配
		a=auntService.selectByPhonenoAndPassword(auntPhoneno, auntPassword);
		if(a==null){
			//判断五次密码错误的
			Object o1= request.getSession().getAttribute("time"+auntPhoneno);
			int er=0;
			if(o1!=null){
				er=(int)o1+1;
			}else{
				er=1;
			}
			request.getSession().setAttribute("time"+auntPhoneno, er);
			rj.setStatus(1);
			rj.setMsg("账号密码不匹配");
			return rj;
		}else{
			request.getSession().removeAttribute(auntPhoneno);
			request.getSession().removeAttribute("time"+auntPhoneno);
			rj.setStatus(0);
			a.setAuntPassword(null);
			rj.setData(a);
			return rj;
		}
	}
	/**
	 * 阿姨请假
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addholiday", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object addHoliday(HttpServletRequest request,Model model)
	{
		ReturnJson rj=new ReturnJson();
		String auntId=request.getParameter("auntId");
		String holiday_starttime=request.getParameter("starttime");
		String holiday_endtime=request.getParameter("endtime");
		HolidayInfo holiday=null;
		holiday=new HolidayInfo(GenerateIdUtil.generateId("holiday"), auntId,holiday_starttime, holiday_endtime);
		try{
			if(holidayService.insertSelective(holiday)<0){
				rj.setStatus(1);
				rj.setMsg("添加请假数据失败");
			}else{
				rj.setStatus(0);
			}
		}catch(Exception e){
			rj.setStatus(1);
			rj.setMsg("该请假信息已存在");
		}
		return rj;
	}
	/**
	 * 找到阿姨当月假期
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findholiday", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindHoliday(HttpServletRequest request,Model model)
	{
		String auntId=request.getParameter("auntId");
		String mouth=request.getParameter("mouth");
		List<HolidayInfo> holidays = holidayService.selectByAuntId(auntId,mouth);
		ReturnJson rj=new ReturnJson();
		rj.setStatus(0);
		rj.setData(holidays);
		return rj;
	}
}
