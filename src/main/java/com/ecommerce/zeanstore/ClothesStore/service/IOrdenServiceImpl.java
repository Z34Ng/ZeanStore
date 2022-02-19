/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Orden;
import com.ecommerce.zeanstore.ClothesStore.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service
public class IOrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;
    
    @Override
    public Orden save(Orden o) {
         return ordenRepository.save(o);
    }
    
}
