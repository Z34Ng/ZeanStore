/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.model.Usuario;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */

public interface IUsuarioService {
    Optional<Usuario> findById(int id);
    Usuario save(Usuario usuario); 
    Optional<Usuario> findByEmail(String email);
}
