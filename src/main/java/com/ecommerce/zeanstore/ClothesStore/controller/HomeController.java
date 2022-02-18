/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.DetalleOrden;
import com.ecommerce.zeanstore.ClothesStore.model.Orden;
import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ZEAN
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService;
    
    //alamacena los detalles de cada orden
    List<DetalleOrden> detallesOrden=new ArrayList<>();  
    //datos de orden
    Orden orden = new Orden();
    
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
    
    @PostMapping("carrito")
    public String añadirCarrito(@RequestParam int id, @RequestParam int cantidad, Model model){ 
        //las anotaciones permiten obtener las datos enviados por formulario del productohome.html        
        Producto producto = productoService.get(id).get();
        DetalleOrden detalle = new DetalleOrden();
        double sumaPrecio;
        //LOGGER.info("Producto añadido: {}",producto);
        //LOGGER.info("Cantidad: {}",cantidad);
        detalle.setName(producto.getName());
        detalle.setProducto(producto);
        detalle.setAmount(cantidad);
        detalle.setPrize(producto.getPrice());
        detalle.setTotal(cantidad*producto.getPrice());
        detallesOrden.add(detalle);
        
        sumaPrecio=detallesOrden.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setAmount(sumaPrecio);
        
        model.addAttribute("detallesOrden",detallesOrden); //se manda para ver los datos en la vista
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }
}
