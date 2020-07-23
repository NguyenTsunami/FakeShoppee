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
public class Classify {

    private int id;
    private String name;
    private ArrayList<Item> list;

    public Classify() {
        list = new ArrayList<>();
    }

    public Classify(int id, String name, ArrayList<Item> list) {
        this.id = id;
        this.name = name;
        this.list = list;
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

    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return name;
    }

}
