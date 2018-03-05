/**
 * Created by DarkieToothpaste on 2017/4/18.
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
    $.ajax({
        url: '/AuntSystem/order/findtendayuser',
        type: 'POST',
        dataType: 'json',
        data: {page:0},
    }).done(function(data) {
    	console.log(data)
        //data = [[{aunt_name:"xxx",aunt_sex:"xxx",aunt_phone:"xxx",aunt_address:"xxx",aunt_state:"xxx"}],100]
        if(data.data.length <=0){
            $("#content").html("There is nothing here! ").css("font-size","20px")
        }else{
        	console.log(data)
            show(data);
        }
    }).fail(function() {
        alert("加载页面失败！")
    })
    function show(data){
        var amount = data.count;
        division.division(amount,20,"division",1,function(){
            $.ajax({
                url: '/AuntSystem/order/findtendayuser',
                type: 'POST',
                dataType: 'json',
                data: {page:this.Current-1},
            }) .done(function(data) {
                $("html,body").animate({scrollTop:$("#content").offset().top},300)
                User.items = data.data;
            })
        });
        var User = new Vue({
            el:"#table1",
            data:{
                items:data.data
            },
            methods:{
                detail:function(id){
                    window.open("ManagerUserDetail.jsp?user_id="+id);
                },
                Delete: function (id, index) {
                    var that = this;
                    var com = confirm("是否赠予该用户优惠卷？");
                    if (com) {
                        $.ajax({
                            url: '/AuntSystem/discount/adddiscounts',
                            type: 'POST',
                            dataType: '',
                            data: {userId: id},
                        }).done(function () {
                            alert("赠予成功!");
                        }).fail(function () {
                            alert("赠予失败!")
                        })
                    }
                }
            }
        })
    }
});