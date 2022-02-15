/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.model;

/**
 *
 * @author ZEAN
 */
public class DetalleOrden {
    private int id;
    private String name;
    private double amount;
    private double prize;
    private double total; 

    public DetalleOrden(int id, String name, double amount, double prize, double total) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.prize = prize;
        this.total = total;
    }

    public DetalleOrden() {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
