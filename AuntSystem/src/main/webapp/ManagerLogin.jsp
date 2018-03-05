<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
 <meta charset="UTF-8">
    <title>Title</title>
  <style type="text/css">
body,html{
  margin: 0;
  padding: 0;
  }
  body{
    width: 80%;
    margin-left: 5%;
  }
  ul{
    list-style: none;
    padding: 0;
    margin: 0;
  }
  a{
    text-decoration: none;
    color: #000;
  }
  .center{
    width: 400px;
    height:200px;
    margin: 0 auto;
    margin-top: 200px;
    border: 2px solid #999;
  }
  .center p{
    text-align: center;
    font-size: 20px;
    font-weight: bold;
  }
  .center .psw,.center .id{
     width: 200px;
     height: 25px;
     line-height: 25px;
     padding-left: 10px;
     
     margin-left: 20px;
     font-size: 20px
  }
  .center .psw{
    margin-top: 20px;
  }
  .center span{
    margin-left: 30px;
  }
  .center div{
    margin-top: 20px;
    text-align: center;
  }
  .center button{
    margin-left: 20px;
  }
</style>
</head>
  <body>
   <div class="center" id="test">
     <p>管理员登陆</p>
     <span>账号:</span><input v-model="name" class="id" type="text" /><br/>
     <span>密码:</span><input v-model="psw" class="psw" type="password"/><br />
     <div><button id="sub">登陆</button></div>
  <a id="a" href="ManagerAunt.jsp" style="display:none"><span></span></a>
</div>

  </body>
</html>
<script src="Javascript/require.js"></script>
     <script src="Javascript/Js/ManagerLogin.js"></script>
