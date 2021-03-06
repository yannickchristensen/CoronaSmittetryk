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

    public Set<Kommune> findAll(){
        Set<Kommune> kommuner = new HashSet<>();
        for (Kommune kommune : kommuneRepository.findAll()) {
            kommuner.add(kommune);
        }
        return kommuner;
    }

    public Kommune findById(Long id){
        Optional<Kommune> optionalKommune = kommuneRepository.findById(id);
        if (optionalKommune.isEmpty()){
            throw new RuntimeException("Kommune " + id + " not found");
        }
        return optionalKommune.get();
    }

    public Kommune create(Kommune kommune){
        return kommuneRepository.save(kommune);
    }

    public Kommune update(Kommune kommune){
            return kommuneRepository.save(kommune);
        }

    public void deleteById(Long id){
        kommuneRepository.deleteById(id);
    }
}
