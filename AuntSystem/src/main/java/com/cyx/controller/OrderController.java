package com.cyx.controller;

import java.nio.channels.SeekableByteChannel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

import com.cyx.pojo.AssetOfHash;
import com.cyx.pojo.AuntInfo;
import com.cyx.pojo.AuntOrderContentInfo;
import com.cyx.pojo.AuntOrderInfo;
import com.cyx.pojo.ComplaintInfo;
import com.cyx.pojo.CountSum;
import com.cyx.pojo.DisCount;
import com.cyx.pojo.EvaluateInfo;
import com.cyx.pojo.FeedbackInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.pojo.OrderInfo_YMS;
import com.cyx.pojo.ReturnJson;
import com.cyx.pojo.ReturnJson1;
import com.cyx.pojo.ReturnJson2;
import com.cyx.pojo.UserInfo;
import com.cyx.pojo.UserOrderContentInfo;
import com.cyx.pojo.UserOrderInfo;
import com.cyx.service.IAuntOrderService;
import com.cyx.service.IAuntService;
import com.cyx.service.IComplaintService;
import com.cyx.service.IDisCountService;
import com.cyx.service.IEvaluateService;
import com.cyx.service.IFeedbackService;
import com.cyx.service.IHolidayService;
import com.cyx.service.IMonthPayService;
import com.cyx.service.IOrderService;
import com.cyx.service.IUserOrderService;
import com.cyx.service.IUserService;
import com.cyx.util.CheckJz;
import com.cyx.util.GenerateIdUtil;
import com.cyx.util.JpushClientUtil;
import com.cyx.util.MyTimerTask;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	IUserOrderService userOrderService;
	@Resource
	IMonthPayService monthPayService;
	@Resource
	IDisCountService discountService;
	@Resource
	IOrderService orderService;
	@Resource
	IUserService userService;
	@Resource
	IAuntOrderService auntOrderService;
	@Resource
	IAuntService auntService;
	@Resource
	IEvaluateService evaluateService;
	@Resource
	IHolidayService holidayService;
	@Resource
	IFeedbackService feedbackService;
	@Resource
	IComplaintService complaintService;

	/**
	 * 派单
	 * 
	 * @param request
	 * @param model
	 * @param e1
	 * @return
	 */
	@RequestMapping("/placeorder")
	public @ResponseBody Object placeOrder(HttpServletRequest request,
			Model model, Exception e1) {
//		e1.printStackTrace();
		// 拿到订单所有数据
		String userId = request.getParameter("userId");
		String serviceId = request.getParameter("serviceId");
		String orderAddress = request.getParameter("orderAddress");
		String orderRes = request.getParameter("orderRes");
		String userResiger = request.getParameter("userResgiterId");
		String userResiger1 = userResiger;
		String orderDesc = request.getParameter("orderDesc");
		String orderZwaddress = request.getParameter("orderZwaddress");
		String orderRoughtime = request.getParameter("orderRoughtime");
		System.out.println(orderAddress);
		// 数据验证
		if (userId == null || serviceId == null || orderAddress == null
				|| orderRes == null || orderRoughtime == null || userId == ""
				|| serviceId == "" || orderAddress == "" || orderRes == ""
				|| orderRoughtime == "") {
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("订单信息不完整");
			return rj;
		}
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderZwaddress(orderZwaddress);
		orderInfo.setUserId(userId);
		try {
			orderAddress = CheckJz.LongLatExchange(orderAddress);
		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("地址出错，导致异常，不可提交订单");
			return rj;
		}
		if (orderAddress.equalsIgnoreCase("s000000000")) {
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("地址出错，导致异常，不可提交订单");
			return rj;
		}
		orderInfo.setOrderAddress(orderAddress);
		orderInfo.setServiceId(serviceId);
		String orderId = GenerateIdUtil.generateId("order");
		orderInfo.setOrderId(orderId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = sdf.parse(orderRes);
			orderInfo.setOrderRes(d);
			d = sdf.parse(orderRoughtime);
			orderInfo.setOrderRoughtime(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("有的字段不匹配，导致异常，不可提交订单");
			return rj;
		}
		orderInfo.setOrderDesc(orderDesc);
		orderInfo.setOrderState(0);
		orderService.insertSelective(orderInfo);
		int index = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		userOrderService.insertSelective(index, orderInfo);
		Date d = orderInfo.getOrderRes();
		try {
			d = sdf.parse(orderRes);
			orderInfo
					.setOrderRes(sdf.parse(sdf.format(d.getTime() - 1000 * 60 * 60)));
			d = sdf.parse(orderRoughtime);
			orderInfo
					.setOrderRoughtime(sdf.parse(sdf.format(d.getTime() + 1000 * 60 * 60)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("有的字段不匹配，导致异常，不可提交订单");
			return rj;
		}
		
		//算法开始
		Map<String, ArrayList<OrderInfo>> auntMap = new HashMap<String, ArrayList<OrderInfo>>();
		// 通过地址找到当天可接单阿姨
		List<OrderInfo> list = new ArrayList<OrderInfo>(); 
		list=orderService.selectAuntByAddress(
				orderInfo.getOrderAddress(),
				sdf.format(orderInfo.getOrderRes()));
		for (int i = 0; i < list.size(); i++) {
			OrderInfo orderI = list.get(i);
			System.out.println("订单："+orderI);
			ArrayList<OrderInfo> o = auntMap.get(orderI.getAuntId());
			if (orderI.getAuntId() == null) {
				continue;
			}
			if(o==null){
				System.out.println("o==null");
			}else{
				System.out.println(o);
			}
			// 分别添加到map<阿姨id，阿姨当天已接订单List>
			if (o == null) {
				o = new ArrayList<OrderInfo>();
				o.add(orderI);
				auntMap.put(orderI.getAuntId(), o);
			} else {
				auntMap.get(orderI.getAuntId()).add(orderI);
			}
		}
		System.out.println("------------------------");
		// 通过阿姨列表找到可以接单
		List<AuntInfo> strlist = new ArrayList<AuntInfo>();
		strlist=auntService.selectByAddress(orderInfo
				.getOrderAddress());
		for (int i = 0; i < strlist.size(); i++) {
			//将可接单阿姨放在map中
			List<OrderInfo>ls=auntMap.get(strlist.get(i).getAuntId());
			if(ls==null&&strsub(orderInfo.getOrderAddress(),strlist.get(i).getAuntAddress())<5000.0){
				System.out.println("帅选了"+strlist.get(i).getAuntId());
				auntMap.put(strlist.get(i).getAuntId(), new ArrayList());
			}
		}
		List<String> auntIdlist = new ArrayList<String>();
		// 筛选请假阿姨
		try {
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd a");
			String orderres = df2.format(orderInfo.getOrderRes());
			orderres = orderres.replace("AM", "上午");
			String orderrouth = df2.format(orderInfo.getOrderRoughtime());
			orderrouth = orderrouth.replace("PM", "下午");
			System.out.println("请假阿姨筛选");
			List<String> holidayAuntId = holidayService.selectAuntByHoliday(
					orderres, orderrouth);
			System.out.println(holidayAuntId.toString());
			for (int i = 0; i < holidayAuntId.size(); i++) {
				System.out.println("移除" + holidayAuntId.get(i));
				auntMap.remove(holidayAuntId.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//排除时间冲突排除
		try {
			Iterator<Entry<String, ArrayList<OrderInfo>>> iterator = auntMap
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, ArrayList<OrderInfo>> entry = iterator.next();
				System.out.println(entry);
				ArrayList<OrderInfo> orderList = (ArrayList<OrderInfo>) entry
						.getValue();
				System.out.println(orderList);
				int length = orderList.size();
				if (length == 0) {
					continue;
				}
				for (int i = 0; i < length; i++) {
					OrderInfo order_info = (OrderInfo) list.get(i);
					long auntOrderResTime = order_info.getOrderRes().getTime();
					long newOrderResTime = orderInfo.getOrderRes().getTime();
					long auntOrderRouthTime = order_info.getOrderRoughtime()
							.getTime();
					String address=order_info.getOrderAddress();
					String newaddress=orderInfo.getOrderAddress();
					long newOrderRouthTime = orderInfo.getOrderRoughtime()
							.getTime();
					if(strsub(address,newaddress)>5000.0)
					{
						System.out.println("帅选了"+iterator.next().getValue());
						iterator.remove();
						break;
					}
					// 筛选出有订单时间不符合与原来
					if ((auntOrderResTime <= newOrderResTime && auntOrderRouthTime >= newOrderResTime)
							|| (auntOrderResTime <= newOrderRouthTime && auntOrderRouthTime >= newOrderRouthTime)
							|| (auntOrderResTime >= newOrderResTime && auntOrderRouthTime <= newOrderRouthTime)) {
						System.out.println("筛选时间冲突");					
						iterator.remove();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------");
		for (Entry<String, ArrayList<OrderInfo>> entry : auntMap.entrySet()) {
			if(entry.getKey()!=null){
				System.out.println(entry.getKey());
				auntIdlist.add(entry.getKey());
			}
		}
		List<AuntInfo> als = new ArrayList<AuntInfo>();
		try{
			if(auntIdlist.size()>0)
				als=auntService.queryListByIds(auntIdlist);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(als);
		try {
			for (int i = 0; i < als.size(); i++) {
				ArrayList<OrderInfo> ai = auntMap.get(als.get(i).getAuntId());
				if (ai != null && ai.size() > 0
						&& ai.get(0).getOrderAddress() != ""
						&& ai.get(0).getOrderAddress() != null) {
					als.get(i).setAuntAddress(ai.get(0).getOrderAddress());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("冒泡排序开始_______________________");
		try {
			for (int i = 0; i < als.size(); i++) {
				for (int js = i + 1; js < als.size(); js++) {
					if (strsub(als.get(i).getAuntAddress(),
							orderInfo.getOrderAddress()) > strsub(als.get(js)
							.getAuntAddress(), orderInfo.getOrderAddress())) {
						AuntInfo a = als.get(i);
						als.set(i, als.get(js));
						als.set(js, a);
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e);
		}
		ReturnJson rj = new ReturnJson();
		rj.setStatus(0);
		Date d3 = new Date(0);
		List<AuntInfo> auntlists = new ArrayList<AuntInfo>();
		auntlists.add(null);
		for (int i = 0; i < als.size(); i++) {
			if (als.get(i).getAuntState() >= 2) {
				auntlists.add(als.get(i));
			} else {
				System.out.println("remove " + als.get(i));
			}
		}
		if (auntlists.size() == 0) {
			rj.setStatus(1);
			rj.setMsg("对不起，附近无阿姨可以为您服务,如有问题请联系管理员");
			return rj;
		}
		AssetOfHash.Map_auntList.put(orderId, auntlists);
		List<AuntInfo> lists = new ArrayList<AuntInfo>();
		for (int i = 0; i < auntlists.size(); i++) {
			if (auntlists.get(i) != null)
				lists.add(auntlists.get(i));
		}
		AssetOfHash.Map_adminauntList.put(orderId, lists);
		AssetOfHash.Map_OrderInfo.put(orderId, orderInfo);
		AssetOfHash.Map_userResId.put(orderId, userResiger1);
		AssetOfHash.Map_auntDate.put(orderId, d3);
		System.out.println("派单过程结束");
		rj.setMsg("正在为您派送订单");
		orderInfo.setOrderAddress(request.getParameter("orderAddress"));
		try {
			orderInfo.setOrderRes(sdf.parse(orderRes));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("orderRes Has Problem");
			e.printStackTrace();
		}
		try {
			orderInfo.setOrderRoughtime(sdf.parse(orderRoughtime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("orderRoughtime Has Problem");
			e.printStackTrace();
		}
		rj.setData(orderInfo);
		return rj;
	}
//	/**
//	 * 字符串比较
//	 * 
//	 * @param str1
//	 * @param str2
//	 * @return
//	 */
//	public long strsub(String str1, String str2) {
//		long ans=1000000;
//		try{
//			GeoHash g2=GeoHash.fromGeohashString(str1);
//			GeoHash g1=GeoHash.fromGeohashString(str2);
//			String a=g2.toBinaryString();
//			String b=g1.toBinaryString();
////			System.out.println(a+"  "+b+"  "+Long.valueOf(a,2).toString() +"  "+Long.valueOf(b,2).toString()+"  "+(Long.valueOf(a,2)-Long.valueOf(b,2)));
//			ans=Long.valueOf(a,2)-Long.valueOf(b,2);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return Math.abs(ans);
//	}
	/**
	 * 字符串比较
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public double strsub(String str1, String str2) {
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
	/**
	 * 找到本月阿姨的收入和所接单数
	 * 
	 * @param request
	 * @param model
	 * @param e
	 * @return
	 */
	@RequestMapping("/selectmoney")
	public @ResponseBody Object selectMoneyEachMonth(
			HttpServletRequest request, Model model, Exception e) {
		String auntId = request.getParameter("auntId");
		ReturnJson rj = new ReturnJson();
		if (auntId == null || auntId == "") {
			rj.setMsg("参数不完整");
			rj.setStatus(1);
			return rj;
		}
		try {
//			System.out.println(auntId);
			auntId = auntId.replace("\"", "");
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
//			System.out.println(index);
			CountSum ls = auntOrderService.selectMoneyEachMonth(index, auntId);
			rj.setData(ls);
			rj.setStatus(0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return rj;
	}

	/**
	 * 管理员删除订单
	 * 
	 * @param request
	 * @param model
	 * @param e
	 * @return
	 */
	@RequestMapping("/admindeleteorder")
	public @ResponseBody Object AdminDeleteOrder(HttpServletRequest request,
			Model model, Exception e) {
		String orderId = request.getParameter("order_id");
		ReturnJson rj = new ReturnJson();
		try {
			OrderInfo info = orderService.selectByPrimaryKey(orderId);
			orderService.deleteByPrimaryKey(orderId);
			String index = "0";
			AssetOfHash.Map_auntDate.remove(orderId);
			AssetOfHash.Map_auntList.remove(orderId);
			AssetOfHash.Map_OrderInfo.remove(orderId);
			AssetOfHash.Map_userResId.remove(orderId);
			AssetOfHash.Map_adminauntList.remove(orderId);
			if (info.getAuntId() != null) {
				index = String.valueOf(Integer.valueOf(info.getAuntId()
						.substring(info.getAuntId().length() - 2)) % 5);
				auntOrderService.deleteByIndexAuntId(index, orderId);
			}
			index = String.valueOf(Integer.valueOf(info.getUserId().substring(
					info.getUserId().length() - 2)) % 5);
			userOrderService.deleteByPrimaryKey(index, orderId);
		} catch (Exception es) {
			rj.setStatus(1);
			rj.setMsg("移除订单失败");
		}
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 管理员查询所有订单 的日期、金额、状态
	 * 
	 * @param request
	 * @param model
	 * @param e
	 * @return
	 */
	@RequestMapping("/adminfindallorderinfo")
	public @ResponseBody Object AdminFindOrderInfo(HttpServletRequest request,
			Model model, Exception e) {
		List<OrderInfo_YMS> selectAllOrderInfo = orderService.selectAllOrderInfo();
		ReturnJson rj = new ReturnJson();
		rj.setData(selectAllOrderInfo);
		rj.setStatus(0);
		return rj;
	}
	/**
	 * 通过阿姨id找到当天可服务的和带服务的订单
	 * 
	 * @param request
	 * @param model
	 * @param e
	 * @return
	 */
	@RequestMapping("/getsendindorderbyauntid")
	public @ResponseBody Object GetSendingOrder(HttpServletRequest request,
			Model model, Exception e) {
		ReturnJson rj = new ReturnJson();
		String auntId = request.getParameter("auntId");
		if (auntId == null || auntId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
			return rj;
		}
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		try {
			for (Entry<String, List<AuntInfo>> entry : AssetOfHash.Map_auntList
					.entrySet()) {
				if (entry != null) {
					System.out.println(entry.toString());
				}
				if (entry == null || entry.getValue() == null
						|| entry.getValue().size() <= 0) {
					continue;
				}
				if (entry.getValue().get(0) == null
						&& entry.getValue().size() >= 2
						&& entry.getValue().get(1).getAuntId()
								.equalsIgnoreCase(auntId)) {
					list.add((OrderInfo) AssetOfHash.Map_OrderInfo.get(entry
							.getKey()));
				} else {
					if (entry.getValue().get(0).getAuntId()
							.equalsIgnoreCase(auntId)) {
						list.add((OrderInfo) AssetOfHash.Map_OrderInfo
								.get(entry.getKey()));
					}
				}
			}
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			List<AuntOrderInfo> lists = auntOrderService.selectByAuntIdL(index,
					auntId);
			if (lists != null)
				for (int i = 0; i < lists.size(); i++) {
					list.add(lists.get(i));
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		rj.setData(list);
		rj.setStatus(0);
		return rj;
	}

	// 通过用户id找到用户的所有的订单
	@RequestMapping("/findorderbyauntid")
	public @ResponseBody Object FindOrderByAuntId(HttpServletRequest request,
			Model model) {
		String auntId = request.getParameter("auntId");
		int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
		ReturnJson rj = new ReturnJson();
		if (auntId == null || auntId == "") {
			rj.setMsg("参数不完整");
			rj.setStatus(1);
			return rj;
		}
		List<AuntOrderInfo> list = auntOrderService.selectByAuntId(index,
				auntId);
		System.out.println("查询阿姨所有订单数据");
		System.out.println(list);
		rj.setStatus(0);
		rj.setData(list);
		return rj;
	}

	/**
	 * 拒单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/rejectorder")
	public @ResponseBody Object RejectOrder(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		AssetOfHash.Map_auntDate.put(orderId, new Date(0));
		ReturnJson rj = new ReturnJson();
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 管理员取消订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/admindelete")
	public @ResponseBody Object AdminDelete(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("user_id");
		int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
		int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		ReturnJson rj = new ReturnJson();
		AssetOfHash.Map_auntDate.remove(orderId);
		AssetOfHash.Map_auntList.remove(orderId);
		AssetOfHash.Map_userResId.remove(orderId);
		AssetOfHash.Map_OrderInfo.remove(orderId);
		AssetOfHash.Map_adminauntList.remove(orderId);
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOrderId(orderId);
		orderinfo.setOrderState(-1);
		int j = userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
		int i = orderService.updateByPrimaryKeySelective(orderinfo);
		int k = auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
		if (i >= 0 && j >= 0 && k >= 0) {
			rj.setStatus(0);
		} else {
			rj.setStatus(1);
		}
		return rj;
	}

	// 用户取消订单
	@RequestMapping("/delete")
	public @ResponseBody Object Delete(HttpServletRequest request, Model model) {
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		System.out.println(orderId + "  " + userId);
		if (orderId == null || userId == null || orderId == "" || userId == "") {
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("信息不完整");
			return rj;
		}
		int index = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		ReturnJson rj = new ReturnJson();
		System.out.println(orderId);
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOrderId(orderId);
		orderinfo.setOrderState(-1);
		int j = userOrderService.updateByPrimaryKeySelective(index, orderinfo);
		int i = orderService.updateByPrimaryKeySelective(orderinfo);
		if (i > 0 && j > 0) {
			System.out.println(AssetOfHash.Map_auntDate.get(orderId) + "  "
					+ AssetOfHash.Map_auntDate.size());
			AssetOfHash.Map_auntDate.remove(orderId);
			System.out.println(AssetOfHash.Map_auntDate.get(orderId) + "  "
					+ AssetOfHash.Map_auntDate.size());
			AssetOfHash.Map_auntList.remove(orderId);
			System.out.println(AssetOfHash.Map_auntList.get(orderId));
			AssetOfHash.Map_userResId.remove(orderId);
			System.out.println(AssetOfHash.Map_userResId.get(orderId));
			AssetOfHash.Map_OrderInfo.remove(orderId);
			System.out.println("remove " + orderId);
			AssetOfHash.Map_adminauntList.remove(orderId);
			System.out.println("订单被取消");
			rj.setStatus(0);
		} else {
			rj.setStatus(1);
		}
		return rj;
	}

	/**
	 * 阿姨开始服务
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/startwork")
	public @ResponseBody Object StartWork(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		ReturnJson rj = new ReturnJson();
		rj.setStatus(0);
		try {
			OrderInfo orderinfo = new OrderInfo();
			orderinfo.setStartTime(new Date());
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(2);
			System.out.println(orderinfo);
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
			String userPhoneno = orderinfo.getUserId();
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("开始服务失败");
			return rj;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		rj.setData(sdf.format(new Date()));
		return rj;
	}

	// 查看订单状态
	@RequestMapping("/adminfindorderbyorderid")
	public @ResponseBody Object FindOrderID1(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		System.out.println(orderId);
		OrderInfo allOrder = orderService.selectByPrimaryKey(orderId);
		EvaluateInfo einfo = evaluateService.selectByOrderid(allOrder
				.getOrderId());
		FeedbackInfo feed = null;
		if (einfo != null)
			feed = feedbackService.selectByEvaluateId(einfo.getEvaluateId());
		List<AuntInfo> al = AssetOfHash.Map_adminauntList.get(orderId);
		ComplaintInfo com = complaintService.selectByOrderIdAndUserId(orderId,
				allOrder.getUserId());
		ReturnJson2 rj = new ReturnJson2();
		rj.setAuntlists(al);
		rj.setEvaluateInfo(einfo);
		rj.setFeedbackInfo(feed);
		rj.setComplaintInfo(com);
		rj.setOrderInfo(allOrder);
		rj.setStatus(0);
		return rj;
	}

	// 通过订单id和用户id查看订单状态
	@RequestMapping("/findorderbyorderid")
	public @ResponseBody Object FindOrderID(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		int index = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		OrderInfo allOrder = userOrderService.selectByOrderId(index, orderId);
		ReturnJson rj = new ReturnJson();
		rj.setStatus(0);
		if (allOrder == null) {
			rj.setStatus(1);
		} else {
			rj.setData(allOrder);
		}
		return rj;
	}

	/**
	 * 接单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/receiveorder")
	public @ResponseBody Object receiveOrder(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		if (orderId == null || auntId == null || orderId == "" || auntId == "") {
			ReturnJson rj = new ReturnJson();
			rj.setStatus(1);
			rj.setMsg("信息不完整");
			return rj;
		}
		System.out.println(orderId + "   " + auntId);
		System.out.println(auntId + "receiver order ！！！！！");
		if (AssetOfHash.Map_auntList.get(orderId) != null)
			System.out
					.println(AssetOfHash.Map_auntList.get(orderId).toString());
		if (AssetOfHash.Map_userResId.get(orderId) != null) {
			System.out.println(AssetOfHash.Map_userResId.get(orderId));
		}
		ReturnJson rj = new ReturnJson();
		List<AuntInfo> list = AssetOfHash.Map_auntList.get(orderId);
		if (list != null
				&& ((list.get(0) == null && list.size() > 1 && list.get(1)
						.getAuntId().equals(auntId)) || (list.get(0) != null)
						&& list.get(0).getAuntId().equals(auntId))) {
			rj.setStatus(0);
			try {
				OrderInfo orderinfo = orderService.selectByPrimaryKey(orderId);
				if (orderinfo == null) {
					rj.setMsg("order no exit!!!");
					rj.setStatus(1);
				}
				orderinfo.setAuntId(auntId);
				orderinfo.setOrderState(1);
				System.out.println(orderinfo);
				orderService.updateByPrimaryKey(orderinfo);
				String userPhoneno = orderinfo.getUserId();
				int index1 = Integer.valueOf(userPhoneno.substring(userPhoneno
						.length() - 2)) % 5;
				userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
				int index = Integer
						.valueOf(auntId.substring(auntId.length() - 2)) % 5;
				auntOrderService.insert(index, orderinfo);
				if (AssetOfHash.Map_userResId.get(orderId) != null)
					System.out.println(AssetOfHash.Map_userResId.get(orderId));
				if (AssetOfHash.Map_userResId.get(orderId) != null) {
					System.out.println(AssetOfHash.Map_userResId.get(orderId));
					JpushClientUtil.sendToRegistrationId_user(
							AssetOfHash.Map_userResId.get(orderId),
							"您的订单已被阿姨接单", "您的订单已被阿姨接单", "", orderinfo);
				}
				AssetOfHash.Map_auntDate.remove(orderId);
				AssetOfHash.Map_auntList.remove(orderId);
				AssetOfHash.Map_userResId.remove(orderId);
				AssetOfHash.Map_OrderInfo.remove(orderId);
				AssetOfHash.Map_adminauntList.remove(orderId);
			} catch (Exception e) {
				e.printStackTrace();
				rj.setMsg("接单异常");
				rj.setStatus(1);
			}
		} else {
			rj.setStatus(1);
			rj.setMsg("由于未在接单时限内接单，系统将订单派送给其他阿姨");
		}
		return rj;
	}

	/**
	 * 管理员获取所有阿姨
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/admingetaunts")
	public @ResponseBody Object AdminGetAunts(HttpServletRequest request,
			Model model) {
		ReturnJson1 rj = new ReturnJson1();
		String orderId = request.getParameter("orderId");
		if (orderId == null) {
			rj.setStatus(1);
			return rj;
		}
		List<AuntInfo> list = AssetOfHash.Map_auntList.get(orderId);
		rj.setData(list);
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 管理员派单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminsendorder")
	public @ResponseBody Object AdminSendOrder(HttpServletRequest request,
			Model model) {
		ReturnJson1 rj = new ReturnJson1();
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		System.out.println(auntId + "  " + orderId);
		List<AuntInfo> list = AssetOfHash.Map_auntList.get(orderId);
		try {
			list.add(0, null);
			AuntInfo a = auntService.selectByPrimaryKey(auntId);
			list.set(1, a);
			AssetOfHash.Map_auntList.put(orderId, list);
			AssetOfHash.Map_auntDate.put(orderId, new Date(0));
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 订单开始服务
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderauntstart")
	public @ResponseBody Object AuntStart(HttpServletRequest request,
			Model model) {
		ReturnJson1 rj = new ReturnJson1();
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setAuntStarttime(new Date());
		int index1 = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
		int i = orderService.updateByPrimaryKeySelective(orderInfo);
		int j = auntOrderService.updateByPrimaryKeySelective(index1, orderInfo);
		index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		int k = userOrderService.updateByPrimaryKeySelective(index1, orderInfo);
		if (i >= 0 && j >= 0 && k >= 0) {
			rj.setStatus(0);
		} else {
			rj.setStatus(1);
		}
		return rj;
	}

	/**
	 * 找到所有订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findallorder")
	public @ResponseBody Object FindAllOrder(HttpServletRequest request,
			Model model) {
		int page = 0;
		System.out.println(request.getParameter("page"));
		try{
			 page = Integer.valueOf(request.getParameter("page"));
		}catch(Exception e){
			e.printStackTrace();
		}
		int count = orderService.selectAllOrderCount();
		System.out.println(count);
		List<OrderInfo> allOrder = orderService.selectAllOrder(page * 20,
				page * 20 + 20);
		System.out.println(allOrder);
		ReturnJson1 rj = new ReturnJson1();
		rj.setCount(count);
		rj.setData(allOrder);
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 找到一个月未接单阿姨
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findtendayaunt")
	public @ResponseBody Object FindTenDayAunt(HttpServletRequest request,
			Model model) {
		ReturnJson1 rj = new ReturnJson1();
		int page = Integer.valueOf(request.getParameter("page"));
		System.out.println(page);
		rj.setStatus(0);
		List<String> auntlist = orderService.SelectTenDayAunt(page * 20,
				page * 20 + 20);
		rj.setData(auntlist);
		return rj;
	}

	/**
	 * 找到当月未下单用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findtendayuser")
	public @ResponseBody Object FindTenDayUser(HttpServletRequest request,
			Model model) {
		ReturnJson1 rj = new ReturnJson1();
		rj.setStatus(0);
		int page = Integer.valueOf(request.getParameter("page"));
		List<String> userlist = orderService.SelectTenDayUser(page * 20,
				page * 20 + 20);
		rj.setData(userlist);
		return rj;
	}

	/**
	 * 找到派送中订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findsendingorder")
	public @ResponseBody Object FindSendingOrder(HttpServletRequest request,
			Model model) {
		int page = Integer.valueOf(request.getParameter("page"));
		System.out.println(page);
		int count = orderService.selectSendingOrderCount();
		List<OrderInfo> allOrder = orderService.selectSendingOrder(page * 20,
				page * 20 + 20);
		ReturnJson1 rj = new ReturnJson1();
		System.out.println(count);
		System.out.println(allOrder);
		rj.setCount(count);
		rj.setData(allOrder);
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 查找所有订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectorderbymonth")
	public @ResponseBody Object SelectOrderByMonth(HttpServletRequest request,
			Model model) {
		ReturnJson rj = new ReturnJson();
		String auntId = request.getParameter("auntId");
		String month = request.getParameter("month");
		int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
		if (month.length() < 2) {
			month = "0" + month;
		}
		month = "2017-" + month;
		System.out.println(month);
		List<AuntOrderInfo> onlists = auntOrderService
				.selectOnlineOrderEachMonth(index, auntId, month);
		List<AuntOrderInfo> offlists = auntOrderService
				.selectOfflineOrderEachMonth(index, auntId, month);
		String onlineMoney = auntOrderService.selectOnlineMoneyEachMonth(index,
				auntId, month);
		String offlineMoney = auntOrderService.selectofflineMoneyEachMonth(
				index, auntId, month);
		AuntOrderContentInfo auntOrderContentInfo = new AuntOrderContentInfo();
		auntOrderContentInfo.setOffLineOrderLists(offlists);
		auntOrderContentInfo.setOnLineOrderLists(onlists);
		auntOrderContentInfo.setOfflinemoney(offlineMoney);
		auntOrderContentInfo.setOnlinemoney(onlineMoney);
		rj.setStatus(0);
		rj.setData(auntOrderContentInfo);
		System.out.println(auntOrderContentInfo.toString());
		return rj;
	}
	/**
	 * 查找所有订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryuserallorder")
	public @ResponseBody Object QueryUserAllOrder(HttpServletRequest request,
			Model model) {
		ReturnJson rj = new ReturnJson();
		String userId = request.getParameter("userId");
		int index = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		List<UserOrderInfo> orders = userOrderService.selectUserOrderByUserId(index,
				userId);
		for (int i = 0; i < orders.size(); i++) {
			String address = orders.get(i).getOrderAddress();
//			System.out.println(address);
			if (address != null && address != "" && address.length() <= 10
					&& address.length() > 5) {
				GeoHash g = GeoHash.fromGeohashString(address);
//				System.out.println(g.getPoint());
				orders.get(i).setOrderAddress(g.getPoint().toString());
			}
		}
		rj.setStatus(0);
		rj.setData(orders);
		System.out.println(orders.toString());
		return rj;
	}

	/**
	 * 查找所有订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryallorder")
	public @ResponseBody Object QueryOrder(HttpServletRequest request,
			Model model) {
		ReturnJson rj = new ReturnJson();
		String userId = request.getParameter("userId");
		int index = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
		List<UserOrderInfo> orders = userOrderService.selectByUserId(index,
				userId);
		for (int i = 0; i < orders.size(); i++) {
			String address = orders.get(i).getOrderAddress();
//			System.out.println(address);
			if (address != null && address != "" && address.length() <= 10
					&& address.length() > 5) {
				GeoHash g = GeoHash.fromGeohashString(address);
//				System.out.println(g.getPoint());
				orders.get(i).setOrderAddress(g.getPoint().toString());
			}
		}
		rj.setStatus(0);
		rj.setData(orders);
		System.out.println(orders.toString());
		return rj;
	}

	/**
	 * 用户点击线下支付完成
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/offlinepayfinsh")
	public @ResponseBody Object OffLineFinish(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		ReturnJson rj = new ReturnJson();
		if (orderId == null || auntId == null || userId == null
				|| orderId == "" || auntId == "" || userId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
		}
		rj.setStatus(0);
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(8);
			System.out.println(orderinfo);
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("线下支付失败");
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 用户点击线下支付完成
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/onlinepayfinish")
	public @ResponseBody Object OnLineFinish(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		ReturnJson rj = new ReturnJson();
		if (orderId == null || auntId == null || userId == null
				|| orderId == "" || auntId == "" || userId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
		}
		rj.setStatus(0);
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(9);
			System.out.println(orderinfo);
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("线下支付失败");
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 用户点击线下支付修改状态
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/offlinepay")
	public @ResponseBody Object OffLine(HttpServletRequest request, Model model) {
		String discountId = request.getParameter("discountId");
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		ReturnJson rj = new ReturnJson();
		if (orderId == null || auntId == null || userId == null
				|| orderId == "" || auntId == "" || userId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
		}
		if (discountId != null && discountId != "") {
			DisCount dc = new DisCount();
			dc.setDiscountId(discountId);
			dc.setIsuse(1);
			discountService.updateByPrimaryKeySelective(dc);
		}
		rj.setStatus(0);
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(6);
			System.out.println(orderinfo);
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("线下支付失败");
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 用户点击线上支付修改状态
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/onlinepay")
	public @ResponseBody Object OnLine(HttpServletRequest request, Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		String discountId = request.getParameter("discountId");
		ReturnJson rj = new ReturnJson();
		if (orderId == null || auntId == null || userId == null
				|| orderId == "" || auntId == "" || userId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
		}
		if (discountId != null && discountId != "") {
			DisCount dc = new DisCount();
			dc.setDiscountId(discountId);
			dc.setIsuse(1);
			discountService.updateByPrimaryKeySelective(dc);
		}
		rj.setStatus(0);
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(7);
			System.out.println(orderinfo);
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("线下支付失败");
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

	/**
	 * 完成订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/complishorder")
	public @ResponseBody Object ComplishOrder(HttpServletRequest request,
			Model model) {
		String orderId = request.getParameter("orderId");
		String auntId = request.getParameter("auntId");
		String userId = request.getParameter("userId");
		ReturnJson rj = new ReturnJson();
		if (orderId == null || auntId == null || userId == null
				|| orderId == "" || auntId == "" || userId == "") {
			rj.setStatus(1);
			rj.setMsg("参数不完整");
		}

		rj.setStatus(0);
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setEndTime(new Date());
			orderinfo.setOrderId(orderId);
			orderinfo.setOrderState(3);
			System.out.println(orderinfo);
			int index1 = Integer.valueOf(userId.substring(userId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			double money1 = userOrderService.selectMoneyByOrderId(index1,
					orderId);
			DecimalFormat df = new DecimalFormat("######0.00");
			String money = df.format(money1);
			System.out.println(money);
			orderinfo.setOrderMoney(String.valueOf(money));
			int index = Integer.valueOf(auntId.substring(auntId.length() - 2)) % 5;
			userOrderService.updateByPrimaryKeySelective(index1, orderinfo);
			auntOrderService.updateByPrimaryKeySelective(index, orderinfo);
			orderService.updateByPrimaryKeySelective(orderinfo);
		} catch (Exception e) {
			e.printStackTrace();
			rj.setStatus(1);
			rj.setMsg("结束服务失败");
			return rj;
		}
		rj.setStatus(0);
		return rj;
	}

}
