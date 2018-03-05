/**
 * Created by DarkieToothpaste on 2017/4/19.
 */
require.config({
    baseUrl:"",
    paths: {
        "jquery":"Javascript/jquery-2.1.1",
        "vue": "Javascript/vue"
    }
});
require(['jquery','vue'], function ($,Vue){
	var User = new Vue({
        el:"#content",
        data:{
            items:[],
            tables:[]
        },
        methods:{
            detail:function(id){
                window.open("ManagerAuntDetail.jsp?aunt_id="+id);
            }
        }
    });
    var src = window.location.href;
    var user_id = src.slice(src.indexOf("?user_id=")+9);
    $.ajax({
        url: '/AuntSystem/user/adminfind',
        type: 'POST',
        dataType: 'json',
        data: {user_id:user_id},
    }).done(function(data) {
        //data = [[{aunt_name:"xxx",aunt_sex:"xxx",aunt_phone:"xxx",aunt_address:"xxx",aunt_state:"xxx"}],100]
       User.items = data.data;
    }).fail(function() {
        alert("加载页面失败！")
    });
    $.ajax({
        url: '/AuntSystem/blacklist/find',
        type: 'POST',
        dataType: 'json',
        data: {user_id:user_id},
    }).done(function(data) {
    	console.log(data)
        //data = [[{aunt_name:"xxx",aunt_sex:"xxx",aunt_phone:"xxx",aunt_address:"xxx",aunt_state:"xxx"}],100]
        if(data.data.length>0){
            User.tables = data.data;
        }else{
            $("#table1").html("There is nothing here! ").css("font-size","20px")
        }
    }).fail(function() {
        alert("加载页面失败！")
    })
     
   
});