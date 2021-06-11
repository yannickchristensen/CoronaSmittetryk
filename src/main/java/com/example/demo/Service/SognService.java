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
    public Set<Sogn> findAll(){
        Set<Sogn> sogne = new HashSet<>();
        for (Sogn sogn : sognRepository.findAll()) {
            sogne.add(sogn);
        }
        return sogne;
    }

    public Sogn findById(Long id){
        Optional<Sogn> optionalSogn = sognRepository.findById(id);
        if (optionalSogn.isEmpty()){
            throw new RuntimeException("Sogn " + id + " not found");
        }
        return optionalSogn.get();
    }

    public Sogn create(Sogn sogn){
        return sognRepository.save(sogn);
    }

    public Sogn update(Sogn sogn) {
        return sognRepository.save(sogn);
    }

    public void deleteById(Long id){
        sognRepository.deleteById(id);
    }
}