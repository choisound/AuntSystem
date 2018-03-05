<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerAuntDetail.jsp' starting page</title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Css/ManagerHeader.css">
    <link rel="stylesheet" href="Css/reset.css">
    <link rel="stylesheet" href="Css/AuntDetail.css">
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
        <p class="tap" style="margin-top: 0">阿姨基本资料</p>
        <p class="infom setWidth aunt_id"><span class="lab">编码:</span>{{items.auntId}}</p>
        <p class="infom   aunt_name"><span class="lab">姓名:</span>{{items.auntName}}</p>
        <p class="infom   aunt_sex"><span class="lab">性别:</span>{{items.auntSex}}</p>
        <p class="infom   aunt_address"><span class="lab">地址:</span>{{items.auntAddress}}</p>
        <p class="infom   identify"><span class="lab">身份证号码:</span>{{items.auntIdentity}}</p>
        <p class="infom   phone"><span class="lab">手机号码:</span>{{items.auntPhoneno}}</p>
        <p class="infom  count"><span class="lab">帐号:</span>{{items.auntCount}}</p>
        <p class="infom   password"><span class="lab">密码:</span>{{items.auntPassword}}</p>
        <p class="infom   position"><span class="lab">位置:</span>{{items.auntPosition}}</p>
        <p class="infom  parttime"><span class="lab">兼职or全职:</span>{{auntParttimejob}} </p>
        <div class="clear"></div>
        <p class="tap">阿姨服务资料</p>
        <p class="infom  service" v-for="servers in server"><span class="lab">{{servers.serviceDesc}} 好评率:</span><span>{{servers.serviceRate || "暂无资料"}}</span></p>
        
        <div class="clear"></div>
        <p class="tap">阿姨身份证照片</p>
        <div class="identify_img">
            <img :src=items.auntIdentityimage alt="正面身份证" >
            <img :src=items.auntIdentityibackamge alt="反面身份证" >
        </div>
        
        <div class="clear"></div>
        <p class="tap">阿姨月结</p>
            <table id="table1">
	            <tr >
	                <th>Month</th>
	                <th>State</th>
	                <th v-on:click = "MoneyDetail()">Detail</th>
	            </tr>
	             <tr v-for="(item,index) in moneys" :class=((index%2==0)?"alt":"")>
	                <td>{{index+1}}月</td>
	                <td :class=((item==0)?"colorRed":"")>{{item==0?"未结算":"已结算"}}</td>
	                <td><span href="" class="delete" v-on:click="Details(items.auntId,index+1)">Details</span></td>
	            </tr> 
        	</table>
        
        <div class="clear"></div>
        <p class="tap">阿姨出勤</p>
        	<select name="" id="" class="Aunt_vocation" @change="holidaySelect()" v-model="selected" >
        	      
        	      <template v-for="month in months" >
				        <option  :value='month+1' v-if="month == months.length-1" selected>
				           {{month+1}}月
				        </option>
				        <option  :value='month+1' v-else>
				           {{month+1}}月
				        </option>
				  </template>
        	      
                  <!-- <option v-for="month in months" :value='month+1'>{{month+1}}月</option> -->
            </select> 
            <div class="wrap left">
	   			  <div class="circal" ></div>
	   			  <p><span class="green"></span><span class="rate">出勤率：{{((1-rate)*100).toFixed(1)+"%"}}</span></p>
	   			  <p><span class="red"></span><span class="rate">缺勤率：{{(rate*100).toFixed(1)+"%"}}</span></p>
   			</div>
   			<div class="right absence">
   			      <table id="table1">
	            	  <tr >
	                  <th>Start</th>
	                  <th>End</th>
	            	  </tr>
	            	  <tr v-for="(item,index) in holidays" :class=((index%2==0)?"alt":"")>
	                  <td>{{item.holidayStarttime}}</td>
	                  <td>{{item.holidayEndtime}}</td>
	            	  </tr>
        		</table>
        		<p >该阿姨当月请假记录</p>
   			</div>	
        <div class="clear"></div>
        <p class="tap">阿姨状态</p>
        <span class="infom setWidth status"><span class="lab">审核状态:{{auntState}}</span></span><br>
        <input type="radio" name="status" value="1" id="status_waiting"><label class="status" for="status_waiting">审核中</label>
        <input type="radio" name="status" value="3" id="status_refuse"><label class="status" for="status_refuse">审核未通过</label>
        <input type="radio" name="status" value="2" id="status_ok"><label class="status" for="status_ok">审核通过</label>
        <button class="sub right" v-on:click = "subState()">Submit</button>
    </div>
    <div class="clear"></div>
</div>
<div class="clear"></div>
    <div class="footer">
         	版权所有：上天入地OneBoom团队
    </div>
</body>
</html>
<script src="Javascript/require.js"></script>
<script src="Javascript/Js/ManagerAuntDetail.js"></script>