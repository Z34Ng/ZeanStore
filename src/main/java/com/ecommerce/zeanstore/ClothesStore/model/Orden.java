/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ZEAN
 */
@Entity
@Table(name="orden")
public class Orden implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int  id;
    private String number;
    private LocalDate createDate;
    private LocalDate reciedDate;
    private double amount;
    
    @ManyToOne
    private Usuario user;
    
    @OneToOne(mappedBy="orden")
    private DetalleOrden detalle;

    public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario usuario) {
        this.user = usuario;
    }

    public DetalleOrden getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleOrden detalle) {
        this.detalle = detalle;
    }

    public Orden() {
    }

    public Orden(int id, String number, LocalDate createDate, LocalDate reciedDate, double total) {
        this.id = id;
        this.number = number;
        this.createDate = createDate;
        this.reciedDate = reciedDate;
        this.amount = total;
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
        return amount;
    }

    public void setTotal(double total) {
        this.amount = total;
    }
    
}
