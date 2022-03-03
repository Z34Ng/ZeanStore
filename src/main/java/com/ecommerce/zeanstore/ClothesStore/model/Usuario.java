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
    //@Column(name="email")
    private String email;    
    //@Column(name="address")
    private String address;    
    //@Column(name="phone")
    private String phone;    
    //@Column(name="typeUser")
    private String typeUser;    
    //@Column(name="password")
    private String password;
    
    @OneToMany(mappedBy="user")//atributo de clase Producto
    private List<Producto> productos; 
    
    @OneToMany(mappedBy="user")
    private List<Orden> ordenes;
    
    public Usuario(int id, String name, String email, String address, String phone, String typeUser, String password) {
        this.id = id;
        this.name = name;        
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.typeUser = typeUser;
        this.password = password;
    }
    
    public Usuario(){        
    }
}
