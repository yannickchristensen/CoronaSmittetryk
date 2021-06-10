package com.example.demo.controller;


import com.example.demo.model.Sogn;
import com.example.demo.repository.SognRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestSognController {
    SognRepository sognRepository;

    //constructor injection
    public RestSognController(SognRepository sognRepository) {
        this.sognRepository = sognRepository;
    }


    //get list
    @GetMapping("/sogne")
    public ResponseEntity<Iterable<Sogn>> findAll(){
        //findAll recipes and return
        return ResponseEntity.status(HttpStatus.OK).body(sognRepository.findAll());
    }


    //get by id
    @GetMapping("/sogn/{id}")
    public ResponseEntity<Optional<Sogn>> findById(@PathVariable Long id)
    {
        Optional<Sogn> optionalRecipe = sognRepository.findById(id);
        if (optionalRecipe.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalRecipe);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalRecipe);
        }
    }

    // HTTP Post, ie. create
    @PostMapping("/sogn")
    public ResponseEntity<Sogn> createSogn(@RequestBody Sogn s){
        try {
            Sogn _sogn = sognRepository
                    .save(new Sogn(s.getSognKode(), s.getNavn(), s.getSmittetryk(), s.getNedlukningStart()));
            return new ResponseEntity<>(_sogn, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
