package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.CategorieVelo;
import com.itgate.ecommerce.repository.CategorieVeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
//hedhi marbouta bel front end w tzid fichier kaml esmou util mta3 local storage
@CrossOrigin("*")
@RequestMapping("/Categorievelo")
public class CategorieVeloController {
    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    private CategorieVeloRepository categorieVeloRepository;
    @GetMapping("/all")
    public List<CategorieVelo> getallcategorieVelo(){
        return categorieVeloRepository.findAll();

    }
    @PostMapping("/save")
    public CategorieVelo saveCategorieVelo( CategorieVelo cv,@RequestParam (value = "file") MultipartFile file){
        // storageService.store(file);
        //  p.setImage(file.getOriginalFilename());
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = ext+name+fileName;
            Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
           cv.setImage(original);
        }catch (Exception e){
            throw new RuntimeException("Fail File Problem BackEnd");
        }



        return categorieVeloRepository.save(cv);
    }
    @GetMapping("/getone/{id}")
    public CategorieVelo getone(@PathVariable Long id){

        return categorieVeloRepository.findById(id).get();
    }
    @PutMapping("/update/{id}")
    public CategorieVelo updateCategorieVelo(@PathVariable Long id, CategorieVelo cv,@RequestParam (value = "file") MultipartFile file){
        CategorieVelo cv1=categorieVeloRepository.findById(id).get();
        if(cv1!= null){
            cv.setId(id);

            try {
                String fileName = Integer.toString(new Random().nextInt(1000000000));
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                String original = ext+name+fileName;
                Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
                cv.setImage(original);
            }catch (Exception e){
                throw new RuntimeException("Fail File Problem BackEnd");
            }


            return categorieVeloRepository.saveAndFlush(cv);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deleteCategorieVelo(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            categorieVeloRepository.deleteById(id);
            message.put("etat","Categorie Velo deleted");

        }catch (Exception e){
            message.put("etat","Categorie Velo  not deleted");
        }
        return message;
    }
}
