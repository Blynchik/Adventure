package com.adventure.base.service;

import com.adventure.base.model.FirstName;
import com.adventure.base.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NameService {

    private final NameRepository nameRepository;

    @Autowired
    public NameService(NameRepository nameRepository){
        this.nameRepository = nameRepository;
    }

    @Transactional
    public void addNew(FirstName firstName){
        nameRepository.save(firstName);
    }

    public Optional<FirstName> getOne(int id){
        return nameRepository.findById(id);
    }

    public Optional<FirstName> getByName(String name){
        return nameRepository.findByFirstName(name);
    }

    public String getRandomName(){
        return nameRepository.findRandomName().getFirstName();
    }

    public List<FirstName> getAll(){
        return nameRepository.findAll();
    }

    public List<FirstName> getAllSorted(){
        return nameRepository.findAll().stream()
                .sorted(Comparator.comparing(FirstName::getFirstName)).toList();
    }

    @Transactional
    public void delete(int id){
        nameRepository.deleteById(id);
    }

    public boolean idExistence(int id){
        return nameRepository.existsById(id);
    }

    public boolean nameExistence(String name){
        return getByName(name).isPresent();
    }
}
