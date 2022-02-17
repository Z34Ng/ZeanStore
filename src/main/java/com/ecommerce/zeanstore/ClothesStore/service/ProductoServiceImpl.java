/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.repository.ProductoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service //para inyectar al contraldor  los metodos
public class ProductoServiceImpl implements ProductoService{

    @Autowired //inyecta a la clase este objeto
    private ProductoRepository productoRepository;
    
    @Override
    public Producto save(Producto producto){
        return productoRepository.save(producto);        
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);        
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
    
}
