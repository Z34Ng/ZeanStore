/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.zeanstore.ClothesStore.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZEAN
 */
@Service
public class ManageFileService {
    private final String folder="images//";    
    
    public String saveImage(MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            byte[] bytes=file.getBytes(); //se convierte a bytes para realizar el envio
            
            Path path=Paths.get(folder+file.getOriginalFilename());//establezco 
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
}