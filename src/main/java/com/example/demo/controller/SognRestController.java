package com.example.demo.controller;

import com.example.demo.model.Kommune;
import com.example.demo.model.Sogn;
import com.example.demo.repository.KommuneRepository;
import com.example.demo.repository.SognRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @org.springframework.web.bind.annotation.RestController
    @RequestMapping("/api")
    public class SognRestController {

        private SognRepository sognRepository;
        private KommuneRepository kommuneRepository;

        public SognRestController(SognRepository sognRepository, KommuneRepository kommuneRepository) {
            this.sognRepository = sognRepository;
            this.kommuneRepository = kommuneRepository;
        }


        @GetMapping("/sogne")
        public ResponseEntity<Iterable<Sogn>> findAll(){
            return ResponseEntity.status(HttpStatus.OK).body(sognRepository.findAll());
        }

        @GetMapping("/sogn/{id}")
        public ResponseEntity<Optional<Sogn>> findBySogneId(@PathVariable Long id){
            Optional<Sogn> optionalSogn = sognRepository.findById(id);
            if(optionalSogn.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(optionalSogn);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalSogn);
        }

        @CrossOrigin(origins = "*", exposedHeaders = "Location")
        @PostMapping(value = "/sogne/{id}", consumes = "application/json")
        public ResponseEntity<String> create (@PathVariable("id")Long id, @RequestBody Sogn s){
            List<Long> ids = new ArrayList<>();
            for (Kommune kommune: kommuneRepository.findAll()) {
                ids.add(kommune.getId());
            }
            if (!ids.contains(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'kommune " + id + " not found'}");
            }
            Sogn sogn = new Sogn(s.getKode(), s.getNavn(), kommuneRepository.findById(id).get(), s.getSmittetryk(), s.getDatoForNedlukning(), s.isNedlukket());
            sognRepository.save(sogn);

            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/sogn/" + sogn.getKode())
                    .body("{'msg': 'sogn " + sogn.getId() + " created'}");
        }

        @PutMapping("/sogn/{id}")
        public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Sogn sogn){
            Optional<Sogn> optionalSogn = sognRepository.findById(id);
            if(optionalSogn.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'sogn " + id + " not found'");
            }
            sogn.setId(id);
            sognRepository.save(sogn);
            return ResponseEntity.status(HttpStatus.OK).body("{'msg' : 'sogn " + id + " updated'}");
        }

        @DeleteMapping("/sogn/{id}")
        public ResponseEntity<String> delete(@PathVariable("id") Long id){
            Optional<Sogn> optionalSogn = sognRepository.findById(id);
            if(optionalSogn.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'sogn " + id + " not found'");
            }
            Sogn sogn = optionalSogn.get();
            Kommune kommune = sogn.getKommune();
            sogn.setKommune(null);
            sognRepository.save(sogn);
            kommuneRepository.save(kommune);
            kommuneRepository.delete(kommune);
            sognRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("{'msg' : 'sogn " + id + " deleted'}");
        }
    }

