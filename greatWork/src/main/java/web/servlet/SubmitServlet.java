package main.java.web.servlet;

import main.java.web.controller.Controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SubmitServlet")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out =response.getWriter();
        //获取管理员id
        String userNumber = request.getParameter("userNumber");
        //获取管理员密码
        String userPassWord = request.getParameter("passWord");
        //获取选择副框的值
        String checkBox = request.getParameter("checkBox");

        Controller controller = new Controller();
        try {
            //判断是否登录成功
            if(controller.submit(userNumber, userPassWord)){//勾选自动登录，创建cookie
                if( checkBox.equals("1")){
                    //新建cookie
                    Cookie cookie1 = new Cookie("adminerName", userNumber);
                    Cookie cookie2 = new Cookie("adminerPassWord", userPassWord);
                    //设置cookie的有效期为一个小时
                    cookie1.setMaxAge(60*60);
                    cookie2.setMaxAge(60*60);
                    //保存cookie到客户端
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    //向ajax的传输值
                    out.print("true");
                }
                else {
                    if(checkBox.equals("2")){
                        out.print("true");
                    }
                    else {
                        request.getRequestDispatcher("Table.do").forward(request, response);
                    }
                }
            }
            else{
                out.print("false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
