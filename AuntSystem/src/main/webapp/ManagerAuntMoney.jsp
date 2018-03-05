<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
 <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Css/ManagerHeader.css">
    <link rel="stylesheet" href="Css/table.css">
    <link rel="stylesheet" href="Css/reset.css">
     <link rel="stylesheet" href="Css/division.css">
     <style>
       .online,.offline{
           text-align:center;
       }
       .online h4,.offline h4{
   		    margin-bottom: 20px;
		    font-size: 26px;
		    color: green;
		    font-stretch: b;
		    font-weight: bold;
       }
       .totalprice{
       float: right;
    margin-right: 40px;
    margin-top: 20px;
    font-size: 20px;
    font-weight: bold;}
     </style>
</head>
<body>
    <div class="head"><h2>The AuntComing Manager</h2></div>
    <div class="nav">
        <a class="allUser" href="ManagerUser.jsp">全部用户</a>
        <a class="allAunt" href="ManagerAunt.jsp">全部阿姨</a>
        <a class="waitCheck" href="ManagerWaitAunt.jsp">待审核阿姨</a>
        <a class="allOrder" href="ManagerOrder.jsp">所有订单</a>
        <a class="sendingOrder" href="ManagerSending.jsp">派送中订单</a>
        <a class="allBlack" href="ManagerBlack_List.jsp">所有黑名单</a>
        <a class="allComplain" href="ManagerComplain_List.jsp">投诉表</a>
        <a class="allBlack" href="ManagerAuntNotServer.jsp">一个月无接单阿姨</a>
        <a class="allComplain" href="ManagerUserNotServer.jsp">一个月无下单用户</a>
    </div>

    <div class="content" id="content">
       <div class="online">
        <h4>线上支付</h4>
        <table id="table1">
            <tr >
                <th>orderId</th>
                <th>startTime</th>
                <th>endTime</th>
                <th>orderAddress</th>
                <th>money</th>
            </tr>
            <tr  v-for="(item,index) in onLineOrderLists" :class=((index%2==0)?"alt":"")>
                <td><span class="openWin" v-on:click="detail(item.orderId)">{{item.orderId}}</></td>
                <td>{{item.startTime | time}}</td>
                <td>{{item.endTime | time}}</td>
                <td>{{item.orderZwaddress}}</td>
                <td>{{item.orderMoney}}</td>
            </tr>
        </table>
        <span class="totalprice">总计：<span style="color:red">{{onlinemoney}}</span>元</span>
        </div>
        
        <div class="clear"></div>
        <div class="offline">
        <h4>线下支付</h4>
        <table id="table1">
            <tr >
                <th>orderId</th>
                <th>startTime</th>
                <th>endTime</th>
                <th>orderAddress</th>
                <th>money</th>
            </tr>
            <tr  v-for="(item,index) in offLineOrderLists" :class=((index%2==0)?"alt":"")>
                <td><span class="openWin" v-on:click="detail(item.orderId)">{{item.orderId}}</></td>
                <td>{{item.startTime | time}}</td>
                <td>{{item.endTime | time}}</td>
                <td>{{item.orderZwaddress}}</td>
                <td>{{item.orderMoney}}</td>
            </tr>
        </table>
        <span class="totalprice">总计：<span style="color:red">{{offlinemoney}}</span>元</span>
        </div>
    </div>
    <div id="division"></div>
    <div class="footer">
       	版权所有：上天入地OneBoom团队
    </div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerAuntMoney.js"></script>