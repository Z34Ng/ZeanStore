/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.DetalleOrden;
import com.ecommerce.zeanstore.ClothesStore.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ZEAN
 */
@Service
public class IDetalleOrdenServiceImpl implements IDetalleOrdenService{
    
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }    
    
}
