/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.product;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class ProductDTO implements Serializable {

    private String productID;
    private String productName;
    private String des;
    private double price;
    private int quantity;
    private String img;
    private String categoryID;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, String des, double price, int quantity, String img, String categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.des = des;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.categoryID = categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
