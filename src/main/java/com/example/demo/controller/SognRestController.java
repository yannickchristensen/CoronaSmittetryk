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

        //Constructor injection
        public SognRestController(SognRepository sognRepository, KommuneRepository kommuneRepository) {
            this.sognRepository = sognRepository;
            this.kommuneRepository = kommuneRepository;
        }


        @GetMapping("/sogne")
        public ResponseEntity<Iterable<Sogn>> findAll(){
            //returns list of sogne and httpsstatus OK
            return ResponseEntity.status(HttpStatus.OK).body(sognRepository.findAll());
        }

        @GetMapping("/sogn/{id}")
        public ResponseEntity<Optional<Sogn>> findBySogneId(@PathVariable Long id){

            //attempts to get sogn by idea and stores return value (sogn or nullobject) in optional
            Optional<Sogn> optionalSogn = sognRepository.findById(id);

            //if object exists within optional, return said object with httpstatus OK
            if(optionalSogn.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(optionalSogn);
            }
            //if object does not exist within optional, return null object with httpstatus not_found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalSogn);
        }

        @CrossOrigin(origins = "*", exposedHeaders = "Location")
        @PostMapping(value = "/sogne/{id}", consumes = "application/json")
        //create url takes kommune id as pathvariable and relates
        public ResponseEntity<String> create (@PathVariable("id")Long id, @RequestBody Sogn s){

            //local list of kommune ids
            List<Long> ids = new ArrayList<>();
            for (Kommune kommune: kommuneRepository.findAll()) {
                ids.add(kommune.getId());
            }

            //if kommune id path variable does not exist as kommune id, return not found message
            if (!ids.contains(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'kommune " + id + " not found'}");
            }

            //save input in local object
            Sogn sogn = new Sogn(s.getKode(), s.getNavn(), kommuneRepository.findById(id).get(), s.getSmittetryk(), s.getDatoForNedlukning());
            //save local object
            sognRepository.save(sogn);

            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/sogn/" + sogn.getKode())
                    .body("{'msg': 'sogn " + sogn.getId() + " created'}");
        }

        @PutMapping("/sogn/{id}")
        public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Sogn sogn){

            //get sogn to be updated
            Optional<Sogn> optionalSogn = sognRepository.findById(id);

            //return not found if id doesn't exist
            if(optionalSogn.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'sogn " + id + " not found'");
            }

            //update sogn
            sogn.setId(id);
            sognRepository.save(sogn);

            //return no content
            return ResponseEntity.status(HttpStatus.OK).body("{'msg' : 'sogn " + id + " updated'}");
        }

        @DeleteMapping("/sogn/{id}")
        public ResponseEntity<String> delete(@PathVariable("id") Long id){

            //get sogn
            Optional<Sogn> optionalSogn = sognRepository.findById(id);

            //return not found if id doesn't exist
            if(optionalSogn.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'sogn " + id + " not found'");
            }

            //save sogn and kommune in local objects from optional
            Sogn sogn = optionalSogn.get();
            Kommune kommune = sogn.getKommune();

            //set sogn's kommune to null
            sogn.setKommune(null);
            sognRepository.save(sogn);

            //set kommune's sogn to null
            //kommune.setSogn(null);
            kommuneRepository.save(kommune);

            //delete kommune and sogn
            kommuneRepository.delete(kommune);
            sognRepository.deleteById(id);


            //return status ok
            return ResponseEntity.status(HttpStatus.OK).body("{'msg' : 'sogn " + id + " deleted'}");
        }
    }

