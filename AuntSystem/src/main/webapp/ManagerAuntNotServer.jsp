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
        <table id="table1">
            <tr >
                <th>Aunt_name</th>
                <th>Aunt_sex</th>
                <th>Aunt_phone</th>
                <th>Aunt_address</th>
                <th>Aunt_status</th>
                <th>Alert</th>
            </tr>
            <tr  v-for="(item,index) in items" :class=((index%2==0)?"alt":"")>
                <td><span class="openWin" v-on:click="detail(item.auntId)">{{item.auntName}}</span></td>
                <td>{{item.auntSex}}</td>
                <td>{{item.auntPhoneno}}</td>
                <td>{{item.auntAddress}}</td>
                <td>{{item.auntState}}</td>
                <td><span href="" class="delete" v-on:click="Delete(item.auntId,index)">Alert</span></td>
            </tr>
            <!--<tr>-->
                <!--<td><a href="ManagerAuntDetail.html">Aunt_name</a></td>-->
                <!--<td>Aunt_sex</td>-->
                <!--<td>Aunt_phone</td>-->
                <!--<td>Aunt_address</td>-->
                <!--<td>Aunt_status</td>-->
                <!--<td><a href="" class="delete">Delete</a></td>-->
            <!--</tr>-->
            <!--<tr class="alt">-->
                <!--<td><a href="ManagerAuntDetail.html">Aunt_name</a></td>-->
                <!--<td>Aunt_sex</td>-->
                <!--<td>Aunt_phone</td>-->
                <!--<td>Aunt_address</td>-->
                <!--<td>Aunt_status</td>-->
                <!--<td><a href="" class="delete">Delete</a></td>-->
            <!--</tr>-->
        </table>
    </div>
    <div id="division"></div>
    <div class="footer">
       	版权所有：上天入地OneBoom团队
    </div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerAuntNotServer.js"></script>