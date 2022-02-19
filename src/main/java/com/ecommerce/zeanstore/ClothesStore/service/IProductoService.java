/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer  id);  //Optional pq puede existir o no
    public void update(Producto producto);
    public void delete(Integer  id);
    public List<Producto> findAll();
}
