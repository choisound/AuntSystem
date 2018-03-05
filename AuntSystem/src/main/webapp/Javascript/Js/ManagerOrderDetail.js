/**
 * Created by DarkieToothpaste on 2017/4/19.
 */
require.config({
    baseUrl:"",
    paths: {
        "jquery":"Javascript/jquery-2.1.1",
        "vue": "Javascript/vue",
        "division":"Javascript/Division"
    }
});
require(['jquery','vue','division'], function ($,Vue,division){
	$().ready(function() {
		 Vue.filter('time',
		 function(value) {
			 if(!value){
				 return "";
			 }
		 var date = new Date(value);
		 Y = date.getFullYear(),
		 m = date.getMonth() + 1,
		 d = date.getDate(),
		 H = date.getHours(),
		 i = date.getMinutes(),
		 s = date.getSeconds();
		 if (m < 10) {
		  m = '0' + m;
		 }
		 if (d < 10) {
		  d = '0' + d;
		 }
		 if (H < 10) {
		  H = '0' + H;
		 }
		 if (i < 10) {
		  i = '0' + i;
		 }
		 if (s < 10) {
		  s = '0' + s;
		 }
		 var t = Y + '-' + m + '-' + d+" "+H + ":"+i+":"+s;
		 return t;
		 });
		}); 
	
	 var Order = new Vue({
         el:"#content",
         data:{
             items:[],
             aunt:[],
             comment:[],
             feedback:[],
             complaint:[],
             aunts:[]
         },
         methods:{
        	 //新窗口
        	 detail:function(src,id){
                 window.open(src+id);
             },
             //提交
             subAunt:function(){
            	 var auntId =  $(".aunt_list").val();
            	 $.ajax({
            	        url: '/AuntSystem/order/adminsendorder',
            	        type: 'POST',
            	        dataType: 'json',
            	        data: {auntId:auntId,orderId:Order.items.orderId},
            	    }).done(function(data) {
            	    	console.log(data)
            	    	if(data.status == 0){
            	    		alert("派送成功！");
            	    		window.location.reload();
            	    	}else{
            	    		alert("派送失败!");
            	    	}
            	    }).fail(function() {
            	        alert("派送失败！")
            	    })
             },
             cancel:function(){
            	 $.ajax({
         	        url: '/AuntSystem/order/admindelete',
         	        type: 'POST',
         	        dataType: 'json',
         	        data: {user_id:Order.items.userId,orderId:Order.items.orderId,auntId:Order.items.auntId},
         	    }).done(function(data) {
         	    	console.log(data)
         	    	if(data.status == 0){
         	    		alert("取消成功！");
         	    		window.location.reload();
         	    	}else{
         	    		alert("取消失败!");
         	    	}
         	    }).fail(function() {
         	        alert("取消失败！")
         	    })
             }
         },
         computed:{
        	 server:function(){
        		var state = this.items.serviceId;
        		var stateDesc = null;
        		if(state == 0) stateDesc = "真皮沙发保养";
        		if(state == 1) stateDesc = "地板打蜡";
        		if(state == 2) stateDesc = "大扫除";
        		if(state == 3) stateDesc = "日常保洁";
        		return stateDesc;
        	 },
        	 state:function(){
        		var state = this.items.orderState;
         		var stateDesc = null;
         		if(state < 0) stateDesc = "已取消";
         		if(state == 0) stateDesc = "待接单";
         		if(state == 1) stateDesc = "已派单";
         		if(state == 2) stateDesc = "服务中";
         		if(state == 3) stateDesc = "待支付";
         		if(state == 4) stateDesc = "已完成";
         		if(state == 5) stateDesc = "已评价";
         		if(state == 0) isShow = true;
         		else isShow = false;
         		if(state <=1) showCancel = true;
         		else showCancel = false;
         		return {
         			stateDesc:stateDesc,
         			isShow:isShow,
         			showCancel:showCancel
         		}
        	 }
         }
         
     })
    var src = window.location.href;
    var order_id = src.slice(src.indexOf("?order_id=")+10);
    $.ajax({
        url: '/AuntSystem/order/adminfindorderbyorderid',
        type: 'POST',
        dataType: 'json',
        data: {orderId:order_id},
    }).done(function(data) {
    	console.log(data)
    	Order.items = data.orderInfo;
    	Order.comment = data.evaluateInfo || {evaluateContent:"",evaluateTime:""};
    	Order.feedback = data.feedbackInfo || {feedbackContent:""};
    	Order.complaint = data.complaintInfo || {complaintContent:""};
    	Order.aunts = data.auntlists;
    }).fail(function() {
        alert("加载页面失败！")
    })
    //获取服务
//    $.ajax({
//        url: '/AuntSystem/aunt/findservice',
//        type: 'POST',
//        dataType: 'json',
//        data: {auntId:aunt_id},
//    }).done(function(data) {
//    	Aunt.server = data.data;
//    }).fail(function() {
//        alert("加载页面失败！")
//    })
    
});