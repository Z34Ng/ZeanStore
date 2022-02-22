/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.controller;

import com.ecommerce.zeanstore.ClothesStore.model.DetalleOrden;
import com.ecommerce.zeanstore.ClothesStore.model.Orden;
import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import com.ecommerce.zeanstore.ClothesStore.service.IDetalleOrdenService;
import com.ecommerce.zeanstore.ClothesStore.service.IOrdenService;
import com.ecommerce.zeanstore.ClothesStore.service.IUsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private  IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;
    
    @Autowired
    private IDetalleOrdenService detalleOrdenService;
    
    @GetMapping("/registro")
    public String mostrarFormRegistro(){
        return "usuario/registro";
    }
    
    @PostMapping("/saveUser")
    public String registrar(Usuario usuario ){
        //LOGGER.info("Datos de Usuario: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String mostrarFormLogin(){
        return "usuario/login";
    }
    
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){ //param session permite manejar el logueo
        Optional<Usuario> user=usuarioService.findByEmail(usuario.getEmail());
        //LOGGER.info(usuario1.get().getName());
        
        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId()); //la variable "idusuario" se puede usar
            //en cualquier lado de la aplicación 
            if(user.get().getTipo().equals("ADMIN"))
                return "redirect:/administrador";
            else
                return "redirect:/";
        }
        else
            LOGGER.info("Usuario no existe");
        return "redirect:/";
    }
    
    @GetMapping("/getPurchases")
    public String getPurchases(Model model, HttpSession session){
        model.addAttribute("session", session.getAttribute("idusuario"));
        
        Usuario user=usuarioService.findById(Integer.parseInt(
                                    session.getAttribute("idusuario")
                                            .toString())).get();                        
                
        List<Orden> ordenes = ordenService.findByUser(user);
        
        model.addAttribute("ordenes", ordenes);                
        return "usuario/compras";
    }
    
    @GetMapping("/getPurchaseDetails/{id}")
    public String getPurchasesDetails(@PathVariable int id, Model model, HttpSession session){
        model.addAttribute("session", session.getAttribute("idusuario")); //valida la existencia de la sesión
        
        Optional<Orden> orden=ordenService.findById(id);        
        
        model.addAttribute("detallesOrden", orden.get().getDetalles());
        return "usuario/detallecompra";
    }
}
