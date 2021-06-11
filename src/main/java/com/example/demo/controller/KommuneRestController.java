package com.example.demo.controller;

import com.example.demo.model.Kommune;
import com.example.demo.repository.KommuneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class KommuneRestController {
    KommuneRepository kommuneRepository;

    public KommuneRestController(KommuneRepository kommuneRepository) {
        this.kommuneRepository = kommuneRepository;
    }

    @GetMapping("/kommuner")
    public ResponseEntity<Iterable<Kommune>> findKommuner() {
        return ResponseEntity.status(HttpStatus.OK).body(kommuneRepository.findAll());
    }

    @GetMapping("/kommune/{id}")
    public ResponseEntity<Optional<Kommune>> findById(@PathVariable Long id) {
        Optional<Kommune> optionalRecipe = kommuneRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalRecipe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalRecipe);
        }
    }
}