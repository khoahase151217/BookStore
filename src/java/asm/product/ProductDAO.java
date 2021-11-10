/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.product;

import asm.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, name, description, price, quantity, categoryID, image "
                        + " FROM tblProducts "
                        + " WHERE name like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("name");
                    String des = rs.getString("description");
                    Double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String cate = rs.getString("categoryID");
                    String img = rs.getString("image");
                    list.add(new ProductDTO(productID, productName, des, price, quantity, img, cate));
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

    public List<Category> getCategory() throws SQLException {
        List<Category> listC = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT categoryID, name "
                        + " FROM tblCategory ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("categoryID");
                    String cateName = rs.getString("name");
                    listC.add(new Category(cateID, cateName));
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
        return listC;
    }

    public List<ProductDTO> getListProductByCID(String cid) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, name, description, price, quantity, categoryID, image "
                        + " FROM tblProducts "
                        + " WHERE categoryID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, cid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("name");
                    String des = rs.getString("description");
                    Double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String cate = rs.getString("categoryID");
                    String img = rs.getString("image");
                    list.add(new ProductDTO(productID, productName, des, price, quantity, img, cate));
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

    public List<ProductDTO> getProductQuantity() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT productID, quantity "
                        + " FROM tblProducts ";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = "";
                    String des = "";
                    Double price = 0.0;
                    int quantity = rs.getInt("quantity");
                    String cate = "";
                    String img = "";
                    list.add(new ProductDTO(productID, productName, des, price, quantity, img, cate));
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

    public boolean updateQuantity(int quantity, String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblProducts "
                        + " SET quantity=? "
                        + " WHERE productID=? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, productID);

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

}
