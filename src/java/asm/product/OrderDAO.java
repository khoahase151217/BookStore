/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.product;

import asm.user.UserDTO;
import asm.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {

    public String getOrderID() throws SQLException {
        String result = "";
        int count = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT orderID FROM tblOrders ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }
            result = "O" + count;
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
        return result;
    }

    public boolean insertOder(UserDTO user, Order order) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblOrders( orderID, userName, userID, date, total, address, phone, paymentStatus ) "
                        + " VALUES(?,?,?,?,?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOrderID());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getUserID());
                ps.setString(4, order.getDate());
                ps.setDouble(5, order.getTotal());
                ps.setString(6, user.getAddress());
                ps.setString(7, user.getPhone());
                ps.setString(8, order.getPaymentStatus());
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

    public String getDetailID() throws SQLException {
        String result = "";
        int count = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT detailID FROM tblOrderDetail ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }
            result = "D" + count;
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
        return result;
    }

    public boolean insertOderDetail(Order order, OrderDetail detail) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblOrderDetail( detailID, orderID, productID, price, quantity ) "
                        + " VALUES(?,?,?,?,?) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, detail.getDetailID());
                ps.setString(2, order.getOrderID());
                ps.setString(3, detail.getProductID());
                ps.setDouble(4, detail.getPrice());
                ps.setDouble(5, detail.getQuantity());
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
