package main.java.web.servlet;

import main.java.web.controller.Controller;
import main.java.web.model.Adminer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SubmitServlet")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录的信息
        Adminer adminer = new Adminer();
        adminer.setAdminNo(request.getParameter("userNumber"));
        adminer.setPassWord(request.getParameter("passWord"));
        String checkBox = request.getParameter("checkBox");

        Controller controller  = new Controller();
        boolean result = controller.login(adminer);

        //判断登录是否成功
        if(result){
            if(checkBox.equals("1")){
                //创建cookie
                Cookie cookie1 = new Cookie("adminerName", adminer.getAdminNo());//账户
                Cookie cookie2 = new Cookie("adminerPassWord", adminer.getPassWord());//密码
                //设置cookie的有效期为一个小时
                cookie1.setMaxAge(60*60);
                cookie2.setMaxAge(60*60);
                //保存cookie到客户端
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                //第一次选择自动登录 >> 向ajax传值
                response.getWriter().print("true");
            }
            else
            {
                if(checkBox.equals("2")){
                    response.getWriter().print("true");
                }
                else {
                    request.getRequestDispatcher("Table.do").forward(request, response);
                }
            }
        }
        else{//登录失败
            response.getWriter().print("false");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
