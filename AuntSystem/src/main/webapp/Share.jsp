<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Share.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <style type="text/css">
    body{
    	background: url(images/loginBg.png);
    	background-size: 100%;
    	text-align: center;
    	font-size: 1.4em;
    }
    p{
    	margin-top: 30%;
    	color: greenyellow;
    }
    input{
    	width: 60%;
    	margin: 20px auto;
    	line-height: 50px;
    	border-radius: 10px;
    	border:1px solid greenyellow;
    	outline: none;
    	padding-left: 20px;
    }
    div{
    	width: 80%;
    	margin: 20px auto;
    }
    div h2{
        text-align:center;
        font-size: 30px; 
    }
    div p{
    	margin-top: 20px;
    	text-align: left;
    	margin-left: 10%;
    	font-size: 1em;
    }
    button{
    	width: 120px;
    	height:40px;
    	background: greenyellow;
    	border:none;
    	color: #fff;
    	border-radius: 10px;
    	font-size: 1em;
    }
</style>
  <body>
     <p>请输入您的手机号码</p>
     <input id="phone" type="text"><br>
     <button class="sub">提交</button>
     <div class="rule">
     	<h2>活动规则</h2>
     	<p>每位用户每天至多领取3个优惠券。</p>
     	<p>使用优惠券时的下单手机号需为填入的手机号</p>
     	<p>注册登录后即可使用</p>
     	<p>OneBoom团队拥有最终解释权，如有疑问，请咨询客服电话：15768650644</p>
     </div>
     <div>
     	<h2>产品介绍</h2>
     	<p>阿姨来了是家政+互联网产品。</p>
     	<p>为你提供便利的服务，给你一个干净的家</p>
     	<p>服务分为大扫除，地板打蜡，日常清洁，真皮沙发保养等。</p>
     </div>
  </body>
<html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/Share.js"></script>