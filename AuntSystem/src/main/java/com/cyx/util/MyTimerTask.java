package com.cyx.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.cyx.pojo.AssetOfHash;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.OrderInfo;

public class MyTimerTask extends TimerTask {

	public MyTimerTask() {
	}

	@Override
	public void run() {
		System.out.println("线程正在执行。。。");
		Iterator<Map.Entry<String, Date>> iterator = AssetOfHash.Map_auntDate.entrySet().iterator();
		while(iterator.hasNext()){
				 Entry<String, Date> next =iterator.next();
				 if(next==null){
					 break;
				 }
				 String key=next.getKey();
				 if(AssetOfHash.Map_auntList.get(key)==null||AssetOfHash.Map_OrderInfo.get(key)==null||AssetOfHash.Map_userResId.get(key)==null){
					 break;
				 }
				 long between=(new Date().getTime()-next.getValue().getTime())/1000;
				 if(between>300){
					 if(AssetOfHash.Map_auntList.get(key)!=null&&AssetOfHash.Map_auntList.get(key).size()>0)
						 AssetOfHash.Map_auntList.get(key).remove(0);
					 AssetOfHash.Map_auntDate.put(key, new Date());
					 List<AuntInfo> o=AssetOfHash.Map_auntList.get(key);
					 try{
						 if(o!=null&&o.size()>0&&o.get(0)!=null&&o.get(0).getResgiterId()!=null&&o.get(0).getResgiterId()!=""){
							 System.out.println("order post to "+o.get(0).getResgiterId()+"  "+o.get(0).getAuntId());
							 System.out.println(JpushClientUtil.sendToRegistrationId(o.get(0).getResgiterId(), "您有一张订单请注意接收", "您有一张订单请注意接收", "aaaaaa", AssetOfHash.Map_OrderInfo.get(key)));
						 }
					 }catch(Exception e){
						 e.printStackTrace();
					 }
					
				 }
		}
	}
}
