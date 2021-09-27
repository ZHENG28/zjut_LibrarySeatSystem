package com.librarySystem.Demo.oldServlet.dao;

import entity.Manager;
import entity.Student;
import entity.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    private UserDAO() {}

    public static User login(Integer identity, String sno, String password)
    {
        // identity: 0-manager 1-student 2-teacher
        String sql = "";
        User user = null;
        if (identity == 1) { // student
            sql = "select * from t_studentinfo where SNO=? and PSWD=?";
        } else if (identity == 0) { // manager
            sql = "";
            return null;
        } else {
            return null;
        }

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (identity == 1) { // student
                    String sname = rs.getString("SNAME");
                    int gender = rs.getInt("GENDER");
                    String campus = rs.getString("CAMPUS");
                    String birth = rs.getString("BIRTHDAY");
                    int occupyseat = rs.getInt("OCCUPYSEAT");
                    int state = rs.getInt("STATE");
                    String idadress = rs.getString("IDADRESS");
                    int violatetime = rs.getInt("VIOLATETIME");
                    user = new Student(sno, sname, password, gender, campus, birth, occupyseat, state, violatetime,idadress);
                    user.setIdentity(identity);
                } else if (identity == 0) { // manager
                    user = new Manager();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return user;
    }


    public static int modifyUser(Integer gender, String campus, String birthday, String sno)
    {
        String sql = "update t_studentinfo set GENDER=?, CAMPUS=?, BIRTHDAY=? where SNO=?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, gender);
            ps.setString(2, campus);
            ps.setString(3, birthday);
            ps.setString(4, sno);
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return result;
    }

    public static int modifyPwd(String sno, String oldPwd, String newPwd)
    {
        String sql = "update t_studentinfo set PSWD = ? where SNO = ? and PSWD = ?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPwd);
            ps.setString(2, sno);
            ps.setString(3, oldPwd);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return result;
    }
}
