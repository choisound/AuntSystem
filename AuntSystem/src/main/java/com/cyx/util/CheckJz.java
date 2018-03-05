package com.cyx.util;

import java.util.List;

import ch.hsr.geohash.GeoHash;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CheckJz {
	public static boolean check(String str){
		HttpRequest hr=new HttpRequest();
		String strs=hr.sendPost("http://www.hoapi.com/index.php/Home/Api/check", "str="+str+"&token="+"7f1f8782c3985a6bb9824cb6ff70dd38");
		JSON.toJSON(strs);
		JSONObject parseObject = JSON.parseObject(strs);
		System.out.println(parseObject);
		return parseObject.getBooleanValue("status");
	}
	
	
	public static String LongLatExchange(String auntAddress){
		String []ps=auntAddress.split(",");
		double j=Double.parseDouble(ps[0]);
		double w=Double.parseDouble(ps[1]);
		int precision = 10; 
        GeoHash geoHash = GeoHash.withCharacterPrecision(w,j,precision);
        String auntAddress1=geoHash.toBase32();
        return auntAddress1;
	}
}
