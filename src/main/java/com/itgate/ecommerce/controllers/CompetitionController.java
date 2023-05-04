package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.*;
import com.itgate.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
//hedhi marbouta bel front end w tzid fichier kaml esmou util mta3 local storage
@CrossOrigin("*")
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CategorieVeloRepository categorieVeloRepository;
    @Autowired
    private ArbitreRepository arbitreRepository;
    @Autowired
    private EquipeFederaRepository equipeFederaRepository;

    @Autowired
    private CoureurRepository coureurRepository;


    @GetMapping("/all")
    public List<Competition> getallCompetition(){
        return competitionRepository.findAll();

    }
    @PostMapping("/save/{id_categorie}")
    public Competition saveCompetition(@RequestBody Competition co,@RequestParam List<Long> ida,@RequestParam List<Long> idc,  @PathVariable Long id_categorie){
        for (int i=0;i<ida.size();i++){
            Arbitre a =arbitreRepository.findById(ida.get(i)).get();
           co.addarbitre(a);
            System.out.println(a);
        }

        for (int i=0;i<idc.size();i++){
            Coureur c =coureurRepository.findById(idc.get(i)).get();
            co.addcoureur(c);
            System.out.println(c);
        }


        CategorieVelo cv = categorieVeloRepository.findById(id_categorie).get();
        co.setCategorieVelo(cv);

        return competitionRepository.save(co);
    }
    @GetMapping("/getone/{id}")
    public Competition getone(@PathVariable Long id){

        return competitionRepository.findById(id).get();
    }
    @PutMapping("/update/{id}")
    public Competition updateCompetition(@PathVariable Long id,@RequestBody Competition co){
        Competition co1=competitionRepository.findById(id).get();
        if(co1!= null){
            co.setId(id);
            return competitionRepository.saveAndFlush(co);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deleteCompetition(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            competitionRepository.deleteById(id);
            message.put("etat","competition deleted");

        }catch (Exception e){
            message.put("etat","competition  not deleted");
        }
        return message;
    }
}
