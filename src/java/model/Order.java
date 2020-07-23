/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.smartcardio.Card;

/**
 *
 * @author thuy
 */
public class Order {

    int id;
    Product product;
    String date;
    int quantity;
    String state;
    Card card;
    Address address;
    String img;

    public Order() {
    }

    public Order(int id, Product product, String date, int quantity, String state, Card card, Address address, String img) {
        this.id = id;
        this.product = product;
        this.date = date;
        this.quantity = quantity;
        this.state = state;
        this.card = card;
        this.address = address;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
