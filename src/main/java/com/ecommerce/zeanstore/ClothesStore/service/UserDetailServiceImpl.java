/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private IUsuarioService usuarioService;    
    /*
    @Lazy
    @Autowired  
    private BCryptPasswordEncoder bCrypt;//permite encriptar y desencriptar password
    */
    @Autowired
    HttpSession session; //guarda sesion del usuario logueado
    
    private final Logger log=LoggerFactory.getLogger( UserDetailServiceImpl.class);
    
    //busca el usuario mediante la base de datos por Spring security
    //UserDetails pertenece a Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);
        
        if(optionalUser.isPresent()){
            session.setAttribute("idusuario",optionalUser.get().getId());
            Usuario usuario=optionalUser.get();
            log.info("Id del usuario logueado: {}",optionalUser.get().getId());
            //El password estara encriptado y realizara match
            return User.builder()
                    .username(usuario.getName())
                    //.password(bCrypt.encode(usuario.getPassword()))
                    .password(usuario.getPassword())
                    .roles(usuario.getTypeUser())
                    .build(); //construye objeto UserDetails
        }
        else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }    
}
