/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.Producto;
import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import com.ecommerce.zeanstore.ClothesStore.service.ProductoService;
import com.ecommerce.zeanstore.ClothesStore.service.UploadFileService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZEAN
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired //inyecctión
    private UploadFileService upload; //servicio creado para subir imagen

    @GetMapping("/showAll")
    public String showAll(Model model) {
        model.addAttribute("productosTotal", productoService.findAll());
        return "producto/show";
    }

    @GetMapping("/create")
    public String createProduct() {
        return "producto/create";
    }

    @PostMapping("/save")
    public String saveProduct(Producto producto, @RequestParam("img") MultipartFile file) throws IOException { //obtiene el param del atributo img de la vista
        //LOGGER.info("Prueba con logger si guarda esta webaad",producto.toString());        
        producto.setUser(new Usuario(1, "", "", "", "", "", "", ""));

        //imagen
        if (producto.getId()==null) { //cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setPicture(nombreImagen);
        } else {
            if (file.isEmpty()) { //editamos el producto pero no cambiamos la img
                Producto p = productoService.get(producto.getId()).get();
                producto.setPicture(p.getPicture());
            }
            else {
                String nombreImagen = upload.saveImage(file);
                producto.setPicture(nombreImagen);
            }
        }

        productoService.save(producto);

        return "redirect:/productos/showAll";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) { //la variable que pasa por url pasa como parametro del metodo         
        Optional<Producto> optionalProducto = productoService.get(id);
        Producto producto = optionalProducto.get();
        model.addAttribute("producto", producto);
        //LOGGER.info("Resultado del producto buscado",producto.toString());
        return "producto/edit";
    }

    @PostMapping("/update")
    public String updateProduct(Producto producto) {
        productoService.update(producto);
        return "redirect:/productos/showAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) { //la variable que pasa por url pasa como parametro del metodo         
        productoService.delete(id);
        return "redirect:/productos/showAll";
    }
}
