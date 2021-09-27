package com.librarySystem.Demo.oldServlet.dao;

import entity.Desk;
import entity.Student;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatDAO
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    private SeatDAO() {}

    public static boolean bookSeat(String sno, String date, String campus, Integer floor, Integer deskno)
    {
        String sql = "select * from t_seatinfo where CAMPUS=? and FLOOR=? and DESKNO=?";
        boolean success = true;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            ps = conn.prepareStatement(sql);
            ps.setString(1, campus);
            ps.setInt(2, floor);
            ps.setInt(3, deskno);
            rs = ps.executeQuery();
            if (rs.next()) {
                int seatId = rs.getInt("seatid");

                // 选座成功后的操作：
                // occupynum++，更新网页上的显示
                sql = "update t_seatinfo set OCCUPYNUM=OCCUPYNUM+1 where SEATID=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, seatId);
//                ps.addBatch();
                if (ps.executeUpdate() != 1) {
                    throw new SQLException("更新" + seatId + "桌子的占座人数失败");
                }

                // 个人信息的修改——占座桌号，占座状态
                sql = "update t_studentinfo set STATE = 1, OCCUPYSEAT = ? where SNO = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, seatId);
                ps.setString(2, sno);
//                ps.addBatch();
                if (ps.executeUpdate() != 1) {
                    throw new SQLException("更新个人信息失败");
                }

                // 新增历史记录
                sql = "insert into t_historyrecord(sno, occupyseat, reservation) values(?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, sno);
                ps.setInt(2, seatId);
                ps.setString(3, date);
//                ps.addBatch();
                if (ps.executeUpdate() != 1) {
                    throw new SQLException("添加选座历史记录失败");
                }

//                ps.executeBatch();
                JDBCUtil.commitTransaction(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
            JDBCUtil.rollbackTransaction(conn);
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, ps, rs);
        }
        return success;
    }

    public static Student findByStuId(String sno)
    {
        String sql = "select * from t_studentinfo where SNO = ?";
        Student stu = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sname = rs.getString("sname");
                String pwd = rs.getString("pswd");
                int gender = rs.getInt("gender");
                String campus = rs.getString("campus");
                String birthday = rs.getString("birthday");
                int seatid = rs.getInt("occupyseat");
                int state = rs.getInt("state");
                int violatetime = rs.getInt("violatetime");
                stu = new Student(sno, sname, pwd, gender, campus, birthday, seatid, state, violatetime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return stu;
    }


    public static Desk findByDeskno(String campus, Integer floor, Integer deskno)
    {
        String sql = "select * from t_seatinfo where CAMPUS = ? and FLOOR = ? and DESKNO = ?";
        Desk desk = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, campus);
            ps.setInt(2, floor);
            ps.setInt(3, deskno);
            rs = ps.executeQuery();
            if (rs.next()) {
                int seatId = rs.getInt("seatid");
                int desktype = rs.getInt("desktype");
                int occupynum = rs.getInt("occupynum");
                desk = new Desk(seatId, campus, floor, deskno, desktype, occupynum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return desk;
    }

    public static Desk findBySeatId(int seatId)
    {
        String sql = "select * from t_seatinfo where SEATID = ?";
        Desk desk = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, seatId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String campus = rs.getString("campus");
                int floor = rs.getInt("floor");
                int deskno = rs.getInt("deskno");
                int desktype = rs.getInt("desktype");
                int occupynum = rs.getInt("occupynum");
                desk = new Desk(seatId, campus, floor, deskno, desktype, occupynum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return desk;
    }

    public static List<Map<String, Object>> getSeat(String campus, Integer floors)
    {
        List<Map<String, Object>> seatList = new ArrayList<>();
        String sql = "select * from t_seatinfo where CAMPUS=? and FLOOR=?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, campus);
            ps.setInt(2, floors);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> seatInfo = new HashMap<>();
                int seatId = rs.getInt("SEATID");
                int deskNo = rs.getInt("DESKNO");
                int deskType = rs.getInt("DESKTYPE");
                int occupyNum = rs.getInt("OCCUPYNUM");
                seatInfo.put("seatId", seatId);
                seatInfo.put("deskNo", deskNo);
                seatInfo.put("deskType", deskType);
                seatInfo.put("occupyNum", occupyNum);
                seatList.add(seatInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return seatList;
    }

    public static String getLayout(String campus, Integer floor)
    {
        String layout = "";
        String sql = "select CONTENT from t_layout where CAMPUS=? and FLOOR=?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, campus);
            ps.setInt(2, floor);
            rs = ps.executeQuery();
            if (rs.next()) {
                layout = rs.getString("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return layout;
    }

    public static int findDeskno(String sno)
    {
        String sql = "select s.DESKNO from t_seatinfo s join t_studentinfo stu on s.SEATID = stu.OCCUPYSEAT where stu.SNO=?";
        int deskno = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                deskno = rs.getInt("deskno");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return deskno;
    }
}
