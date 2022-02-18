/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ZEAN
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String home(Model model){
        
        model.addAttribute("productos",productoService.findAll());
        return "usuario/home";
    }
    
    @GetMapping("productoHome/{id}")
    public String productoHome(@PathVariable int id, Model model){
        Producto producto=productoService.get(id).get();
        model.addAttribute("producto",producto);
        return "usuario/productohome";
    }
}
