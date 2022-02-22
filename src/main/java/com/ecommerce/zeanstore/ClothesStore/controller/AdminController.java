/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.zeanstore.ClothesStore.service.IProductoService;
import com.ecommerce.zeanstore.ClothesStore.service.IUsuarioService;

@Controller
@RequestMapping("/administrador")   
public class AdminController {
    
    @Autowired 
    private IProductoService productoService;
    
    @Autowired 
    private IUsuarioService usuarioService;
    
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos",productos);
        return "administrador/home";    
    }
    
    @GetMapping("/getUsersList")
    public String getListUser(Model model){
        
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }
}
