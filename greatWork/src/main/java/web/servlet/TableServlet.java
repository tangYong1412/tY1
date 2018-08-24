package main.java.web.servlet;

import main.java.web.controller.Controller;
import main.java.web.model.StudentMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TableServlet")
public class TableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller controller = new Controller();

        try {
            //获取全部学生的信息
            List<StudentMessage> gradeMessages = controller.getMessages();

            //将数据存入request下
            request.setAttribute("gradeMessages", gradeMessages);
            //携带数据页面跳转
            request.getRequestDispatcher("/jsp/Table.jsp").forward(request,response);
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
