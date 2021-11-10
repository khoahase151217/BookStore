/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.product;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {

    private String detailID;
    private String productID;
    private Double price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(String detailID, String productID, Double price, int quantity) {
        this.detailID = detailID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
