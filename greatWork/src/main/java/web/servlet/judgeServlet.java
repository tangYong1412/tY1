package main.java.web.servlet;

import main.java.web.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "judgeServlet")
public class judgeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户输入的账号
        String userNumber = request.getParameter("userNumber");
        //获取验证的表单
        String tableName = request.getParameter("tableName");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        Controller controller = new Controller();

        try {
            if(controller.checkUserNumber(userNumber, tableName)){
                out.print("<font color='green'>用户名可用</font>");
            }
            else{
                out.print("<font color='red'>用户名已被注册</font>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
