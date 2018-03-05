package com.cyx.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyx.pojo.ReturnJson;
import com.cyx.util.IdentifyingCode;
@Controller
@RequestMapping("/smscode/")
public class SMSCodeController {
	/**
	 * 发送短信验证码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getsmscode", produces = "text/html;charset=UTF-8")
	public @ResponseBody Object GetIdentityCode(HttpServletRequest request,Model model){
		String phoneNum=request.getParameter("phoneno");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String code=String.valueOf((int)(Math.random()*1000000));
		String time=sdf.format(new Date());
		String str=IdentifyingCode.GenerateCode(phoneNum, code, time);
		ReturnJson rj=new ReturnJson();
		JSONObject parseObject = JSON.parseObject(str);
		System.out.println(parseObject.getString("respCode"));
		System.out.println(code);
		if(parseObject.getString("respCode").contains("00000")){
			String SmsId=parseObject.getString("smsId");
			request.getSession().setAttribute(SmsId, code+";"+time);
			rj.setStatus(0);
			rj.setData(SmsId);
		}else{
			rj.setStatus(1);
			rj.setMsg("短信验证码获取失败");
		}
		System.out.println(JSON.toJSONString(rj,SerializerFeature.WriteMapNullValue));
		return rj;
	}
}
