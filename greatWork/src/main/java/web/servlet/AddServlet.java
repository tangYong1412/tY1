package main.java.web.servlet;

import main.java.web.controller.Controller;
import main.java.web.model.StudentMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        StudentMessage studentMessage = new StudentMessage();

        studentMessage.setUserNo(request.getParameter("studentNumber"));
        studentMessage.setUserNa(request.getParameter("studentName"));
        studentMessage.setUerAge(request.getParameter("studentAge"));
        studentMessage.setUserSex(request.getParameter("studentSex"));
        studentMessage.setUerPs(request.getParameter("studentPW"));
        studentMessage.setCname(request.getParameter("studentCname"));
        studentMessage.setGrade(request.getParameter("studentGrade"));
        String method =request.getParameter("method");

        Controller controller = new Controller();
        if(method.equals("add")) {
            controller.addStudentMessage(studentMessage);
        }
        else {
            controller.changeStudengMessage(studentMessage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
