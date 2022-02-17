/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import com.ecommerce.zeanstore.ClothesStore.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ZEAN
 */
@Controller
@RequestMapping("/productos")
public class ProductoController{
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/showAll")
    public String show(Model model){
        model.addAttribute("productosTotal",productoService.findAll());
        return "producto/show";
    }
    
    @GetMapping("/create")
    public String create(){
        return "producto/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("Prueba con logger si guarda esta webaad",producto);
        Usuario myUser = new Usuario(1,"","","","","","","");
        producto.setUser(myUser);
        productoService.save(producto);
        return "redirect:/productos";
    }
}
