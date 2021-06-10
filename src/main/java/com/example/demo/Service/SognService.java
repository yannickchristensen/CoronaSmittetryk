package com.example.demo.Service;

import com.example.demo.model.Sogn;
import com.example.demo.repository.SognRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SognService {

    @Autowired
    SognRepository sognRepository;

    //method returns sogne in hashset instead of iterable
    public Set<Sogn> findAll(){
        Set<Sogn> sogne = new HashSet<>();
        for (Sogn sogn : sognRepository.findAll()) {
            sogne.add(sogn);
        }
        return sogne;
    }

    //method returns sogn object instead of optional
    public Sogn findById(Long id){
        Optional<Sogn> optionalSogn = sognRepository.findById(id);
        if (optionalSogn.isEmpty()){
            throw new RuntimeException("sogn " + id + " not found");
        }
        return optionalSogn.get();
    }

    public Sogn create(Sogn sogn){
        return sognRepository.save(sogn);
    }

    public Sogn update(Sogn sogn){
        if (!findAll().contains(sogn)){
            throw new RuntimeException("sogn " + sogn.getId() + " not found");
        } else {
            return sognRepository.save(sogn);
        }
    }

    public void deleteById(Long id){
        sognRepository.deleteById(id);
    }
}