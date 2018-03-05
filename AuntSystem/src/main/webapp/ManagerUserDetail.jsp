<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerUserDetail.jsp' starting page</title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Css/ManagerHeader.css">
    <link rel="stylesheet" href="Css/reset.css">
    <link rel="stylesheet" href="Css/UserDetail.css">
    <link rel="stylesheet" href="Css/detail.css">
    <link rel="stylesheet" href="Css/table.css">
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
    <div class="information">
        <p class="tap" style="margin-top: 0">用户基本资料</p>
        <p class="infom setWidth aunt_id"><span class="lab">编码:</span>{{items.userId}}</p>
        <p class="infom   aunt_name"><span class="lab">姓名:</span>{{items.userName}}</p>
        <p class="infom   aunt_sex"><span class="lab">性别:</span>{{items.userSex}}</p>
        <p class="infom   aunt_address"><span class="lab">地址:</span>{{items.userAddress}}</p>
        <p class="infom   phone"><span class="lab">手机号码:</span>{{items.userPhoneno}}</p>
        <p class="infom  count"><span class="lab">帐号:</span>{{items.userCount}}</p>
        <p class="infom   password"><span class="lab">密码:</span>{{items.userPassword}}</p>
        <p class="infom   status"><span class="lab">状态:</span>{{items.userState}} (0为欠费 1为正常)</p>
        <p class="infom  parttime"><span class="lab">积分:</span>{{items.userJifen}}</p>
        <div class="clear"></div>
        <p class="tap">用户黑名单列表</p>
        <table id="table1">
            <tr >
                <th>Aunt_name</th>
                <th>Aunt_sex</th>
                <th>Aunt_phone</th>
            </tr>
            <tr v-for="(item,index) in tables" :class=((index%2==0)?"alt":"")>
                <td><span class="openWin" v-on:click="detail(item.auntId)">{{item.auntInfo.auntName}}</></td>
                <td>{{item.auntInfo.auntSex}}</td>
                <td>{{item.auntInfo.auntPhoneno}}</td>
            </tr>
        </table>
        <!--<div class="clear"></div>-->
        <!--<p class="tap">阿姨身份证照片</p>-->

        <!--<div class="clear"></div>-->
        <!--<p class="tap">阿姨状态</p>-->

    </div>
</div>
<div class="clear"></div>
<div class="footer">
	版权所有：上天入地OneBoom团队
</div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerUserDetail.js"></script>