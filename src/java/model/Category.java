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
public class Category {

    private int id;
    private String name;
    private ArrayList<Brand> brandList;

    public Category() {
        brandList = new ArrayList<>();
    }

    public Category(int id, String name, ArrayList<Brand> brandList) {
        this.id = id;
        this.name = name;
        this.brandList = brandList;
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

    public ArrayList<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(ArrayList<Brand> brandList) {
        this.brandList = brandList;
    }

}
