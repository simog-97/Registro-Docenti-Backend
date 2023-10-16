package com.example.registri.tables.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    public Register addRegister(Register e) {
        return repository.save(e);
    }

    public Optional<Register> getRegister(Long id) {
        return repository.findById(id);
    }

    public List<Register> getAllRegisters() {
        List<Register> output = new ArrayList<Register>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Register updateRegister(Register e) {
        return repository.save(e);
    }

    public void deleteRegister(Register e) {
        repository.delete(e);
}

    public void deleteRegister(Long id) {
        repository.deleteById(id);
    }

    public List<Register> getByProfessorId(Long id) {
        return repository.findByProfessorId(id);
    }
}