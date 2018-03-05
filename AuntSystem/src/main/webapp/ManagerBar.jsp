<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
 <meta charset="UTF-8">
    <title>Title</title>
<style type="text/css">
        html{
            height: 100%;
        }
        body{
            height: 100%;
        }
    </style>
</head>
<body>
    <div id="map-wrap" style="height: 100%;">
        <!-- 这里以后是地图 -->
    </div>
</body>
</html>
<script src="Javascript/echarts.min.js"></script>
<script src="Javascript/china.js"></script>
<script src="Javascript/jquery-2.1.1.js"></script>
<script>
var text;
$.ajax({
            url: '/AuntSystem/order/adminfindallorderinfo',
            type: 'GET',
            async:false,
            dataType: 'json',
        }).done(function(data) {
            console.log(data);
            text = data;
            }).fail(function() {
            alert("加载页面失败！")
            })

var myOrder = text.data;
var arr1 = new Array(12)
var arr2 = new Array(12)
for(var i = 0;i<12;i++){
    arr1[i] = 0 ;
    arr2[i] = 0 ;
}

 myOrder.map(function(obj){
    var time = obj.startTime;
    if (!time) {return ;}
    var month = new Date(time).getMonth()+1
    console.log(arr1[month]+obj.orderMoney)
    obj.orderState == 9 ? (arr1[month]+=Number(obj.orderMoney)):(arr2[month]+=Number(obj.orderMoney)) 
});
 for(var i = 0;i<12;i++){
    arr1[i] = Math.floor(arr1[i])
    arr2[i] = Math.floor(arr2[i])
}
var myChart = echarts.init(document.getElementById('map-wrap'));
    option = {
    title : {
        text: '全年订单',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['线上支付','线下支付']
    },
    toolbox: {
        show : true,
        feature : {
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'线上支付',
            type:'bar',
            data:arr1,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'线下支付',
            type:'bar',
            data:arr2,
            markPoint : {
                data : [
                    {name : '月最高'},
                    {name : '月最低'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
 myChart.setOption(option);</script>