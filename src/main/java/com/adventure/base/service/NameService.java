package com.adventure.base.service;

import com.adventure.base.model.FirstName;
import com.adventure.base.repository.NameRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void addNew(String name){
        nameRepository.save(new FirstName(name));
    }

    public Optional<FirstName> getOne(int id){
        return nameRepository.findById(id);
    }

    public String getRandomName(){
        return nameRepository.findRandomName().getFirstName();
    }

    public List<FirstName> getAll(){
        return nameRepository.findAll();
    }

    @Transactional
    public void delete(int id){
        nameRepository.deleteById(id);
    }
}
