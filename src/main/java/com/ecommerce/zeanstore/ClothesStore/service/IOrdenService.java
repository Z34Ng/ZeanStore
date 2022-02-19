/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Orden;
import java.util.List;

/**
 *
 * @author ZEAN
 */
public interface IOrdenService {    
    List<Orden> findAll();
    Orden save(Orden o); //retorna una orden que se guarda
    String getNumeroOrden();
}
