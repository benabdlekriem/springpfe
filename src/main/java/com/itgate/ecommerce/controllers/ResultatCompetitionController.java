package com.itgate.ecommerce.controllers;

import com.itgate.ecommerce.models.Competition;
import com.itgate.ecommerce.models.Coureur;
import com.itgate.ecommerce.models.ResultatCompetition;
import com.itgate.ecommerce.repository.CompetitionRepository;
import com.itgate.ecommerce.repository.CoureurRepository;
import com.itgate.ecommerce.repository.ResultatCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
//hedhi marbouta bel front end w tzid fichier kaml esmou util mta3 local storage
@CrossOrigin("*")
@RequestMapping("/resultat")
public class ResultatCompetitionController {
    @Autowired
    private ResultatCompetitionRepository resultatCompetitionRepository;
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CoureurRepository coureurRepository;
    @GetMapping("/all")
    public List<ResultatCompetition> getallResultatCompetition(){
        return resultatCompetitionRepository.findAll();

    }
    @PostMapping("/save/{id_competition}")
    public ResultatCompetition saveResultatCompetition(@RequestBody ResultatCompetition rc,@PathVariable Long id_competition ){
        Competition comp = competitionRepository.findById(id_competition).get();
        rc.setCompetition(comp);


        return  resultatCompetitionRepository.save(rc);
    }
    @GetMapping("/getone/{id}")
    public ResultatCompetition getone(@PathVariable Long id){

        return resultatCompetitionRepository.findById(id).get();
    }
    @PutMapping("/update/{id}")
    public ResultatCompetition updateResultatCompetition(@PathVariable Long id,@RequestBody ResultatCompetition rc){
        ResultatCompetition rc1=resultatCompetitionRepository.findById(id).get();
        if(rc1!= null){
            rc.setId(id);
            rc.setCompetition(rc1.getCompetition());
            return resultatCompetitionRepository.saveAndFlush(rc);
        }
        else{
            throw  new RuntimeException("FAIL!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String ,String > deleteResultatCompetition(@PathVariable Long id){
        HashMap<String ,String> message=new HashMap<>();
        try{
            resultatCompetitionRepository.deleteById(id);
            message.put("etat","resultat de competition  deleted");

        }catch (Exception e){
            message.put("etat","resultat de competition   not deleted");
        }
        return message;
    }
}
