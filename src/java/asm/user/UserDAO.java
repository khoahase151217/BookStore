/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import asm.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT fullName, roleID, statusID, phone, address, createDate "
                        + " FROM tblUsers "
                        + " WHERE userID=? AND password=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String statusID = rs.getString("statusID");
                    String phone= rs.getString("phone");
                    String address= rs.getString("address");
                    String createDate= rs.getString("createDate");

                    user = new UserDTO(userID, fullName, roleID, "***", phone, address, statusID, createDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
    public UserDTO checkLogin1(String userID)throws SQLException, ClassNotFoundException{
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT fullName, roleID, statusID, phone, address, createDate "
                        + " FROM tblUsers "
                        + " WHERE userID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String statusID = rs.getString("statusID");
                    String phone= rs.getString("phone");
                    String address= rs.getString("address");
                    String createDate= rs.getString("createDate");

                    user = new UserDTO(userID, fullName, roleID, "***", phone, address, statusID, createDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, fullName, roleID, phone, address, statusID, createDate "
                        + " FROM tblUsers "
                        + " WHERE fullName like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String statusID = rs.getString("statusID");
                    String createDate = rs.getString("createDate");
                    String password = "***";
                    list.add(new UserDTO(userID, fullName, roleID, password, phone, address, statusID, createDate));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteUser(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE tblUsers "
                        + " WHERE userID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                result = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblUsers "
                        + " SET fullName=? , roleID=?, phone=?, address=?, statusID=? "
                        + " WHERE userID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getRoleID());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getAddress());
                ps.setString(5, user.getStatusID());
                ps.setString(6, user.getUserID());

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID "
                        + " FROM tblUsers "
                        + " WHERE userID=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertUserNew(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUsers( fullName, userID, password, roleID, phone, address, statusID, createDate ) "
                        + " VALUES(?,?,?,?,?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getUserID());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getRoleID());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getAddress());
                ps.setString(7, user.getStatusID());
                ps.setString(8, user.getCreateDate());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
