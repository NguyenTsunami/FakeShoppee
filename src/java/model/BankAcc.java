/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thuy
 */
public class BankAcc {

    int id;
    String holder;
    String number;
    String expiration;
    int CVV;
    String bank;

    public BankAcc() {
    }

    public BankAcc(int id, String holder, String number, String expiration, int CVV, String bank) {
        this.id = id;
        this.holder = holder;
        this.number = number;
        this.expiration = expiration;
        this.CVV = CVV;
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

}
