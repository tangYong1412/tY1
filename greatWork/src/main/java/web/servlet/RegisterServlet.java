package main.java.web.servlet;

import main.java.web.controller.Controller;
import main.java.web.model.Adminer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Adminer adminer = new Adminer();
        //获取账户和密码
        adminer.setAdminNo(request.getParameter("userNumber"));
        adminer.setPassWord(request.getParameter("passWord"));

        Controller controller = new Controller();

        try {
            controller.addAdminer(adminer);
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
