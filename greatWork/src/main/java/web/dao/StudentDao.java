package main.java.web.dao;

import main.java.web.model.Adminer;
import main.java.web.model.StudentMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static main.java.web.util.DBUtil.getConnection;

public class StudentDao {
    private static StudentDao studentDao;

    public static StudentDao getStudentDao(){
        if(studentDao == null){
            studentDao = new StudentDao();
        }

        return studentDao;
    }

    //管理员登陆
    public boolean submit(String userNumber, String passWord) throws SQLException, ClassNotFoundException {
            List<Adminer> adminerMessages = getAdminerMessage();

        for(int i=0; i< adminerMessages.size(); i++){
            if(adminerMessages.get(i).getAdmiNo().equals(userNumber)&&adminerMessages.get(i).getPassWord().equals(passWord)){
                return true;
            }
        }

        return false;
    }

    //管理员注册
    public void addAdminerMessage(String userNumber, String passWord){
        String sql = "INSERT INTO admin (admiNo, passWord) VALUES('" + userNumber +"', '" + passWord + "')";
        addOrUpdateOrDelete(sql);
    }

    //验证管理员用户是否存在
    public boolean checkUserNumber (String adminerNumber) throws SQLException, ClassNotFoundException {
        List<Adminer> adminerMessages = getAdminerMessage();

        for(int i=0; i<adminerMessages.size() ;i++){
            if(adminerMessages.get(i).getAdmiNo().equals(adminerNumber)){
                return false;
            }
        }

        return true;
    }

    //获取管理员信息
    public static List<Adminer> getAdminerMessage () throws SQLException, ClassNotFoundException {
        //操作指令
        String sql = "SELECT * FROM admin" ;
        //executeQuery用于产生单个结果集的语句，例如SELECT语句
        ResultSet rs = select(sql);
        //getMetaData()得到结果集的结构
        ResultSetMetaData data = rs.getMetaData();
        //创建arryList存放从数据库读取的数据
        List<Adminer> adminerMessages = new ArrayList<Adminer>();

        while(rs.next()){
            Adminer adminer = new Adminer();
            //数据库的第一列为1，而不是零，所以i应该为1
            for(int i=1; i<= data.getColumnCount(); i++){
                //获得指定列的数据
                String columnValue = rs.getString(i);
                switch (i) {
                    //管理员账号
                    case 1: {
                        adminer.setAdmiNo(columnValue);
                        break;
                    }
                    //第二列数据
                    case 2: {
                        adminer.setPassWord(columnValue);
                        break;
                    }
                }
            }
            adminerMessages.add(adminer);
        }
        return adminerMessages;
    }

    //验证学号是否存在
    public boolean checkStudentNumber(String studentNumber) throws SQLException, ClassNotFoundException {
        StudentDao studentDao = new StudentDao();
        //获取学生信息
        List<StudentMessage> studentMessages = studentDao.geStudentMessages();

        for(int i=0; i<studentMessages.size() ;i++){
            if(studentMessages.get(i).getUserNo().equals(studentNumber)){
                return false;
            }
        }

        return true;
    }

    //获取学生的信息
    public List<StudentMessage> geStudentMessages() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student";
        //executeQuery用于产生单个结果集的语句
        ResultSet rs = select(sql);
        //得到结果集的数据
        ResultSetMetaData data = rs.getMetaData();
        //将数据存入列表中
        List<StudentMessage> studentMessages = new ArrayList<StudentMessage>();

        while (rs.next()){
            StudentMessage messages = new StudentMessage();
            for(int i=1; i<= data.getColumnCount(); i++){
                String columnValue = rs.getString(i);
                switch (i){
                    case 1:{
                        messages.setUserNo(columnValue); break;
                    }
                    case 2:{
                        messages.setUserNa(columnValue); break;
                    }
                    case 3:{
                        messages.setUerAge(columnValue); break;
                    }
                    case 4:{
                        messages.setUserSex(columnValue); break;
                    }
                    case 5:{
                        messages.setUerPs(columnValue); break;
                    }
                    case 6:{
                        messages.setCname(columnValue); break;
                    }
                    case 7:{
                        messages.setGrade(columnValue); break;
                    }
                }
            }
            studentMessages.add(messages);
        }

        return studentMessages;
    }

    //查询信息
    public static ResultSet select(String sql) throws SQLException, ClassNotFoundException {
        //启动数据库连接
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);

        return stmt.executeQuery(sql);
    }

    //添加学生信息
    public void addStudentMessage(StudentMessage studentMessage){
        String sql = "INSERT INTO student (userNo, userNa, userAge, userSex, userPs, Cname, Grade) VALUES('"
                + studentMessage.getUserNo() + "', '" + studentMessage.getUserNa() + "', " + studentMessage.getUerAge()+ ", '"
                + studentMessage.getUserSex() + "', '" + studentMessage.getUerPs() + "', '" + studentMessage.getCname() + "', "
                + studentMessage.getGrade() + ")";

        addOrUpdateOrDelete(sql);
    }

    //更改学生信息
    public void changeStudentMessage(StudentMessage studentMessage){
        String sql = "UPDATE student SET ";
        if(studentMessage.getUserNa() != ""){
            sql += "userNa='" + studentMessage.getUserNa() + "'";
        }
        if(studentMessage.getUerAge() != ""){
            sql += ", userAge='" + studentMessage.getUerAge() + "'";
        }
        if(studentMessage.getUserSex() != ""){
            sql += ", userSex='" +studentMessage.getUserSex() + "'";
        }
        if(studentMessage.getUerPs() != ""){
            sql += ", userPs='" +studentMessage.getUerPs() + "'";
        }
        if(studentMessage.getUerPs() != ""){
            sql += ", Cname='" + studentMessage.getCname() + "'";
        }
        if(studentMessage.getGrade() != ""){
            sql += ", Grade='" +studentMessage.getGrade() + "'";
        }

        sql += " WHERE userNo='" + studentMessage.getUserNo() + "';";

        addOrUpdateOrDelete(sql);
    }

    //删除学生信息
    public void deleteStudentMessage(String studentNumber){
            String sql = "DELETE FROM student WHERE userNo = " + studentNumber + "";

            addOrUpdateOrDelete(sql);
    }

    //管理员注册、添加学生信息或更改学生信息
    public static void addOrUpdateOrDelete(String sql){
        try {
            //启动数据库连接
            Connection connection = getConnection();;
            //创建一个Statement
            Statement stmt = connection.createStatement();
            //执行sql语句
            stmt.executeUpdate(sql);
            //关闭数据库
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
