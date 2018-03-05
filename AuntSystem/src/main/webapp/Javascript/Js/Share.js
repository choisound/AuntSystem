require.config({
    baseUrl:"",
    paths: {
        "jquery":"Javascript/jquery-2.1.1",
    }
});
require(['jquery'], function ($){
       
       $(".sub").click(function(){
    	   var phone = $("#phone").val();
    	   if(!phone){
    		   alert("请输入您的手机号码!");
    		   return ;
    	   }
    	   $.ajax({
               url: '/AuntSystem/discount/share',
               type: 'POST',
               dataType: 'json',
               data: {phone:phone},
           }).done(function(data) {
               console.log(data);
               $(".rule").html("<p>尊敬的"+phone+"用户,您领取了"+data.data.discountMoney+"元优惠卷，快去使用吧！</p>")
               }).fail(function() {
               alert("加载页面失败！")
               })
       });
});