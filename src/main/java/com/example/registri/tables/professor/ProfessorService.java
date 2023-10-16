package com.example.registri.tables.professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor addProfessor(Professor e) {
        return repository.save(e);
    }

    public Optional<Professor> getProfessor(Long id) {
        return repository.findById(id);
    }

    public List<Professor> getAllProfessors() {
        List<Professor> output = new ArrayList<Professor>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Professor updateProfessor(Professor e) {
        return repository.save(e);
    }

    public void deleteProfessor(Professor e) {
        repository.delete(e);
}

    public void deleteProfessor(Long id) {
        repository.deleteById(id);
    }


}