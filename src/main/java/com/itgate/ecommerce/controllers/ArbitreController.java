package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.Arbitre;
import com.itgate.ecommerce.repository.ArbitreRepository;
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
@CrossOrigin("*")
@RequestMapping("/arbitre")
public class ArbitreController {
    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    private ArbitreRepository arbitreRepository;

    @GetMapping("/all")
    public List<Arbitre> getallArbitre(){
        return arbitreRepository.findAll();

    }
    @PostMapping("/save")
    public Arbitre saveArbitre( Arbitre a,@RequestParam (value = "file") MultipartFile file){
        // storageService.store(file);
        //  p.setImage(file.getOriginalFilename());
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = ext+name+fileName;
            Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
            a.setImage(original);


        }catch (Exception e){
            throw new RuntimeException("Fail File Problem BackEnd");
        }
        return arbitreRepository.save(a);
    }
    @GetMapping("/getone/{id}")
    public Arbitre getone(@PathVariable Long id){

        return arbitreRepository.findById(id).get();
    }
    @PutMapping("/update/{id}")
    public Arbitre updateArbitre(@PathVariable Long id, Arbitre ar,@RequestParam (value = "file") MultipartFile file){
        Arbitre ar1=arbitreRepository.findById(id).get();
        if(ar1!= null){
            ar.setId(id);
            ar.setDatenaissance(ar1.getDatenaissance());
            ar.setCin(ar1.getCin());
            ar.setNumlicence(ar1.getNumlicence());
            try {
                String fileName = Integer.toString(new Random().nextInt(1000000000));
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                String original = ext+name+fileName;
                Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
               ar.setImage(original);






            }catch (Exception e){
                throw new RuntimeException("Fail File Problem BackEnd");
            }

            return arbitreRepository.saveAndFlush(ar);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deletearbitre(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            arbitreRepository.deleteById(id);
            message.put("etat","arbitre deleted");

        }catch (Exception e){
            message.put("etat","arbitre  not deleted");
        }
        return message;
    }
}
