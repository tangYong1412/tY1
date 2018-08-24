package main.java.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //加载数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");
        //建立连接
        String userName = "root";//数据库id
        String passWord = "akmm1412";//数据库密码
        String url = "jdbc:mysql://localhost:3306/s?useUnicode=true&characterEncoding=UTF-8";//连接协议+数据库地址+数据库名称
        //连接数据库
        Connection connection = DriverManager.getConnection(url, userName, passWord);

        return connection;
    }
}
