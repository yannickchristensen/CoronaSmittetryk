package com.example.demo.controller;


import com.example.demo.model.Kommune;
import com.example.demo.model.Sogn;
import com.example.demo.repository.KommuneRepository;
import com.example.demo.repository.SognRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RestSognController {
    private KommuneRepository kommuneRepository;
    private SognRepository sognRepository;


    //constructor injection
    public RestSognController(SognRepository sognRepository, KommuneRepository kommuneRepository) {
        this.kommuneRepository = kommuneRepository;
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

    // create sogn

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/sogn", consumes = "application/json")
    public ResponseEntity<String> createSogn(@RequestBody Sogn s){
        Sogn _sogn = new Sogn(s.getSognKode(), s.getNavn(), s.getSmittetryk(), s.getNedlukningStart());
        sognRepository.save(_sogn);

        Kommune kommune = s.getKommune();
        kommune.setSogn(_sogn);
        kommuneRepository.save(kommune);

        _sogn.setKommune(kommune);
        sognRepository.save(_sogn);

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/sogn/" + _sogn.getId())
                .body("{'msg': 'Post Created'}");
    }


    @PutMapping("/sogn/{id}")
    public ResponseEntity<Sogn> updateTutorial(@PathVariable("id") long id, @RequestBody Sogn sogn) {
            Optional<Sogn> sognData = sognRepository.findById(id);

            if (sognData.isPresent()) {
                Sogn _sogn = sognData.get();
                _sogn.setSognKode(sogn.getSognKode());
                _sogn.setNavn(sogn.getNavn());
                _sogn.setNedlukningStart(sogn.getNedlukningStart());
                _sogn.getKommune().setKommuneKode(sogn.getKommune().getKommuneKode());
                _sogn.getKommune().setNavn(sogn.getKommune().getNavn());
                return new ResponseEntity<>(sognRepository.save(_sogn), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }


