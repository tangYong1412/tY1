package main.java.web.servlet;

import main.java.web.controller.Controller;
import main.java.web.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        Student student = new Student();

        student.setUserNo(request.getParameter("studentNumber"));
        student.setUserNa(request.getParameter("studentName"));
        student.setUserAge(request.getParameter("studentAge"));
        student.setUserSex(request.getParameter("studentSex"));
        student.setUserPs(request.getParameter("studentPW"));
        student.setCname(request.getParameter("studentCname"));
        student.setGrade(request.getParameter("studentGrade"));
        String method =request.getParameter("method");

        Controller controller = new Controller();

        try {
            controller.upateStudnet(student, method);
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
