/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author thuy
 */
public class Cart {

    private ArrayList<Order> cart;

    public Cart(ArrayList<Order> cart) {
        this.cart = cart;
    }

    public ArrayList<Order> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Order> cart) {
        this.cart = cart;
    }

}
