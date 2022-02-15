/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.model;

import java.time.LocalDate;

/**
 *
 * @author ZEAN
 */
public class Orden {
    private int  id;
    private String number;
    private LocalDate createDate;
    private LocalDate reciedDate;
    private double total;

    public Orden() {
    }

    public Orden(int id, String number, LocalDate createDate, LocalDate reciedDate, double total) {
        this.id = id;
        this.number = number;
        this.createDate = createDate;
        this.reciedDate = reciedDate;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getReciedDate() {
        return reciedDate;
    }

    public void setReciedDate(LocalDate reciedDate) {
        this.reciedDate = reciedDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
