package com.example.demo.Service;

import com.example.demo.model.Kommune;
import com.example.demo.repository.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class KommuneService {

    @Autowired
    KommuneRepository kommuneRepository;

    //method returns kommuner in hashset instead of iterable
    public Set<Kommune> findAll(){
        Set<Kommune> kommuner = new HashSet<>();
        for (Kommune kommune : kommuneRepository.findAll()) {
            kommuner.add(kommune);
        }
        return kommuner;
    }

    //method returns kommune object instead of optional
    public Kommune findById(Long id){
        Optional<Kommune> optionalKommune = kommuneRepository.findById(id);
        if (optionalKommune.isEmpty()){
            throw new RuntimeException("kommune " + id + " not found");
        }
        return optionalKommune.get();
    }

    public Kommune create(Kommune kommune){
        return kommuneRepository.save(kommune);
    }

    public Kommune update(Kommune kommune){
        if (!findAll().contains(kommune)){
            throw new RuntimeException("kommune " + kommune.getId() + " not found");
        } else {
            return kommuneRepository.save(kommune);
        }
    }

    public void deleteById(Long id){
        kommuneRepository.deleteById(id);
    }
}
