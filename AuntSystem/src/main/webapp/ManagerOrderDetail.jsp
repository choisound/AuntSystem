<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerOrderDetail.jsp' starting page</title>
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
        <p class="tap" style="margin-top: 0">订单基本资料</p>
        <p class="infom setWidth aunt_id"><span class="lab">订单编码:</span>{{items.orderId}}</p>
        <p class="infom setWidth  aunt_name"><span class="lab">阿姨:</span><a v-on:click="detail('ManagerAuntDetail.jsp?aunt_id=',items.auntId)" href="javascript:void(0)">{{items.auntId}}</a></p>
        <p class="infom setWidth  user_name"><span class="lab">用户:</span><a v-on:click="detail('ManagerUserDetail.jsp?user_id=',items.userId)" href="javascript:void(0)">{{items.userId}}</a></p>
        <p class="infom   service"><span class="lab">服务:</span>{{server}}</p>
        <p class="infom   address"><span class="lab">地址:</span>{{items.orderZwaddress}}</p>
        <p class="infom   startTime"><span class="lab">开始时间:</span>{{items.startTime | time}}</p>
        <p class="infom  endTime"><span class="lab">结束时间:</span>{{items.endTime | time}}</p>
        <p class="infom   money"><span class="lab">价格:</span>{{items.orderMoney}}元</p>
        <p class="infom   status"><span class="lab">状态:</span>{{state.stateDesc}}</p>
        <p class="infom  res"><span class="lab">预约时间:</span>{{items.orderRes | time}}</p>
        <p class="infom  roughTime"><span class="lab">估计完成时间:</span>{{items.orderRoughtime | time}}</p>
        <p class="infom setWidth clear desc"><span class="lab">订单描述:</span>{{items.orderDesc}}</p>
        <button class="sub right" :class=(state.showCancel?"":"hide") v-on:click = "cancel()" >Cancel</button>
        <div class="clear"></div>
        <p class="tap">评论及回复</p>
        <p class="infom  evaluate"><span class="lab">用户评论:</span>{{comment.evaluateContent}}</p>
        <p class="infom  evaluate"><span class="lab">评论时间:</span>{{comment.evaluateTime | time}}</p>
        <p class="infom setWidth evaluate"><span class="lab">阿姨回复:</span>{{feedback.feedbackContent}}</p>
        <div class="clear"></div>
        <p class="tap">用户投诉</p>
        <p class="infom setWidth evaluate"><span class="lab">用户投诉:</span>{{complaint.complaintContent}}</p>
        <div class="clear"></div>
        <p class="tap" :class=(state.isShow?"":"hide")>重新派送订单</p>
        <div class="sendOrder" :class=(state.isShow?"":"hide")>
            <select name="" id="" class="Aunt_list">
                <option v-for="aunt in aunts" :value='aunt.auntId'>{{aunt.auntName}}：{{aunt.auntPhoneno}}</option>
            </select>
            <button class="sub right" v-on:click = "subAunt()" >Submit</button>
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="footer">
	版权所有：上天入地OneBoom团队
</div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerOrderDetail.js"></script>