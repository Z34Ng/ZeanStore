/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ZEAN
 */

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;    
    //@Column(name="name")
    private String name;    
    //@Column(name="username")
    private String username;    
    //@Column(name="email")
    private String email;    
    //@Column(name="address")
    private String address;    
    //@Column(name="phone")
    private String phone;    
    //@Column(name="tipo")
    private String tipo;    
    //@Column(name="password")
    private String password;
    
    @OneToMany(mappedBy="user")
    private List<Producto> productos; 
    
    @OneToMany(mappedBy="user")
    private List<Orden> ordenes;
    
    public Usuario(int id, String name, String username, String email, String address, String phone, String tipo, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.tipo = tipo;
        this.password = password;
    }
    
    public Usuario(){        
    }
}
