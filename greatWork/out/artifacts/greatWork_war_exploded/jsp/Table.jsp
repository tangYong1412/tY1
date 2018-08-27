<%--
  Created by IntelliJ IDEA.
  User: 唐勇1412
  Date: 2018/8/14
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.web.model.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    List<Student> gradeMessage =  (List<Student>)request.getAttribute("gradeMessages");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table</title>
        <link rel="stylesheet" type="text/css" href="../css/Table.css">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/Delete.js"></script>
    </head>

    <body>
        <div id="bigBox">
            <div id="add">
                <div id="back1">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times</a>
                        <h3  class="studentM"><p>学生信息添加系统</p></h3>
                        <form>
                        <div class="in"><p>学号：</p><input type="number" id="studentNumber" onblur="check(this.value)"/></div>
                        <span id="nameTipMsg"></span>
                        <div class="in"><p>姓名：</p><input type="text" id="studentName" onblur="checkName(this.value)"/></div>
                        <span class="nameTipMsg1"></span>
                        <div class="in"><p>年龄：</p><input type="number" id="studentAge" onblur="checkAge(this.value)"/></div>
                        <span class="nameTipMsg2"></span>
                        <div class="in"><p>性别：</p><input type="text" id="studentSex" onblur="checkSex(this.value)"/></div>
                        <span class="nameTipMsg3"></span>
                        <div class="in"><p>密码：</p><input type="text" id="studentPW" onblur="checkPw(this.value)"/></div>
                        <span class="nameTipMsg4"></span>
                        <div class="in"><p>学科：</p><input type="text" id="cName" onblur="checkcName(this.value)"/></div>
                        <span class="nameTipMsg5"></span>
                        <div class="in"><p>成绩：</p><input type="number" id="grade" onblur="checkGrade(this.value)"/></div>
                        <span class="nameTipMsg6"></span>
                        <input type="button" onclick="add()" class="btn" value="添加"/>
                    </form>
                </div>
        </div>
            <div id="update">
                <div id="back2">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav1()">&times</a>
                    <h3 class="studentM"><p>学生信息修改系统</p></h3>
                    <form>
                        <div class="in"><p>学号：</p><input type="number" id="studentNu" onfocus=this.blur()></div>
                        <div class="in"><p>姓名：</p><input type="text" id="studentNa" onblur="checKName(this.value)"/></div>
                        <span class="nameTipMsga1"></span>
                        <div class="in"><p>年龄：</p><input type="text" id="studentA" onblur="checKAge(this.value)"/></div>
                        <span class="nameTipMsga2"></span>
                        <div class="in"><p>性别：</p><input type="text" id="studentS" onblur="checKSex(this.value)"/></div>
                        <span class="nameTipMsga3"></span>
                        <div class="in"><p>密码：</p><input type="text" id="studentP" onblur="checKPw(this.value)"/></div>
                        <span class="nameTipMsga4"></span>
                        <div class="in"><p>学科：</p><input type="text" id="textName" onblur="checKcName(this.value)"/></div>
                        <span class="nameTipMsga5"></span>
                        <div class="in"><p>成绩：</p><input type="text" id="textGrade" onblur="checKGrade(this.value)"/></div>
                        <span class="nameTipMsga6"></span>
                        <input type="button" onclick="update()" class="btn" value="修改"/>
                    </form>
                </div>
            </div>
            <span class="more" onclick="openNav()">&#9776:新增</span>
            <div id="box">
                <h3><p>学生基本信息</p></h3>
                <table class="myTable" class="table table-bordered table-condensed">
                <tr>
                    <td class="titile">学号</td>
                    <td class="titile">姓名</td>
                    <td class="titile">年龄</td>
                    <td class="titile">性别</td>
                    <td class="titile">密码</td>
                    <td class="titile">学科名</td>
                    <td class="titile">成绩</td>
                    <td class="titile">操作</td>
                    <td class="titile">操作</td>
                </tr>
                <%
                    for(int i = 0; i<gradeMessage.size(); i++){ %>
                        <tr>
                            <td class="body"><%=gradeMessage.get(i).getUserNo()%></td>
                            <td class="body"><%=gradeMessage.get(i).getUserNa()%></td>
                            <td class="body"><%=gradeMessage.get(i).getUserAge()%></td>
                            <td class="body"><%=gradeMessage.get(i).getUserSex()%></td>
                            <td class="body"><%=gradeMessage.get(i).getUserPs()%></td>
                            <td class="body"><%=gradeMessage.get(i).getCname()%></td>
                            <td class="body"><%=gradeMessage.get(i).getGrade()%></td>
                            <td class="body"><span class="btn1" onclick="openNav1(<%=gradeMessage.get(i).getUserNo()%>)">修改</span></td>
                            <td class="body"><span class="btn1" onclick="deleTe(<%=gradeMessage.get(i).getUserNo()%>)">删除</span> </td>
                        </tr>
                <% }%>
            </table>
            </div>
        </div>
    </body>
</html>
