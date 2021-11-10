/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class Cart implements Serializable {

    private Map<String, ProductDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public void add(ProductDTO book) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(book.getProductID())) {
            int currentQuantity = this.cart.get(book.getProductID()).getQuantity();
            book.setQuantity(currentQuantity + book.getQuantity());
        }
        cart.put(book.getProductID(), book);
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, ProductDTO newBook) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, newBook);
        }
    }
}
