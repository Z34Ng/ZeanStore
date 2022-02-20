/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import com.ecommerce.zeanstore.ClothesStore.controller.ProductoController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZEAN
 */
@Service
public class ManageFileService {
    private final String folder="images//";    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    public String saveImage(MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            byte[] bytes=file.getBytes(); //se convierte a bytes para realizar el envio            
            Path path=Paths.get(folder+file.getOriginalFilename());//establezco 
            //LOGGER.info(folder+file.getOriginalFilename());
            //la ruta (URI) donde se guardara la imagen 
            Files.write(path,bytes);
            return file.getOriginalFilename(); //Retorna el nombre de la imagen subida
        }
        
        return "default.jpg";
    }
    
    public void deleteImage(String nombre){        
        File file=new File(folder+nombre);
        file.delete();
    }
    
    public String convertNameFile(String prueba){            
        return prueba.replace(" ", "_");        
    }
}
