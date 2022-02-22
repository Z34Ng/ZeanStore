/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ZEAN
 */
@Entity
@Data
@Table(name="orden")
public class Orden implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int  id;
    private String number; //codigo de orden
    private LocalDate createDate;
    private LocalDate reciedDate;
    private double amount;
    
    @ManyToOne
    private Usuario user;
    
    //@OneToOne(mappedBy="orden")
    @OneToMany(mappedBy="orden")
    private List<DetalleOrden> detalles;
    
    public Orden() {
    }

    public Orden(int id, String number, LocalDate createDate, LocalDate reciedDate, double amount) {
        this.id = id;
        this.number = number;
        this.createDate = createDate;
        this.reciedDate = reciedDate;
        this.amount = amount;
    }
}
