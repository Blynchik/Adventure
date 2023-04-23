package com.adventure.base.service;

import com.adventure.base.model.LastName;
import com.adventure.base.repository.SurnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SurnameService {

    private final SurnameRepository surnameRepository;

    @Autowired
    public SurnameService(SurnameRepository surnameRepository){
        this.surnameRepository = surnameRepository;
    }

    @Transactional
    public void addNew(String surname){
        surnameRepository.save(new LastName(surname));
    }

    public Optional<LastName> getOne(int id){
        return surnameRepository.findById(id);
    }

    public Optional<LastName> getBySurname(String surname){
        return surnameRepository.findByLastName(surname);
    }

    public String getRandomSurname(){
        return surnameRepository.findRandomName().getLastName();
    }

    public List<LastName> getAll(){
        return surnameRepository.findAll();
    }

    public List<LastName> getAllSorted(){
        return surnameRepository.findAll().stream()
                .sorted(Comparator.comparing(LastName::getLastName)).toList();
    }

    @Transactional
    public void delete(int id){
        surnameRepository.deleteById(id);
    }

    public boolean idExistence(int id){
        return surnameRepository.existsById(id);
    }

    public boolean surnameExistence(String surname){
        return getBySurname(surname).isPresent();
    }
}