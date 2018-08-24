<%--
  Created by IntelliJ IDEA.
  User: 唐勇1412
  Date: 2018/8/20
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>register</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <script type="text/javascript" src="../js/Register.js"></script>
    <script src="../jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
        <form>
            <h3>注册</h3>
            <div class="user">用户名：<input type="number" id="userNumber" onblur="checkNumber(this.value);"/></div>
            <span id="nameTipMsg"></span>
            <div class="pS">密码：<input type="password" id="passWord" onblur="checkPs(this.value)"/></div>
            <span id="nameTipMs"></span>
            <div class="ps">请再次输入：<input type="password" id="passWord1"/></div>
            <input type="button" value="注册" id="btn" onclick="register();"/>
        </form>
</body>
</html>
