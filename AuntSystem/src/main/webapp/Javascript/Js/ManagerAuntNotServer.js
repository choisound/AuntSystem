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
            url: '/AuntSystem/order/findtendayaunt',
            type: 'POST',
            dataType: 'json',
            data: {page:0},
        }).done(function(data) {
            console.log(data);
                 if(data.data.length <=0){
                     $("#content").html("There is nothing here! ").css("font-size","20px")
                 }else{
                     show(data);
                 }
            }).fail(function() {
            alert("加载页面失败！")
            })
        function show(data){
            var amount = data.count;
            division.division(amount,20,"division",1,function(){
                $.ajax({
                    url: '/AuntSystem/order/findtendayaunt',
                    type: 'POST',
                    dataType: 'json',
                    data: {page:this.Current-1},
                }) .done(function(data) {
                        $("html,body").animate({scrollTop:$("#content").offset().top},300)
                        Aunt.items = data.data;
                    })
            });
            var Aunt = new Vue({
                el:"#table1",
                data:{
                    items:data.data
                },
                methods:{
                    detail:function(id){
                        window.open("ManagerAuntDetail.jsp?aunt_id="+id);
                    },
                    Delete: function (id, index) {
                        var that = this;
                        var com = confirm("是否警告该阿姨？");
                        if (com) {
                            $.ajax({
                                url: '/AuntSystem/aunt/alertAunt',
                                type: 'POST',
                                dataType: '',
                                data: {aunt_id: id},
                            }).done(function () {
                                alert("警告成功!将会推送警告信息给阿姨。");
                            }).fail(function () {
                                alert("警告失败!")
                            })
                        }
                    }
                }
            })
        }
});