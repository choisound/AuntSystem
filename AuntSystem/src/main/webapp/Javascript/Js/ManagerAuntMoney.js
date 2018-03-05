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
    var str = window.location.href;
    var getUrl = "/AuntSystem/order/selectorderbymonth";
    var auntIdStart = str.indexOf("auntId=");
    var monthStart = str.indexOf("&month=");
    var auntId = str.slice(auntIdStart+7,monthStart);
    var month = str.slice(monthStart+7);
    console.log(auntId+"    "+month)
    var Order = new Vue({
        el:"#content",
        data:{
        	onlinemoney:0,
        	offlinemoney:0,
        	onLineOrderLists:[],
        	offLineOrderLists:[]
        },
        methods:{
            detail:function(id){
                window.open("ManagerOrderDetail.jsp?order_id="+id);
            }
        }
    });
    
        $.ajax({
            url:getUrl,
            type: 'POST',
            dataType: 'json',
            data: {auntId:auntId,month:month},
        }).done(function(data) {
        	    console.log(data)
            //data = [[{aunt_name:"xxx",aunt_sex:"xxx",aunt_phone:"xxx",aunt_address:"xxx",aunt_state:"xxx"}],100]
                if(!data.data.onlinemoney){
                    $(".online").html("There is onlineOrder here! ").css("font-size","20px")
                }else{
                    Order.onlinemoney = data.data.onlinemoney;
                    Order.onLineOrderLists = data.data.onLineOrderLists;
                }
        	    
        	    if(!data.data.offlinemoney){
                    $(".offline").html("There is offlineOrder here! ").css("font-size","20px")
                }else{
                    Order.offlinemoney = data.data.offlinemoney;
                    Order.offLineOrderLists = data.data.offLineOrderLists;
                }
            }).fail(function() {
            alert("加载页面失败！")
            })
       
});