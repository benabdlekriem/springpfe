package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.Competition;
import com.itgate.ecommerce.models.EquipeFederation;
import com.itgate.ecommerce.models.User;
import com.itgate.ecommerce.repository.EquipeFederaRepository;
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
@RequestMapping("/equipe")
public class EquipeFederationController {
    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    private EquipeFederaRepository equipeFederaRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<EquipeFederation> getallequipe(){
        return equipeFederaRepository.findAll();

    }
    @PostMapping("/save/{id_user}")
    public EquipeFederation saveEquipe(EquipeFederation e, @PathVariable Long id_user, @RequestParam (value = "file") MultipartFile file){
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = ext+name+fileName;
            Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
            e.setImage(original);
        User user =userRepository.findById(id_user).get();
        e.setUser(user);
        e.setEnabled("activer");
        }catch (Exception ex){
            throw new RuntimeException("Fail File Problem BackEnd");
        }
        return equipeFederaRepository.save(e);
    }


    @PutMapping("/update/{id}")
    public EquipeFederation updateEquipe(@PathVariable Long id,  EquipeFederation e,@RequestParam (value = "file") MultipartFile file){
        EquipeFederation e1= equipeFederaRepository.findById(id).get();
        if(e1!= null){
            e.setId(id);
            e.setUser(e1.getUser());
            e.setType(e1.getType());
            e.setDatecreation(e1.getDatecreation());
            e.setEnabled(e1.getEnabled());

            try {
                String fileName = Integer.toString(new Random().nextInt(1000000000));
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                String original = ext+name+fileName;
                Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
                e.setImage(original);






            }catch (Exception ex){
                throw new RuntimeException("Fail File Problem BackEnd");
            }
            return equipeFederaRepository.saveAndFlush(e);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }

    @GetMapping("/getone/{id}")
    public EquipeFederation getone(@PathVariable Long id){

        return equipeFederaRepository.findById(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deleteequipe(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            equipeFederaRepository.deleteById(id);
            message.put("etat","equipe deleted");

        }catch (Exception e){
            message.put("etat","equipe not deleted");
        }
        return message;
    }

    @PutMapping("/desactiver/{id}")
    public EquipeFederation desactiver(@PathVariable Long id){
        EquipeFederation equipeFederation=equipeFederaRepository.findById(id).get();
        if(equipeFederation!= null){
            equipeFederation.setId(id);
            equipeFederation.setEnabled("désactiver");
            return equipeFederaRepository.saveAndFlush(equipeFederation);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }

    @PutMapping("/activer/{id}")
    public EquipeFederation activer(@PathVariable Long id){
        EquipeFederation equipeFederation=equipeFederaRepository.findById(id).get();
        if(equipeFederation!= null){
            equipeFederation.setId(id);
            equipeFederation.setEnabled("activer");
            return equipeFederaRepository.saveAndFlush(equipeFederation);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
}
