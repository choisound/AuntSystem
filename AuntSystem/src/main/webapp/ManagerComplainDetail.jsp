<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerComplainDetail.jsp' starting page</title>
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
    <link rel="stylesheet" href="Css/OrderDetail.css">
    <link rel="stylesheet" href="Css/detail.css">
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
        <p class="tap" style="margin-top: 0">投诉基本资料</p>
        <p class="infom setWidth aunt_id" ><span class="lab">投诉编码:</span>{{items.complaintId}}</p>
        <p class="infom  setWidth   user_name" v-on:click="detail('ManagerUserDetail.jsp?user_id=',items.userId)"><span class="lab">用户:</span><a href="javascript:void(0)">{{items.userId}}</a></p>
        <p class="infom  setWidth   aunt_name" v-on:click="detail('ManagerOrderDetail.jsp?order_id=',items.orderId)"><span class="lab">订单:</span><a href="javascript:void(0)">{{items.orderId}}</a></p>
        <div class="clear"></div>
        <p class="tap">投诉内容</p>
        <p class="infom setWidth clear desc"><span class="lab">投诉内容:</span>{{items.complaintContent}}</p>
    </div>
</div>
<div class="clear"></div>
<div class="footer">
	版权所有：上天入地OneBoom团队
</div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerComplainDetail.js"></script>