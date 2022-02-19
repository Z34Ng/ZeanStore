/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.DetalleOrden;
import com.ecommerce.zeanstore.ClothesStore.model.Orden;
import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import com.ecommerce.zeanstore.ClothesStore.service.IDetalleOrdenService;
import com.ecommerce.zeanstore.ClothesStore.service.IOrdenService;
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
import com.ecommerce.zeanstore.ClothesStore.service.IProductoService;
import com.ecommerce.zeanstore.ClothesStore.service.IUsuarioService;
import java.time.LocalDate;

/**
 *
 * @author ZEAN
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private IProductoService productoService;
    
    @Autowired
    private IOrdenService ordenService;
    
    @Autowired
    private IDetalleOrdenService detalleOrdenService;
    
    @Autowired
    private IUsuarioService usuarioService;
    
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
        
        //valida que el producto no se añada dos veces
        int idProducto=producto.getId();
        boolean ingresado=detallesOrden.stream().anyMatch(p -> p.getProducto().getId()==idProducto);        
        if(!ingresado)
            detallesOrden.add(detalle); 
        
        
        sumaPrecio=detallesOrden.stream().mapToDouble(dt->dt.getTotal()).sum(); // streams java 8
        orden.setAmount(sumaPrecio);
        
        model.addAttribute("detallesOrden",detallesOrden); //se manda para ver los datos en la vista
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }   
    /*
       (Se puede agregar un id global y asignarlo al detalle.id cuando se crea)
    */
    @GetMapping("/quitarProducto/{id}") //retorna el id del producto, no del detalle
    public String quitarProducto(@PathVariable int id, Model model){        
        //detallesOrden.removeIf(dt->dt.getName().equals(productoService.get(id).get().getName()));
        List<DetalleOrden> newOrdenDetalle=new ArrayList<>();
        
        for(DetalleOrden detalle:detallesOrden)
            if(detalle.getProducto().getId()!=id)
                newOrdenDetalle.add(detalle);
        
        detallesOrden=newOrdenDetalle; //cambia la lista 
        
        double sumaPrecio=detallesOrden.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setAmount(sumaPrecio);
        
        model.addAttribute("detallesOrden",detallesOrden); //se manda para ver los datos en la vista
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }
    
    @GetMapping("/getCart")
    public String getCart( Model model ){
        model.addAttribute("detallesOrden",detallesOrden); //se manda para ver los datos en la vista
        model.addAttribute("orden",orden);
        
        return "usuario/carrito";
    }
    
    @GetMapping("/order")
    public String getOrder(Model model){
        Usuario usuario=usuarioService.findById(1).get();//id de la base de datos
        
        model.addAttribute("detallesOrden",detallesOrden); //se manda para ver los datos en la vista
        model.addAttribute("orden",orden);
        model.addAttribute("usuario", usuario);
        
        return "usuario/resumenorden";
    }
    
    @GetMapping("/saveorder")
    public String saveOrder(){
        orden.setNumber(ordenService.getNumeroOrden());
        orden.setCreateDate(LocalDate.now()); //obtiene la fecha del sistema
        //orden.setReciedDate(LocalDate.MIN);
        orden.setUser(usuarioService.findById(2).get());//id de la base de datos        
        ordenService.save(orden);
        guardarDetalleOrden();
        vaciarOrdenYDetalles();
                
        return "redirect:/";
    }
    
    private void guardarDetalleOrden(){
        for(DetalleOrden detalle:detallesOrden){
            detalle.setOrden(orden);
            detalleOrdenService.save(detalle);
        }            
    }
    
   private void vaciarOrdenYDetalles(){
       orden=new Orden();
       detallesOrden.clear();
   }
}
