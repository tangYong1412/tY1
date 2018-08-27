package main.java.web.dao;

import main.java.web.Flection.Column;
import main.java.web.model.base.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.web.util.DBUtil.getConnection;

public class EnityDao {
    public static EnityDao enityDao;

    public static EnityDao getEnityDao(){
        if(enityDao == null){
            enityDao = new EnityDao();
        }

        return enityDao;
    }

    //获取数据
    public<T extends Entity> List<T> getMessages(Object object, Class<T> tClass) throws SQLException, ClassNotFoundException {
        //类型转换
        T entity = (T) object;
        //拼接sql语句
        String sql = "SELECT * FROM ";
        sql += entity.getTableName();
        //得到成员变量
        Field[] fields = tClass.getDeclaredFields();

        //得到返回的结果集
        ResultSet rs = select(sql);
        //得到结果集中的数据
        ResultSetMetaData data = rs.getMetaData();
        //创建列表储存数据
        List<T> messages = new ArrayList<T>();

        try {
            //数据库的第一列为1，所以i=1
            while(rs.next()) {
                //实例化
                T message = tClass.newInstance();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //拼接方法名
                    String methodName = "set" + fields[i-1].getName().toUpperCase().charAt(0) + fields[i-1].getName().substring(1);
                    //通过方法名将数据库值出入message
                    message.getClass().getMethod(methodName, String.class).invoke(message, rs.getString(i));
                }
                //将每一行数据存入列表
                messages.add(message);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return messages;
    }

    //得到数据库数据
    public static ResultSet select(String sql) throws SQLException, ClassNotFoundException {
        //建立数据库连接
        Connection connection = getConnection();
        //多次使用sql语句时，PreparedStatement接口在运行时接收参数
        PreparedStatement stmt = connection.prepareStatement(sql);
        //sql语句执行后从数据库查询读取数据，返回数据放在结果集中
        return stmt.executeQuery(sql);
    }

    //管理员注册、学生信息添加
    public<T extends Entity> void add(Object object, Class<T> tClass) throws SQLException, ClassNotFoundException {
        //类型转换
        T entity = (T) object;
        //获得object的class对象》》为了可以使用反射调用方法
        Class<?> t = entity.getClass();
        //获取tClass的成员变量，用于后面的拼接
        Field[] fields = tClass.getDeclaredFields();
        Column annotation = null;
        //拼接vaules之前的语句
        String sql  = "INSERT INTO " + entity.getTableName() +"(";
        //拼接列表名
        for(int i=0;i< fields.length; i++){
            annotation = fields[i].getAnnotation(Column.class);
            if(i == 0){
                sql += annotation.value();
                continue;
            }
            sql += ", " + annotation.value();
        }
        sql += ") VALUES(";
        for(int i=0; i<fields.length ;i++){
            //拼接方法名
            String methodName = "get" + fields[i].getName().toUpperCase().charAt(0) +fields[i].getName().substring(1);
            try {
                //获取通过反射调用的方法
                Method method = t.getMethod(methodName);
                //反射调用方法
                String object1 = (String)method.invoke(entity);
                if(i == 0){
                    if(object1 != null){
                        sql += "'" + object1 + "'";
                    }
                    else{
                        sql +="'0'";
                    }

                    continue;
                }

                if(object1 != null){
                    sql += ", '" + object1 + "'";
                }
                else {
                    sql +=", '0'";
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        sql += ")";

        updateConnection(sql);
    }

    //修改
    public<T extends Entity> void update(Object object, Class<T> tClass) throws SQLException, ClassNotFoundException {
        //强制类型转换
        T entity = (T) object;
        //获取object的class对象，为了通过反射调用方法
        Class<?> t = entity.getClass();
        //获取成员变量
        Field[] fields = tClass.getDeclaredFields();
        //拼接sql语句
        String sql  = "UPDATE " + entity.getTableName() +" SET ";
        //注释
        Column annotation = null;

        try {
            for(int i=1;i< fields.length ;i++){
                //获取列名
                annotation = fields[i].getAnnotation(Column.class);
                String value = annotation.value();
                //获取方法名
                String methodName = "get" + fields[i].getName().toUpperCase().charAt(0) + fields[i].getName().substring(1);
                //反射通过方法名获取方法
                Method method = t.getMethod(methodName);
                //调用方法获取更改值
                String change = (String)method.invoke(entity);
                if(i == 1){
                    if(change != null) {
                        sql += value + " = '" + change +"'";
                    }
                    else{
                        sql += value + " = " + "'0'";
                    }
                    continue;
                }

                if(change != null) {
                    sql += ", " + value + " = '" + change +"'";
                }
                else{
                    sql += ", " + value + " = '" + "0" +"'";
                }
            }
            //获取列名
            annotation = fields[0].getAnnotation(Column.class);
            String value = annotation.value();
            //获取方法名
            String methodName = "get" + fields[0].getName().toUpperCase().charAt(0) + fields[0].getName().substring(1);
            //反射通过方法名获取方法
            Method method = t.getMethod(methodName);
            //调用方法获取更改值
            String change = (String)method.invoke(entity);
            //拼接where语句
            sql += " WHERE " + value + " = '" + change + "';";
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        updateConnection(sql);
    }

    //删除
    public<T extends Entity> void delete(Object object, Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, SQLException, ClassNotFoundException {
        //强制类型转换
        String userNumber = (String) object;
        //拼接sql语句,
        String sql = "DELETE FROM " +  tClass.getMethod("getTableName").invoke(tClass.newInstance()) + " WHERE userNo = '" + userNumber +"'";

        updateConnection(sql);
    }

    //数据库的增删改
    public static void updateConnection(String sql) throws SQLException, ClassNotFoundException {
        //启动数据库链接
        Connection connection = getConnection();
        //创建一个Statement
        Statement stmt = connection.createStatement();
        //执行sql语句
        stmt.executeUpdate(sql);
        //关闭数据库
        connection.close();
    }
}
