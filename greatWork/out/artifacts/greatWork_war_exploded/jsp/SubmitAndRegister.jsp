<%--
  Created by IntelliJ IDEA.
  User: 唐勇1412
  Date: 2018/8/17
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>submitAndRegister</title>
    <link rel="stylesheet" type="text/css" href="../css/SubmitAndRegister.css">
    <script type="text/javascript" src="../js/ajax.js"></script>
    <script src="../jquery/jquery-3.3.1.min.js"></script>

</head>
<body>
<%
    String adminerNumber = null;
    String adminerPassWord = null;
    //获取cookie
    Cookie[] cookies = request.getCookies();
    if(cookies != null){//如果cookies不为空的话遍历一遍
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("adminerName")){
                adminerNumber = cookie.getValue();//获取adminerName的值
            }
            if(cookie.getName().equals("adminerPassWord")){
                adminerPassWord = cookie.getValue();//获取adminerPassWord的值
            }
        }
    }
    //如果存在cookie的话不需要输入用户名和密码自己登陆
    if( adminerNumber != null && adminerPassWord != null){
        request.getRequestDispatcher("../Submit.do?userNumber=" + adminerNumber +"&passWord=" + adminerPassWord + "&checkBox=3").forward(request, response);
    }
%>
        <div id="box">
            <h3 class="titile">学生信息管理系统</h3>
            <form id="submit">
                <h3>登录</h3>
                <div class="user">用户名：<input type="number"  id="userNumber"/></div>
                <div class="passWord">密码： <input type="password"  id="passWord"/></div>
                <div class="check"><input type="checkbox" id="checkBox" value="1" >自动登录</div>
                <input type="button" value="登录" id="btn" onclick="login()"/>
                <a href="/jsp/Register.jsp">没有账号？立即注册</a>
            </form>
        </div>
</body>
</html>
