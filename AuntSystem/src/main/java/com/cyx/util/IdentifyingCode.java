package com.cyx.util;

import java.text.SimpleDateFormat;

public class IdentifyingCode {
	public static String GenerateCode(String phoneno,String Code,String time){
		String params="accountSid=47f38483fa5b4a408e988213e3987374"
				+ "&smsContent=【惠州学院】您的验证码为"+Code+"，请于10分钟内正确输入，如非本人操作，请忽略此短信。"
				+ "&to="+phoneno
				+ "&timestamp="+time
				+ "&sig="+MD5.getMd5("47f38483fa5b4a408e988213e3987374bae044000c734d56b70880b9b16411fa"+time)
				+ "&respDataType=JSON";
		HttpRequest httpRequest=new HttpRequest();
		String url="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
		String res="";
		try {
			res=httpRequest.request(url, params, "POST");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
