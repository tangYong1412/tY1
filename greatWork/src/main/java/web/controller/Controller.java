package main.java.web.controller;

import main.java.web.dao.EnityDao;
import main.java.web.model.Adminer;
import main.java.web.model.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    EnityDao enityDao = EnityDao.getEnityDao();

    //登录
    public boolean login(Adminer adminer){
        List<Adminer> messages = new ArrayList<Adminer>();

        try {
            messages = enityDao.getMessages(adminer, Adminer.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //循环判断该用户名和密码是否存在
        for(int i=0; i<messages.size(); i++){
            if(messages.get(i).getAdminNo().equals(adminer.getAdminNo()) && messages.get(i).getPassWord().equals(adminer.getPassWord())){
                return true;
            }
        }

        return false;
    }

    //管理员注册
    public void addAdminer(Adminer adminer) throws SQLException, ClassNotFoundException {
        enityDao.add(adminer, Adminer.class);
    }

    //学生添加和数据更新
    public void upateStudnet(Student student, String method) throws SQLException, ClassNotFoundException {
        if(method.equals("add")){
            enityDao.add(student, Student.class);
        }
        else{
            enityDao.update(student, Student.class);
        }
    }

    //学生数据删除
    public void delete(String userNumber) throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        enityDao.delete(userNumber, Student.class);
    }

    //查看学生信息
    public List<Student> getStudentMessage() throws SQLException, ClassNotFoundException {
        return enityDao.getMessages(new Student(), Student.class);
    }

    //验证管理员账号或学号是否存在0
    public boolean checkUserNumber(String userNumber, String tableName) {
          if(tableName.equals("admin")){
              List<Adminer> lsit1 = new ArrayList<Adminer>();
              Adminer adminer = new Adminer();
              adminer.setAdminNo(userNumber);
              try {
                  lsit1 = enityDao.getMessages(adminer , Adminer.class);
              } catch (SQLException e) {
                  e.printStackTrace();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }

              //循环判断该用户名是否存在
              for(int i=0; i<lsit1.size() ;i++){
                  if(lsit1.get(i).getAdminNo().equals(adminer.getAdminNo())){
                      return false;
                  }
              }

              return true;
          }
          else{
              List<Student> lsit2 = new ArrayList<Student>();
              Student student = new Student();
              student.setUserNo(userNumber);

              try {
                  lsit2 = enityDao.getMessages(student , Student.class);
              } catch (SQLException e) {
                  e.printStackTrace();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }

              //循环判断该用户名是否存在
              for(int i=0; i<lsit2.size() ;i++){
                  if(lsit2.get(i).getUserNo().equals(student.getUserNo())){
                      return false;
                  }
              }

              return true;
          }
    }
}
