package com.librarySystem.Demo.oldServlet.util;

import java.sql.*;

public class JDBCUtil
{
    static final String URL = "jdbc:mysql://localhost:3306/library_seat_selection?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "xyshu123";

    // 因为工具类当中的方法都是静态的，不需要new对象，一般都直接采用类名调用
    private JDBCUtil() {}

    // 静态代码块，只执行一次
    // 注册驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // 释放资源
    public static void close(Connection conn, Statement stmt, ResultSet rs)
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void beginTransaction(Connection conn)
    {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commitTransaction(Connection conn)
    {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackTransaction(Connection conn)
    {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void endTransaction(Connection conn)
    {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
