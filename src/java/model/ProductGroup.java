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
public class ProductGroup {

    private int id;
    private String name;
    private Shop shop;
    private Category category;
    private String description;
    private Brand brand;
    private Classify classify1;
    private Classify classify2;
    private double sale;
    private int rating;
    private String state;
    double cost;
    private ArrayList<String> images;

    public ProductGroup() {
    }

    public ProductGroup(int id, String name, Shop shop, Category category, 
            String description, Brand brand, Classify classify1, Classify classify2, 
            double sale, int rating, String state, double cost, ArrayList<String> images) {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.category = category;
        this.description = description;
        this.brand = brand;
        this.classify1 = classify1;
        this.classify2 = classify2;
        this.sale = sale;
        this.rating = rating;
        this.state = state;
        this.cost = cost;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Classify getClassify1() {
        return classify1;
    }

    public void setClassify1(Classify classify1) {
        this.classify1 = classify1;
    }

    public Classify getClassify2() {
        return classify2;
    }

    public void setClassify2(Classify classify2) {
        this.classify2 = classify2;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
