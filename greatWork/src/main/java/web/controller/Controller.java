package main.java.web.controller;

import main.java.web.dao.StudentDao;
import main.java.web.model.Adminer;
import main.java.web.model.StudentMessage;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    StudentDao studentDao = StudentDao.getStudentDao();

    public Controller(){
    }

    //登陆
    public boolean submit(String userNo, String passWord) throws SQLException, ClassNotFoundException {
        if(studentDao.submit(userNo, passWord)){
            return true;
    }
        else {
            return false;
        }
    }

/*    //注册>>增加管理员
    public void register(){
        Controller controller = new Controller();

        controller.addAOrSMessage("admin");
    }*/

    //验证管理员账号或学号是否存在
    public boolean checkUserNumber(String userNumber, String tableName) throws SQLException, ClassNotFoundException {
        if(tableName.equals("admin")) {
            if (studentDao.checkUserNumber(userNumber)) {
                return true;
            } else {
                return false;
            }
        }
        else {
            if (studentDao.checkStudentNumber(userNumber)) {
                return true;
            } else {
                return false;
            }
        }
    }

    //添加管理员
    public void addAdminerMessage(String userNumber, String passWord){
        studentDao.addAdminerMessage(userNumber, passWord);
    }

    //添加学生
    public void addStudentMessage(StudentMessage studentMessage){
        studentDao.addStudentMessage(studentMessage);
    }

    //查询全部学生的信息
    public List<StudentMessage> getMessages() throws SQLException, ClassNotFoundException {
        StudentDao studentDao = StudentDao.getStudentDao();

        return studentDao.geStudentMessages();
    }

    //删除指定学号的学生信息
    public void deleteStudentMessage(String studengNumber){
        studentDao.deleteStudentMessage(studengNumber);
    }

    //修改指定学号的学生信息
    public void changeStudengMessage(StudentMessage studentMessage) {
        studentDao.changeStudentMessage(studentMessage);
    }
}
