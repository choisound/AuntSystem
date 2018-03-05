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
    var src = window.location.href;
    var complaint_id = src.slice(src.indexOf("?complaint_id=")+14);
    $.ajax({
        url: '/AuntSystem/complaint/findbyid',
        type: 'POST',
        dataType: 'json',
        data: {complaint_id:complaint_id},
    }).done(function(data) {
        //data = [[{aunt_name:"xxx",aunt_sex:"xxx",aunt_phone:"xxx",aunt_address:"xxx",aunt_state:"xxx"}],100]
        if(data){
        	console.log(data)
            show(data);
        }else{
            $("#content").html("There is nothing here! ").css("font-size","20px")
        }
    }).fail(function() {
        alert("加载页面失败！")
    })
    function show(data){
        var Complaint = new Vue({
            el:"#content",
            data:{
                items:data.data
            },
            methods:{
                //新窗口
            	detail:function(src,id){
                    window.open(src+id);
                }
            }
        })
    }
});