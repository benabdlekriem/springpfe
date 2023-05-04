package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.EquipeFederation;
import com.itgate.ecommerce.models.MembreEquipeFederation;
import com.itgate.ecommerce.models.User;
import com.itgate.ecommerce.repository.EquipeFederaRepository;
import com.itgate.ecommerce.repository.MembreEquipeFederaRepository;
import com.itgate.ecommerce.repository.UserRepository;
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

@RequestMapping("/mequipe")
public class MembreEquipeFederController {
    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    private MembreEquipeFederaRepository membreEquipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EquipeFederaRepository equipeFederaRepository;
    @GetMapping("/all")
    public List<MembreEquipeFederation> getallmbreequipe(){
        return membreEquipeRepository.findAll();

    }
    @PostMapping("/save/{id_equipefedera}")
    public MembreEquipeFederation saveMembreequipe(MembreEquipeFederation me, @PathVariable Long id_equipefedera, @RequestParam (value = "file") MultipartFile file){

        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = ext+name+fileName;
            Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
            me.setImage(original);
      EquipeFederation equipeFederation = equipeFederaRepository.findById(id_equipefedera).get();
       me.setEquipeFederation(equipeFederation);
        }catch (Exception e){
            throw new RuntimeException("Fail File Problem BackEnd");
        }
        return membreEquipeRepository.save(me);
    }
    @GetMapping("/getone/{id}")
    public MembreEquipeFederation getone(@PathVariable Long id){

        return membreEquipeRepository.findById(id).get();
    }
    @PutMapping("/update/{id}/{id_equipefeder}")
    public MembreEquipeFederation updateMembreequipe(@PathVariable Long id,MembreEquipeFederation me,@PathVariable Long id_equipefeder,@RequestParam (value = "file") MultipartFile file){
        MembreEquipeFederation mf1=membreEquipeRepository.findById(id).get();
        if(mf1!= null){
            me.setId(id);
            me.setDatenaissance(mf1.getDatenaissance());
            me.setCin(mf1.getCin());
            me.setNom(mf1.getNom());
            me.setPrenom(mf1.getPrenom());
            me.setNumlicence(mf1.getNumlicence());

            try {
                String fileName = Integer.toString(new Random().nextInt(1000000000));
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                String original = ext+name+fileName;
                Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
                me.setImage(original);






            }catch (Exception e){
                throw new RuntimeException("Fail File Problem BackEnd");
            }


            EquipeFederation ef = equipeFederaRepository.findById(id_equipefeder).get();
            me.setEquipeFederation(ef);
            return membreEquipeRepository.saveAndFlush(me);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deleteMembreequipe(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            membreEquipeRepository.deleteById(id);
            message.put("etat","Membree quipe deleted");

        }catch (Exception e){
            message.put("etat","Membre equipe not deleted");
        }
        return message;
    }

}
