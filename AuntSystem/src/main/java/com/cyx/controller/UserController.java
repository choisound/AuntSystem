package com.cyx.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.geohash.GeoHash;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.report.ReceivedsResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.DisCount;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ReturnJson1;
import com.cyx.pojo.UserInfo;
import com.cyx.service.IDisCountService;
import com.cyx.service.IUserService;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.JpushClientUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	@Resource
	private IDisCountService discountService;
	@RequestMapping(value="/testsmsId", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object SmsIdInsert(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		String smsId=request.getParameter("smsId");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String code=request.getParameter("code");
		System.out.println(smsId);
		System.out.println(code);
		String time=sdf.format(new Date());
		request.getSession().setAttribute(smsId, code+";"+time);
		System.out.println(smsId+"   "+request.getSession().getAttribute(smsId));
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 用户注册
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resiger_phoneinsert", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object PhoneInsert(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		String smsId=request.getParameter("smsId");
		String Code=request.getParameter("code");
		String resgiterId=request.getParameter("resgiterId");
		String data=(String) request.getSession().getAttribute(smsId);
		System.out.println(smsId+"  "+Code+"  "+resgiterId+"  "+data);
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
			System.out.println(ds[0]+ Code);
			if(!Code.equalsIgnoreCase(ds[0])||d1==null||d2.getTime()-d1.getTime()>1000*60*5){
				rj.setStatus(1);
				rj.setMsg("验证码不正确");
				return rj;
			}
		}
		
		UserInfo a=userService.selectByPhoneno(request.getParameter("userPhoneno"));
		if(a!=null){
			rj.setStatus(1);
			rj.setMsg("该手机号已注册");
			return rj;
		}
		UserInfo User=new UserInfo();
		User.setUserId(GenerateIdUtil.generateId("user"));
		User.setUserPhoneno(request.getParameter("userPhoneno"));
		User.setUserPassword(request.getParameter("userPassword"));
		User.setUserState(1);
		System.out.println("resgiterId==="+resgiterId);
		User.setResgiterId(resgiterId);
		int s=-1;
		s=userService.insertSelective(User);
		if(s<0){
			rj.setStatus(1);
			rj.setData(User);
			rj.setMsg("注册失败");
			return rj;
		}
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date=df.parse(String.valueOf((new Date(date.getTime() + (long)10 * 24 * 60 * 60 * 1000))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String discountId=GenerateIdUtil.generateId("discount");
		DisCount dis=new DisCount();
		dis.setDiscountId(discountId);
		dis.setDiscountLimit("0");
		dis.setUserId(User.getUserId());
		dis.setDiscountMoney("20");
		dis.setDiscountTime(date);
		dis.setIsuse(0);
		dis.setDiscountId(discountId);
		try{
			discountService.insert(dis);
			JpushClientUtil.sendToRegistrationId_user(User.getResgiterId(), "您获得一张二十元优惠券", "您获得一张二十元优惠券", "您获得一张二十元优惠券", "您获得一张二十元优惠券");
		}catch(Exception e){
			e.printStackTrace();
		}
		User.setUserPassword(null);
		rj.setData(User);
		rj.setStatus(0);
		request.getSession().removeAttribute(smsId);
		return rj;
	}
	/**
	 * 管理员分页找到用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findalllist", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object FindAllList(HttpServletRequest request,Model model){
		ReturnJson1 rj=new ReturnJson1();
		int page = Integer.valueOf(request.getParameter("page"));
		int count=userService.selectAllUserCount();
		List<UserInfo> allList = userService.selectAllList(page*20, 20+page*20);
		rj.setCount(count);
		rj.setData(allList);
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 管理员删除用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Delete(HttpServletRequest request,Model model){
		ReturnJson1 rj=new ReturnJson1();
		String  user_id = request.getParameter("user_id");
		int state=userService.deleteByPrimaryKey(user_id);
		if(state>=0){
			rj.setStatus(0);
		}else{
			rj.setStatus(1);
		}
		return rj;
	}
	/**
	 * 重置密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resiger_resetpasswd", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Resetpasswd(HttpServletRequest request,Model model){
		UserInfo User=new UserInfo();
		ReturnJson rj=new ReturnJson();
		String UserPhoneno=request.getParameter("userPhoneno");
		String UserPassword=request.getParameter("userPassword");
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
		User.setUserPhoneno(UserPhoneno);
		User.setUserPassword(UserPassword);
		int res=userService.updateByPhoneNoSelective(User);
		request.getSession().removeAttribute(smsId);
		if(res>=0){
			rj.setStatus(0);
			return rj;
		}else{
			rj.setStatus(1);
			rj.setMsg("重置密码失败");
			return rj;
		}
	}
	/**
	 * 完善信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resiger_addinfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object Addinfo(HttpServletRequest request,Model model){
		UserInfo User=new UserInfo();
		ReturnJson rj=new ReturnJson();
		String userName=request.getParameter("userName");
		User.setUserName(userName);
		String userSex=request.getParameter("userSex");
		User.setUserSex(userSex);
		User.setUserState(1);
		String phone=request.getParameter("userPhoneno");
		User.setUserCount(phone);
		System.out.println(phone);
		User.setUserPhoneno(phone);
		UserInfo a=userService.selectByPhoneno(phone);
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("该用户不存在");
			return rj;
		}
		System.out.println(User);
		int res=userService.updateByPhoneNoSelective(User);
		if(res>0){
			rj.setStatus(0);
			rj.setData(User);
			return rj;
		}else{
			rj.setStatus(1);
			rj.setMsg("添加用户信息失败");
			return rj;
		}
	}
	/**
	 * 通过阿姨id找阿姨详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find", produces = "application/json; charset=utf-8")
	public @ResponseBody Object find(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		String user_id =request.getParameter("user_id");
		UserInfo userInfo = userService.selectByPrimaryKey(user_id);
		rj.setStatus(0);
		rj.setData(userInfo);
		return rj;
	}
	/**
	 * 管理员查找用户详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminfind", produces = "application/json; charset=utf-8")
	public @ResponseBody Object find1(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		String user_id =request.getParameter("user_id");
		UserInfo userInfo = userService.adminselectByPrimaryKey(user_id);
		rj.setStatus(0);
		rj.setData(userInfo);
		return rj;
	}
	/**
	 * 登录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login", produces = "application/json; charset=utf-8")
	public @ResponseBody Object login(HttpServletRequest request,Model model){
		ReturnJson rj=new ReturnJson();
		String UserPhoneno=request.getParameter("userPhoneno");
		String UserPassword=request.getParameter("userPassword");
		Object o=request.getSession().getAttribute("usertime"+UserPhoneno);
		if(o!=null&&(int)o>=5){
			request.getSession().setAttribute("user"+UserPhoneno, new Date());
		}
		Date d=(Date)request.getSession().getAttribute("user"+UserPhoneno);
		if(d!=null&&(new Date().getTime()-d.getTime()<1000*60*30)){
			request.getSession().removeAttribute("usertime"+UserPhoneno);
			rj.setStatus(1);
			rj.setMsg("错误超过五次，该账号已被锁定，请半小时后登录");
			return rj;
		}
		UserInfo a=userService.selectByPhoneno(UserPhoneno);
		if(a==null){
			rj.setStatus(1);
			rj.setMsg("该账号不存在");
			return rj;
		}
		a=userService.selectByPhonenoAndPassword(UserPhoneno, UserPassword);
		if(a!=null){
			request.getSession().removeAttribute("user"+UserPhoneno);
			request.getSession().removeAttribute("usertime"+UserPhoneno);
			rj.setStatus(0);
			a.setUserPassword(null);
			String address=a.getUserAddress();
			if(address!=""&&address!=null){
				GeoHash g=GeoHash.fromGeohashString(address);
				a.setUserAddress(g.getPoint().toString());
			}
			rj.setData(a);
			return rj;
		}else{
			Object o1= request.getSession().getAttribute("usertime"+UserPhoneno);
			int er=0;
			if(o1!=null){
				er=(int)o1+1;
			}else{
				er=1;
			}
			request.getSession().setAttribute("usertime"+UserPhoneno, er);
			rj.setStatus(1);
			rj.setMsg("账号密码不匹配");
			return rj;
		}
		
	}
}