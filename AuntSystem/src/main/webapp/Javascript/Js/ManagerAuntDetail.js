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
	 var month = new Date().getMonth();
	 
	 var vocation = [];
	 for(var i =0;i<=month;i++){
		 vocation[i] = i;
	 };
	 function tranDate(month){
		 var holidayMonth = month>=9?(month):("0"+month);
		 var holidayMonth = (new Date().getYear() + 1900) + "-" + holidayMonth;
		 return holidayMonth;
	 }
	 function getRate(month,data){
		 var day = new Date(2017,month,0);
		 var daycount = day.getDate()*2;
		 var holiday = 0;
		 var rate = 0;
		 for(var i = 0;i<data.length;i++){
			 var startTime = Number(data[i].holidayStarttime.slice(8,10))*2;
			 var endTime = Number(data[i].holidayEndtime.slice(8,10))*2;
			 startTime = (data[i].holidayStarttime.slice(11)) == "上午" ? (startTime+1):(startTime+2);
			 endTime = (data[i].holidayEndtime.slice(11)) == "上午" ? (endTime+1):(endTime+2);
			 holiday = endTime - startTime + 1 + holiday;
		 };
		 rate = (holiday/daycount).toFixed(3);
		 Aunt.rate = rate;
	//	 console.log(1-Aunt.rate)
		 changeStyle(rate);
	 };
	 
	 function changeStyle(rate){
		 if(rate < 0.5){
			     rate = rate * 100 * 3.6;
			     $("head").append("<style>.circal:before{background:green;transform:rotate("+rate+"deg);}</style>")
		 }else{
			 rate = (rate - 0.5) * 100 * 3.6;
			 $("head").append("<style>.circal:before{background:green;transform:rotate("+rate+"deg);}</style>")
		 }
	 };
	 
	 
	 function getHoliday(month){
		// console.log(123)
		   var holidayMonth = tranDate(month);
		   console.log(holidayMonth)
		    $.ajax({
		        url: '/AuntSystem/aunt/findholiday',
		        type: 'POST',
		        dataType: 'json',
		        data: {auntId:aunt_id,mouth:holidayMonth},
		    }).done(function(data) {
		    	console.log(data)
		    	Aunt.holidays = data.data;
		    	getRate(month+1,data.data);
		    }).fail(function() {
		        alert("加载页面失败！");
		    })
	 }
	 var Aunt = new Vue({
         el:"#content",
         data:{
             items:[],
             server:[],
             months:vocation,
             holidays:[],
             rate:0,
             selected:month,
             moneys:[]
         },
         methods:{
             //提交
             subState:function(){
            	 var aunt_status =  $("input[name='status']:checked").val();
            	 $.ajax({
            	        url: '/AuntSystem/aunt/updatestate',
            	        type: 'POST',
            	        dataType: 'json',
            	        data: {aunt_id:aunt_id,aunt_status:aunt_status},
            	    }).done(function(data) {
            	    	if(data.status == 0){
            	    		alert("修改成功！");
            	    		window.location.reload();
            	    	}else{
            	    		alert("修改失败!");
            	    	}
            	    }).fail(function() {
            	        alert("修改失败！")
            	    })
             },
             holidaySelect:function(){
            	 getHoliday(Aunt.selected);
             },
             Details:function(id,month){
            	 window.location = "ManagerAuntMoney.jsp?auntId="+id+"&month="+month;
             }
         },
         computed:{
        	 auntState:function(){
        		var state = this.items.auntState;
        		var stateDesc = null;
        		if(state == 1) stateDesc = "审核中";
        		if(state == 2) stateDesc = "审核通过";
        		if(state == 3) stateDesc = "审核不通过";
        		return stateDesc;
        	 },
        	 auntParttimejob:function(){
        		 return this.items.auntParttimejob == 0 ? "兼职" :"全职";
        	 }
         }
         
     })
    var src = window.location.href;
    var aunt_id = src.slice(src.indexOf("?aunt_id=")+9);
    $.ajax({
        url: '/AuntSystem/aunt/adminfindauntbyId',
        type: 'POST',
        dataType: 'json',
        data: {aunt_id:aunt_id},
    }).done(function(data) {
    	console.log(data)
    	Aunt.items = data.auntInfo;
    	Aunt.server = data.auntservicecontents;
    	Aunt.moneys = data.moneyPay;
    }).fail(function() {
        alert("加载页面失败！")
    });
    getHoliday(Aunt.selected);
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