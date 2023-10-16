package com.example.registri.tables.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public Subject addSubject(Subject e) {
        return repository.save(e);
    }

    public Optional<Subject> getSubject(Long id) {
        return repository.findById(id);
    }

    public List<Subject> getAllSubjects() {
        List<Subject> output = new ArrayList<Subject>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Subject updateSubject(Subject e) {
        return repository.save(e);
    }

    public void deleteSubject(Subject e) {
        repository.delete(e);
}

    public void deleteSubject(Long id) {
        repository.deleteById(id);
    }
}