package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.*;
import com.itgate.ecommerce.repository.*;
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
@RequestMapping("/coureur")
public class CoureurController {
    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    private CoureurRepository coureurRepository;
    @Autowired
    private EquipeFederaRepository equipeFederaRepository;



    @GetMapping("/all")
    public List<Coureur> getallCoureur(){
        return coureurRepository.findAll();

    }
    @PostMapping("/save/{id_equipefeder}")
    public Coureur saveCoureur(Coureur c, @PathVariable Long id_equipefeder,@RequestParam (value = "file") MultipartFile file){
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = ext+name+fileName;
            Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
           c.setImage(original);
            c.setEnabled("activer");

        EquipeFederation ef = equipeFederaRepository.findById(id_equipefeder).get();
        c.setEquipeFederation(ef);



        }catch (Exception e){
            throw new RuntimeException("Fail File Problem BackEnd");
        }


        return coureurRepository.save(c);
    }


    @PutMapping("/update/{id}/{id_equipefeder}")
    public Coureur updateCoureur(@PathVariable Long id, Coureur c,@PathVariable Long id_equipefeder,@RequestParam (value = "file") MultipartFile file){
        Coureur c1=coureurRepository.findById(id).get();
        if(c1!= null){
            c.setId(id);
            c.setGenre(c1.getGenre());
            c.setDatenaissance(c1.getDatenaissance());
            c.setCin(c1.getCin());
            c.setCategorycoureur(c1.getCategorycoureur());
            c.setEnabled(c1.getEnabled());
            try {
                String fileName = Integer.toString(new Random().nextInt(1000000000));
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                String original = ext+name+fileName;
                Files.copy(file.getInputStream(),this.rootLocation.resolve(original));
                c.setImage(original);






            }catch (Exception e){
                throw new RuntimeException("Fail File Problem BackEnd");
            }


            EquipeFederation ef = equipeFederaRepository.findById(id_equipefeder).get();
            c.setEquipeFederation(ef);
            return coureurRepository.saveAndFlush(c);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }


    @GetMapping("/getone/{id}")
    public Coureur getone(@PathVariable Long id){

        return coureurRepository.findById(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deletecoureur(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            coureurRepository.deleteById(id);
            message.put("etat","coureur deleted");

        }catch (Exception e){
            message.put("etat","coureur  not deleted");
        }
        return message;
    }

    @PutMapping("/desactiver/{id}")
    public Coureur desactiver(@PathVariable Long id){
        Coureur coureur=coureurRepository.findById(id).get();
        if(coureur!= null){
            coureur.setId(id);
            coureur.setEnabled("désactiver");
            return coureurRepository.saveAndFlush(coureur);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }

    @PutMapping("/activer/{id}")
    public Coureur activer(@PathVariable Long id){
        Coureur coureur=coureurRepository.findById(id).get();
        if(coureur!= null){
            coureur.setId(id);
            coureur.setEnabled("activer");
            return coureurRepository.saveAndFlush(coureur);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }

    @PutMapping("/updatedistance/{id}")
    public Coureur updatedistance(@PathVariable Long id,String distance){
        Coureur coureur=coureurRepository.findById(id).get();
        if(coureur!= null){
            coureur.setId(id);
            coureur.setDistance(distance);
            return coureurRepository.saveAndFlush(coureur);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }


    @PutMapping("/updateclassement/{id}")
    public Coureur updateclassement(@PathVariable Long id,String classement,String dossards){
        Coureur coureur=coureurRepository.findById(id).get();
        if(coureur!= null){
            coureur.setId(id);
            coureur.setClassement(classement);
            coureur.setDossards(dossards);
            return coureurRepository.saveAndFlush(coureur);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }

}
