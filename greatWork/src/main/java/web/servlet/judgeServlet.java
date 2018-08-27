package main.java.web.servlet;

import main.java.web.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        boolean result = controller.checkUserNumber(userNumber, tableName);

        if(result){
            out.print("<font color='green'>用户名可用</font>");
        }
        else{
            out.print("<font color='red'>用户名已被注册</font>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
