require.config({
    baseUrl:"",
    paths: {
        "jquery":"Javascript/jquery-2.1.1",
        "vue": "Javascript/vue"
    }
});
require(['jquery','vue'], function ($,Vue){
	var id = new Vue({
    	el:"#test",
    	data:{
    		name:"",
    		psw:""
    	}
    });
    var login = function(json,url){
    	$.ajax({
    		url: url,
    		type: 'POST',
    		dataType: 'json',
    		data: json,
    	})
    	.done(function(data) {
    		console.log(data.status)
    		if(data.status == 0){
             $("#a span").click();
     		}else{
     			alert(data.msg);
     		}
    	})
    	.fail(function() {
    		console.log("error");
    	})    	
    };
    $("#sub").click(function(event) {
    	//console.log(id.name,id.psw,id)
         login({adminPhoneno:id.name,adminPassword:id.psw},"/AuntSystem/admin/login");
    	/* Act on the event */
    })
});