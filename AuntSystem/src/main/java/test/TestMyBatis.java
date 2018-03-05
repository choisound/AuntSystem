package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Timer;

import javax.annotation.Resource;
import javax.faces.event.SystemEvent;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.report.UsersResult.User;

import com.alibaba.fastjson.JSON;
import com.cyx.dao.UserOrderInfoMapper;
import com.cyx.pojo.AssetOfHash;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.AuntServiceInfo;
import com.cyx.pojo.BlackListContentInfo;
import com.cyx.pojo.BlackListInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.EvaluateInfo;
import com.cyx.pojo.FeedbackInfo;
import com.cyx.pojo.HolidayInfo;
import com.cyx.pojo.LocationInfo;
import com.cyx.pojo.MonthlyPay;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.ServiceInfo;
import com.cyx.pojo.UserInfo;
import com.cyx.pojo.UserOrderContentInfo;
import com.cyx.pojo.UserOrderInfo;
import com.cyx.service.IAdminService;
import com.cyx.service.IAuntOrderService;
import com.cyx.service.IAuntService;
import com.cyx.service.IAuntServiceService;
import com.cyx.service.IBlackListService;
import com.cyx.service.IComplaintService;
import com.cyx.service.IEvaluateService;
import com.cyx.service.IFeedbackService;
import com.cyx.service.IHolidayService;
import com.cyx.service.IMonthPayService;
import com.cyx.service.IOrderService;
import com.cyx.service.IServiceService;
import com.cyx.service.ISystemBlacklistService;
import com.cyx.service.IUserOrderService;
import com.cyx.service.IUserService;
import com.cyx.service.impl.AuntServiceServiceImpl;
import com.cyx.util.CheckJz;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.IdentifyingCode;
import com.cyx.util.JpushClientUtil;
import com.cyx.util.MD5;
import com.cyx.util.HttpRequest;
import com.cyx.util.MyTimerTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@RunWith(SpringJUnit4ClassRunner.class)		//��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = {"classpath:config/spring-mybatis.xml"})
public class TestMyBatis {
	@Value("${filePath}")
	private String s=null;
	@Resource
	IUserService userService;
	@Resource
	IAuntService auntService;
	@Resource
	IServiceService serviceService;
	@Resource
	IAuntServiceService auntServiceService;
	@Resource
	IHolidayService holidayService;
	@Resource
	IAuntOrderService auntOrderService;
	@Resource
	IEvaluateService evaluateService;
	@Resource
	IFeedbackService feedbackService;
	@Resource
	IUserOrderService userOrderService;
	@Resource
	IBlackListService blackListService;
	@Resource
	IComplaintService complaintService;
	@Resource
	IOrderService orderService;
	@Resource
	ISystemBlacklistService systemBlacklistService;
	@Resource
	IMonthPayService monthPayService;
	@Resource
	IAdminService adminService;
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	@Test
	public void test() {
//		List<String>lists=new ArrayList<String>();
//		lists.add("aunt_2017429234022739124acc99d739b5bf8070633667");
//		auntService.queryListByIds(lists);
////		System.out.println(adminService.selectByPhoneAndPassword("admin2", "123456"));
//		System.out.println(GenerateIdUtil.generateId("order"));
//		System.out.println(auntOrderService.selectOrderEachMonth(0, "aunt_20175216736417e340ba808660eafe6aecea2685","2017-05"));;
//		System.out.println(auntOrderService.selectOnlineMoneyEachMonth(0, "aunt_20175216736417e340ba808660eafe6aecea2685","2017-05"));;
//		System.out.println(auntOrderService.selectofflineMoneyEachMonth(0, "aunt_20175216736417e340ba808660eafe6aecea2685","2017-05"));;
//		Calendar c=Calendar.getInstance();
//		System.out.println(c.get(Calendar.YEAR));
//		System.out.println(Calendar.YEAR);
//		MonthlyPay monthlyPay = new MonthlyPay();
//		monthlyPay.setAuntId("aunt_2017429234022739124acc99d739b5bf8070633667");
//		monthlyPay.setMonthlypayMouth("2017-03");
//		monthPayService.insertSelective(monthlyPay);
//		System.out.println(monthPayService.selectmonthbyauntid("aunt_2017429234022739124acc99d739b5bf8070633667", "2017").toString());
//		OrderInfo order=new OrderInfo();
//		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd a");
//		System.out.println(df2.format(new Date()));
//		System.out.println(holidayService.selectAuntByHoliday("2017-05-02 上午","2017-05-02 下午").toString());
		
//		OrderInfo allOrder = orderService.selectByPrimaryKey("order_20174282132151405b42c5a01f4879372745da256");
//		JpushClientUtil.sendToRegistrationId("aunt_2017429234259639d4dad8ffb21ed4ba8620a7155", "aaaaa", "aaaaa", "aaaaa", allOrder);
//		JpushClientUtil.sendToAll("aaaaa", "a", "aaaaa", "aaaaa");
//		String auntId="aunt_201742821228134354a25a9f0ef1eb996c8813231";
//		int index=Integer.valueOf(auntId
//				.substring(auntId.length() - 2)) % 5;
//		CountSum list=auntOrderService.selectMoneyEachMonth(index,auntId );
//		System.out.println(list.toString());
//		UserInfo user=new UserInfo();
//		user.setUserPhoneno("15768650644");
//		user.setUserSex("男");
//		userService.updateByPhoneNoSelective(user);
//		orderService.deleteByPrimaryKey("order_2017423215742604946d6bedeeadae5f8b0fc4033");
//		String orderId="order_2017423215742604946d6bedeeadae5f8b0fc4033";
//		String index="0";
//		auntOrderService.deleteByIndexAuntId(index,orderId);
//		userOrderService.deleteByPrimaryKey(index,orderId);
//		HolidayInfo holiday=new HolidayInfo(GenerateIdUtil.generateId("holiday"),"aunt_201742221278497e43babaee05c7a00b620e9705","2017-04-25上午","2017-04-25下午");
//		holidayService.insertSelective(holiday);
//		System.out.println(holidayService.selectByAuntId("aunt_201742221278497e43babaee05c7a00b620e9705", "2017-04"));
//		String userId="user_20174101434694370f65e28c41243ffb2df758a6ess5300";
//		String orderId="order_2017423215742604946d6bedeeadae5f8b0fc4033";
//		System.out.println(complaintService.selectByOrderIdAndUserId(orderId, userId));
//		OrderInfo allOrder = orderService.selectByPrimaryKey("order_2017423215742604946d6bedeeadae5f8b0fc4033");
//		System.out.println(allOrder);
//		EvaluateInfo einfo = evaluateService.selectByOrderid(allOrder.getOrderId());
//		System.out.println(einfo.toString());
//		FeedbackInfo feed=feedbackService.selectByEvaluateId(einfo.getEvaluateId());
//		System.out.println(feed.toString());
//		List<AuntInfo> al=AssetOfHash.Map_auntList.get(allOrder.getOrderId());
//		System.out.println(al);
//		OrderInfo allOrder = orderService.selectByPrimaryKey("order_2017423215742604946d6bedeeadae5f8b0fc4033");
//		System.out.println(allOrder);
//		System.out.println(complaintService.selectByPrimaryKey("1").toString());;
//		double d=Math.random();
//		d=d*100;
//		d=d%20;
//		d*=100;
//		d=(int)d;
//		d/=100;
//		String orderId="order_2017423215742604946d6bedeeadae5f8b0fc4033";
//		String auntId="aunt_201742221278497e43babaee05c7a00b620e9705";
//		String userId="user_201741217029245af207731e7244ffcaa8a3b9f13e1c900";
//		OrderInfo orderinfo = new OrderInfo();
//		orderinfo.setEndTime(new Date());
//		orderinfo.setOrderId(orderId);
//		orderinfo.setOrderState(3);
//		System.out.println(orderinfo);
//		int index = Integer.valueOf(auntId.substring(auntId
//				.length() - 2)) % 5;
//		auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
//		orderService.updateByPrimaryKeySelective(orderinfo);
//		String userPhoneno =orderinfo.getUserId ();
//		int index1 = Integer.valueOf(userId.substring(userId
//				.length() - 2)) % 5;
//		userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
////		OrderInfo orderinfo=new OrderInfo();
//		orderinfo.setAuntId("aunt_20174222053197287743cf9b7fb843b1550d9e7392");
//		System.out.println(auntOrderService.selectByAuntId(2, "aunt_20174222053197287743cf9b7fb843b1550d9e7392"));;
//		AuntInfo userInfo=auntService.selectByPhoneno("15768611567");
//		UserInfo user=userService.selectByPhoneno("15768611567");
//		System.out.println(userInfo);
//		OrderInfo orderinfo=new OrderInfo();
//		orderinfo.setOrderId("order_201745164756134932aa5f82af4bcabca49bef9e648dc1");
//		UserOrderInfo order = userOrderService.selectByOrderId(0, "order_2017423103256977a4d07a050f683cb1aa4fb8359");
//		order.setAuntId("aunt_201742220426672d0466e92ca13219811441c9453");
//		System.out.println(order);
//		System.out.println(JpushClientUtil.sendToRegistrationId_user("1507bfd3f7f4baf22d8", "九千歲", "九千歲", "九千歲", order));;
//		System.out.println(JpushClientUtil.sendToRegistrationId_user(user.getResgiterId(), "您的订单已被接收", "您的订单已被接收", "您的订单已被接收", "您的订单已被接收"));;
//		System.out.println(userOrderService.selectByUserId("0", "user_2017420164829209cf49ea940220944819dcd10"));
//		OrderInfo orderinfo=new OrderInfo();
//		orderinfo.setOrderId("order_201745164756134932aa5f82af4bcabca49bef9e648dc1");
//		orderinfo.setOrderState(4);
//		userOrderService.selectByOrderId("0", "order_201745164756134932aa5f82af4bcabca49bef9e648dc1");
//		orderService.updateByPrimaryKeySelective(orderinfo);
//		OrderInfo orderinfo=new OrderInfo();
//		orderinfo.setOrderId("aaaaa30aaaa");
//		orderinfo.setOrderZwaddress("惠州学院");
//		orderinfo.setOrderRes(new Date());
//		orderinfo.setOrderRoughtime(new Date());
//		orderinfo.setUserId("user_20174201856362653347a2a6a9926cbfa395b30");
//		System.out.println(userOrderService.insertSelective(0, orderinfo));;
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d = new Date(0);
//		try {
//			d = df.parse("1990-10-09 8:00:09");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(df.parse(df.format(d.getTime()-1000*60*60*2)));;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<String>lists=new ArrayList<String>();
//		lists.add("aunt_20174201836573208b49d0b174c098cbe7f4b30");
//		lists.add("aunt_2017420174524771b64558b73340ae304400190");
//		auntService.queryListByIds(lists);
//		System.out.println(aunts.toString());
//		String serviceIds[]={"0","1","2","3"};
//		List<AuntServiceInfo> lists=new ArrayList<AuntServiceInfo>();
//		AuntServiceInfo auntServiceinfo;
//		for(int i=0;i<serviceIds.length;i++){
//			auntServiceinfo=new AuntServiceInfo();
//			auntServiceinfo.setAuntId("aunt_20174222053197287743cf9b7fb843b1550d9e7392");
//			auntServiceinfo.setServiceId(serviceIds[i]);
//			auntServiceinfo.setAuntserviceNo(GenerateIdUtil.generateId("auntservice"));
//			lists.add(auntServiceinfo);
//		}
//		String res="";
//		int rc=1;
//			rc=auntServiceService.insertList(lists);
//		System.out.println(evalueteService.selectAuntService("0", "aunt_20174201836573208b49d0b174c098cbe7f4b30"));
//		for(int i=0;i<10;i++)
//			System.out.println(GenerateIdUtil.generateId("user"));
//		JpushClientUtil.sendToRegistrationId_user("160a3797c83756d0479", "您的订单无阿姨接单", "您的订单无阿姨接单",null,null);
//		UserInfo User=new UserInfo();
//		User.setResgiterId("160a3797c83756d0479");
//		OrderInfo o=orderService.selectByPrimaryKey("order_201745164756134932aa5f82af4bcabca49bef9e648dc1");
//		int i=JpushClientUtil.sendToRegistrationId_user(User.getResgiterId(), "您的订单无阿姨接单", "您的订单无阿姨接单", "您的订单无阿姨接单","您的订单无阿姨接单" );
//		System.out.println(i);
//		AuntInfo auntinfo=new AuntInfo();
//		auntinfo.setAuntPhoneno("15815425421");
//		auntinfo.setAuntState(1);
//		auntService.updateByPhoneNoSelective(auntinfo);
//		System.out.println(userService.selectAllList(0, 20));
//		AuntInfo aunt=new AuntInfo();
//		System.out.println(systemBlacklistService.selectAll(0, 20));
//		aunt.setAuntId("aunt_2017451112226835c380f29e12487e8bbf119047a349aw");
//		aunt.setAuntState(1);
//		System.out.println(blackListService.selectByUserIdLimit("user_2017442145484732e68751e099c400d83e5c105089e5872", 0, 20));
//		System.out.println(auntService.updateByPrimaryKeySelective(aunt));
//		System.out.println(complaintService.selectAll(0, 20));
//		System.out.println(complaintService.selectAllCount());
//		System.out.println(auntService.selectAllListCount());
//		System.out.println(auntService.selectAllList(0, 20));
//		System.out.println(userService.selectAllUserCount());
//		System.out.println(userService.selectAllList(0, 20));
////		0
////			 TagAliasResult result = jpushClient.getDeviceTagAlias("user_20174101434694370f65e28c41243ffb2df758a6e7e53c4");
////			 System.out.println(result.alias+ "  " + result.tags);
////		} catch (APIConnectionException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (APIRequestException e) {
//
////			// TODO Auto-generated catch block
////					e.printStackTrace();
////		}
//		List<BlackListContentInfo> bla = blackListService.selectByUserId("user_2017442145484732e68751e099c400d83e5c105089e5872");
//		for(int i=0;i<bla.size();i++){
//			System.out.println(bla.get(i).toString());
//		}
////			UserInfo aunt=userService.selectByPhonenoAndPassword("15768650644", "choisound");
////					System.out.println(aunt.toString());
////					System.out.println(getEncoding(aunt.toString()));
//			List<UserOrderContentInfo> l=userOrderService.selectByUserId("0", "user_2017442145484732e68751e099c400d83e5c105089e5872");
//			for(int i=0;i<l.size();i++){
//				System.out.println(l.get(i).toString());
//			}
//			JPushClient jpushClient = new JPushClient("5f8c4616ba3848186ac51afe", "79315a6b4d8b7358243a0ecf", null, ClientConfig.getInstance());
//		    // For push, all you need do is to build PushPayload object.
//			String msg="�������Ѿ�������Ķ���";
//			try {
////				jpushClient.bindMobile(, "15768650644");
//				TagAliasResult deviceTagAlias = jpushClient.getDeviceTagAlias("aunt_2017451112226835c380f29e12487e8bbf119047a349a5");
//				System.out.println(deviceTagAlias);
//			} catch (APIConnectionException | APIRequestException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    PushPayload payload = JGTuiSong.buildPushObject_all_all_alert(msg);
//		    try {
//		        PushResult result = jpushClient.sendPush(payload);
//		    } catch (APIConnectionException e) {
//		        // Connection error, should retry later
//		    } catch (APIRequestException e) {
//		        // Should review the error, and fix the request
//		    }
//		UserOrderInfo userOrderInfo=new UserOrderInfo();
//		userOrderInfo.setOrderId(GenerateIdUtil.generateId("order"));
//		userOrderInfo.setOrderAddress("ws175ghch3");
//		userOrderInfo.setOrderRes(new Date());
//		userOrderInfo.setOrderRoughtime(new Date());
//		userOrderInfo.setUserId("user_2017461652243768c24ca5fa58e438c96b5e4bc638104cf");
//		int index=(43%5);
//		System.out.println(index);
//		List<AuntInfo> als=userOrderService.selectByOrder(index, userOrderInfo);
//		for(int i=0;i<als.size();i++){
//			System.out.println(als.get(i));
//		}
//		Timer timer = new Timer();
//		MyTimerTask mtt=new MyTimerTask(als);
//        timer.schedule(mtt, 1000, 15*60*1000); 
//        System.out.println("�ŵ���ʼ");
//        mtt.setflag(false);
//		for(int i=0;i<als.size();i++){
//			System.out.println(als.get(i).toString());
//		}
//		System.out.println("\n\n");
//		String address=userOrderInfo.getOrderAddress();
//		for(int i=0;i<als.size();i++){
//			for(int j=i+1;j<als.size();j++){
//				if(strsub(als.get(i).getAuntAddress(),address)>strsub(als.get(j).getAuntAddress(),address)){
//					AuntInfo a=als.get(i);
//					als.set(i,als.get(j));
//					als.set(j,a);
//				}
//			}
//		}
//		for(int i=0;i<als.size();i++){
//			System.out.println(als.get(i).toString());
//		}
//		OrderInfo aoi=new OrderInfo();
//		aoi.setOrderId(GenerateIdUtil.generateId("order"));
//		aoi.setUserId("user_2017442145484732e68751e099c400d83e5c105089e5872");
//		aoi.setAuntId("aaaaa");
//		aoi.setOrderAddress("kdifn");
//		aoi.setOrderState(0);
//		aoi.setOrderRes(new Date());
//		aoi.setServiceId("service_2017451531292321b7b852bf543488f8d712252fe3683f6");
//		auntOrderService.insert(0, aoi);
//		String serviceId="service_2017451531292321b7b852bf543488f8d712252fe3683f6,service_201745153552818f59470c4acd482da1de82cf4f9d83dd";
//		String[] serviceIds=serviceId.split(",");
//		AuntServiceInfo auntService;
//		System.out.println(serviceIds);
//		List<AuntServiceInfo> lists=new ArrayList<AuntServiceInfo>();
//		for(int i=0;i<serviceIds.length;i++){
//			auntService=new AuntServiceInfo();
//			auntService.setAuntId("aaaaa");
//			auntService.setServiceId(serviceIds[i]);
//			auntService.setAuntserviceNo(GenerateIdUtil.generateId("auntservice"));
//			lists.add(auntService);
//		}
		
//		System.out.println(orderService.selectAllOrderInfo());
//		System.out.println("aaaa");
		//惠州市教育局  ws17765qm4
				//惠州学院公交车站ws175fuzhg
				//万林湖体育馆   ws174zrr10
				//下埔并将公园ws175weemf
//		GeoHash g2=GeoHash.fromGeohashString("ws17765qm4");
//		GeoHash g1=GeoHash.fromGeohashString("ws175fuzhg");
//		String a=g2.toBinaryString();
//		String b=g1.toBinaryString();
//		System.out.println(a+"  "+b+"  "+Long.valueOf(a,2).toString() +"  "+Long.valueOf(b,2).toString()+"  "+(Long.valueOf(a,2)-Long.valueOf(b,2)));
//		String auntAddress="114.4249376663,23.0430049991";
//		String []ps=auntAddress.split(",");
//		double j=Double.parseDouble(ps[0]);
//		double w=Double.parseDouble(ps[1]);
		HashMap<String ,String > map=new HashMap();
		map.put("海门","121.15,31.89");
		map.put("鄂尔多斯","109.781327,39.608266");
		map.put("招远","120.38,37.35");
		map.put("舟山","122.207216,29.985295");
		map.put("齐齐哈尔","123.97,47.33");
		map.put("盐城","120.13,33.38");
		map.put("赤峰","118.87,42.28");
		map.put("青岛","120.33,36.07");
		map.put("乳山","121.52,36.89");
		map.put("金昌","102.188043,38.520089");
		map.put("泉州","118.58,24.93");
		map.put("莱西","120.53,36.86");
		map.put("日照","119.46,35.42");
		map.put("胶南","119.97,35.88");
		map.put("南通","121.05,32.08");
		map.put("拉萨","91.11,29.97");
		map.put("云浮","112.02,22.93");
		map.put("梅州","116.1,24.55");
		map.put("文登","122.05,37.2");
		map.put("上海","121.48,31.22");
		map.put("攀枝花","101.718637,26.582347");
		map.put("威海","122.1,37.5");
		map.put("承德","117.93,40.97");
		map.put("厦门","118.1,24.46");
		map.put("汕尾","115.375279,22.786211");
		map.put("潮州","116.63,23.68");
		map.put("丹东","124.37,40.13");
		map.put("太仓","121.1,31.45");
		map.put("曲靖","103.79,25.51");
		map.put("烟台","121.39,37.52");
		map.put("福州","119.3,26.08");
		map.put("瓦房店","121.979603,39.627114");
		map.put("即墨","120.45,36.38");
		map.put("抚顺","123.97,41.97");
		map.put("玉溪","102.52,24.35");
		map.put("张家口","114.87,40.82");
		map.put("阳泉","113.57,37.85");
		map.put("莱州","119.942327,37.177017");
		map.put("湖州","120.1,30.86");
		map.put("汕头","116.69,23.39");
		map.put("昆山","120.95,31.39");
		map.put("宁波","121.56,29.86");
		map.put("湛江","110.359377,21.270708");
		map.put("揭阳","116.35,23.55");
		map.put("荣成","122.41,37.16");
		map.put("连云港","119.16,34.59");
		map.put("葫芦岛","120.836932,40.711052");
		map.put("常熟","120.74,31.64");
		map.put("东莞","113.75,23.04");
		map.put("河源","114.68,23.73");
		map.put("淮安","119.15,33.5");
		map.put("泰州","119.9,32.49");
		map.put("南宁","108.33,22.84");
		map.put("营口","122.18,40.65");
		map.put("惠州","114.4,23.09");
		map.put("江阴","120.26,31.91");
		map.put("蓬莱","120.75,37.8");
		map.put("韶关","113.62,24.84");
		map.put("嘉峪关","98.289152,39.77313");
		map.put("广州","113.23,23.16");
		map.put("延安","109.47,36.6");
		map.put("太原","112.53,37.87");
		map.put("清远","113.01,23.7");
		map.put("中山","113.38,22.52");
		map.put("昆明","102.73,25.04");
		map.put("寿光","118.73,36.86");
		map.put("盘锦","122.070714,41.119997");
		map.put("长治","113.08,36.18");
		map.put("深圳","114.07,22.62");
		map.put("珠海","113.52,22.3");
		map.put("宿迁","118.3,33.96");
		map.put("咸阳","108.72,34.36");
		map.put("铜川","109.11,35.09");
		map.put("平度","119.97,36.77");
		map.put("佛山","113.11,23.05");
		map.put("海口","110.35,20.02");
		map.put("江门","113.06,22.61");
		map.put("章丘","117.53,36.72");
		map.put("肇庆","112.44,23.05");
		map.put("大连","121.62,38.92");
		map.put("临汾","111.5,36.08");
		map.put("吴江","120.63,31.16");
		map.put("石嘴山","106.39,39.04");
		map.put("沈阳","123.38,41.8");
		map.put("苏州","120.62,31.32");
		map.put("茂名","110.88,21.68");
		map.put("嘉兴","120.76,30.77");
		map.put("长春","125.35,43.88");
		map.put("胶州","120.03336,36.264622");
		map.put("银川","106.27,38.47");
		map.put("张家港","120.555821,31.875428");
		map.put("三门峡","111.19,34.76");
		map.put("锦州","121.15,41.13");
		map.put("南昌","115.89,28.68");
		map.put("柳州","109.4,24.33");
		map.put("三亚","109.511909,18.252847");
		map.put("自贡","104.778442,29.33903");
		map.put("吉林","126.57,43.87");
		map.put("阳江","111.95,21.85");
		map.put("泸州","105.39,28.91");
		map.put("西宁","101.74,36.56");
		map.put("宜宾","104.56,29.77");
		map.put("呼和浩特","111.65,40.82");
		map.put("成都","104.06,30.67");
		map.put("大同","113.3,40.12");
		map.put("镇江","119.44,32.2");
		map.put("桂林","110.28,25.29");
		map.put("张家界","110.479191,29.117096");
		map.put("宜兴","119.82,31.36");
		map.put("北海","109.12,21.49");
		map.put("西安","108.95,34.27");
		map.put("金坛","119.56,31.74");
		map.put("东营","118.49,37.46");
		map.put("牡丹江","129.58,44.6");
		map.put("遵义","106.9,27.7");
		map.put("绍兴","120.58,30.01");
		map.put("扬州","119.42,32.39");
		map.put("常州","119.95,31.79");
		map.put("潍坊","119.1,36.62");
		map.put("重庆","106.54,29.59");
		map.put("台州","121.420757,28.656386");
		map.put("南京","118.78,32.04");
		map.put("滨州","118.03,37.36");
		map.put("贵阳","106.71,26.57");
		map.put("无锡","120.29,31.59");
		map.put("本溪","123.73,41.3");
		map.put("克拉玛依","84.77,45.59");
		map.put("渭南","109.5,34.52");
		map.put("马鞍山","118.48,31.56");
		map.put("宝鸡","107.15,34.38");
		map.put("焦作","113.21,35.24");
		map.put("句容","119.16,31.95");
		map.put("北京","116.46,39.92");
		map.put("徐州","117.2,34.26");
		map.put("衡水","115.72,37.72");
		map.put("包头","110,40.58");
		map.put("绵阳","104.73,31.48");
		map.put("乌鲁木齐","87.68,43.77");
		map.put("枣庄","117.57,34.86");
		map.put("杭州","120.19,30.26");
		map.put("淄博","118.05,36.78");
		map.put("鞍山","122.85,41.12");
		map.put("溧阳","119.48,31.43");
		map.put("库尔勒","86.06,41.68");
		map.put("安阳","114.35,36.1");
		map.put("开封","114.35,34.79");
		map.put("济南","117,36.65");
		map.put("德阳","104.37,31.13");
		map.put("温州","120.65,28.01");
		map.put("九江","115.97,29.71");
		map.put("邯郸","114.47,36.6");
		map.put("临安","119.72,30.23");
		map.put("兰州","103.73,36.03");
		map.put("沧州","116.83,38.33");
		map.put("临沂","118.35,35.05");
		map.put("南充","106.110698,30.837793");
		map.put("天津","117.2,39.13");
		map.put("富阳","119.95,30.07");
		map.put("泰安","117.13,36.18");
		map.put("诸暨","120.23,29.71");
		map.put("郑州","113.65,34.76");
		map.put("哈尔滨","126.63,45.75");
		map.put("聊城","115.97,36.45");
		map.put("芜湖","118.38,31.33");
		map.put("唐山","118.02,39.63");
		map.put("平顶山","113.29,33.75");
		map.put("邢台","114.48,37.05");
		map.put("德州","116.29,37.45");
		map.put("济宁","116.59,35.38");
		map.put("荆州","112.239741,30.335165");
		map.put("宜昌","111.3,30.7");
		map.put("义乌","120.06,29.32");
		map.put("丽水","119.92,28.45");
		map.put("洛阳","112.44,34.7");
		map.put("秦皇岛","119.57,39.95");
		map.put("株洲","113.16,27.83");
		map.put("石家庄","114.48,38.03");
		map.put("莱芜","117.67,36.19");
		map.put("常德","111.69,29.05");
		map.put("保定","115.48,38.85");
		map.put("湘潭","112.91,27.87");
		map.put("金华","119.64,29.12");
		map.put("岳阳","113.09,29.37");
		map.put("长沙","113,28.21");
		map.put("衢州","118.88,28.97");
		map.put("廊坊","116.7,39.53");
		map.put("菏泽","115.480656,35.23375");
		map.put("合肥","117.27,31.86");
		map.put("武汉","114.31,30.52");
		map.put("大庆","125.03,46.58");
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		int precision = 10; 
		int i=1;
		while(iterator.hasNext()){
			Entry<String, String> next = iterator.next();
			String str=next.getValue();
//			System.out.println(str);
			String[] split = str.split(",");
//			System.out.println(split[1]+";"+split[0]);
		      GeoHash geoHash = GeoHash.withCharacterPrecision(Double.parseDouble(split[1]),Double.parseDouble(split[0]),precision);
		      String  temp=geoHash.toBase32();
		      System.out.println("when n1to10="+(i++)+" then set citynum='"+temp+"',city='"+next.getKey()+"市***';");
//		      WGS84Point point = GeoHash.fromGeohashString(temp).getPoint();
//		      System.out.println(temp);
//		      System.out.println(point);
		}
//		int precision = 10; 
//        GeoHash geoHash = GeoHash.withCharacterPrecision(w,j,precision);
//        String  temp=geoHash.toBase32();
//        WGS84Point point = GeoHash.fromGeohashString("ws16ukcumg").getPoint();
//        System.out.println(point);
//        System.out.println(temp);
//        System.out.println(Distance("ws175wrsjq", "ws175f0x4v"));
//        System.out.println(Distance("ws175f0x4v","ws16ur2wk8"));
//        System.out.println(Distance("ws177dgntv","ws16ur2wk8"));
//        System.out.println(Distance("ws175fceq7","ws16ur2wk8"));
//        System.out.println(Distance("ws172xz8p5","ws16ur2wk8"));
//        System.out.println(Distance("ws16ukcumg","ws175wrsjq"));
//      
//      
        //惠州市农村商业银行(金山湖分理处)  ws175vt4zx
//        宏益商业广场   ws175ysu36
//        ws175fcenr
//        System.out.println(strsub("ws175f0x4v","ws1778n1x2"));
//		System.out.println(strsub("ws175f29nt","ws1778n1x2"));
//		System.out.println(strsub("ws175fceq7","ws1778n1x2"));
//		System.out.println(strsub("ws172xz8p5","ws1778n1x2"));
//		System.out.println(strsub("ws177dgntv","ws175fceq7"));
//		auntServiceService.insertList(lists);
//		IdentifyingCode identifyingCode=new IdentifyingCode();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//		String str=identifyingCode.GenerateCode("15768650644", String.valueOf((int)(Math.random()*1000000)), sdf.format(new Date()));
//		String str="{\"respCode\":\"00000\",\"failCount\":\"0\",\"failList\","");\"smsId\":\"60a12b3e6d9047c9b4d61af4660a04d0\",\"createDate\":\"2017-04-01 16:13:08\"}";
//		System.out.println(JSON.parseObject(str).getString("respCode"));
//		System.out.println(auntServiceService.selectByAuntId("aunt_2017429234022739124acc99d739b5bf8070633667"));
//		System.out.println( userOrderService.selectByOrderId(4, "order_201781720160237359"));
////		System.out.println(SMSVerificationCode.tosendSMSCode("http://baidu.com", "", "POST"));
//		SMSVerificationCode sms=new SMSVerificationCode();
//		String params="accountSid=47f38483fa5b4a408e988213e3987374"
//				+ "&smsContent=���������ˡ������֤����345678��10����������Ч��"
//				+ "&to=15768650644"
//				+ "&timestamp=20170331200012"
//				+ "&sig="+MD5.getMd5("47f38483fa5b4a408e988213e3987374bae044000c734d56b70880b9b16411fa20170331200012")
//				+ "&respDataType=JSON";
//		String url="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
//		try {
//			System.out.println(sms.request(url, params, "POST"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		int count = orderService.selectAllOrderCount();
//		System.out.println(count);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
//		String auntId="aaaaa";
//		String[] holiday_starttime={"2017/03/31 21:04:33","2017/03/31 23:04:33","2017/04/01 4:04:33"};
//		String[] holiday_endtime={"2017/03/31 21:05:33","2017/03/31 23:55:33","2017/04/01 4:05:33"};
//		HolidayInfo holiday=null;
//		List<HolidayInfo> holidays=new ArrayList<HolidayInfo>();
//		try {
//			for(int i=0;i<holiday_starttime.length;i++){
//				holiday=new HolidayInfo(GenerateIdUtil.generateId("holiday"), auntId, sdf.parse(holiday_starttime[i]), sdf.parse(holiday_endtime[i]));
//				holidays.add(holiday);
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		holidayService.insertHoliDays(holidays);
	}
	public long strsub(String str1, String str2) {
		long ans=1000000;
		try{
			GeoHash g2=GeoHash.fromGeohashString(str1);
			GeoHash g1=GeoHash.fromGeohashString(str2);
			String a=g2.toBinaryString();
			String b=g1.toBinaryString();
//			System.out.println(a+"  "+b+"  "+Long.valueOf(a,2).toString() +"  "+Long.valueOf(b,2).toString()+"  "+(Long.valueOf(a,2)-Long.valueOf(b,2)));
			ans=Long.valueOf(a,2)-Long.valueOf(b,2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Math.abs(ans);
	}
	  private static final double EARTH_RADIUS = 6378.137;//地球半径,单位千米
	    private static double rad(double d)
	    {
	        return d * Math.PI / 180.0;
	    }
	    
	    public static double Distance(String str1,String str2) {
	    	  GeoHash g=GeoHash.fromGeohashString(str1);
	          WGS84Point point = g.getPoint();
	          System.out.println(point);
	          double lat1=point.getLatitude();
	          double long1=point.getLongitude();
	          g=GeoHash.fromGeohashString(str2);
	          point = g.getPoint();
	          double lat2=point.getLatitude();
	          double long2=point.getLongitude();
	        double a, b, R;  
	        R = 6378137; // 地球半径  
	        lat1 = lat1 * Math.PI / 180.0;  
	        lat2 = lat2 * Math.PI / 180.0;  
	        a = lat1 - lat2;  
	        b = (long1 - long2) * Math.PI / 180.0;  
	        double d;  
	        double sa2, sb2;  
	        sa2 = Math.sin(a / 2.0);  
	        sb2 = Math.sin(b / 2.0);  
	        d = 2  
	                * R  
	                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                        * Math.cos(lat2) * sb2 * sb2));  
	        return d;  
	    }  
}
