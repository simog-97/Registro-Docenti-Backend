package com.example.registri.tables.degree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository repository;

    public Degree addDegree(Degree e) {
        return repository.save(e);
    }

    public Optional<Degree> getDegree(Long id) {
        return repository.findById(id);
    }

    public List<Degree> getAllDegrees() {
        List<Degree> output = new ArrayList<Degree>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Degree updateDegree(Degree e) {
        return repository.save(e);
    }

    public void deleteDegree(Degree e) {
        repository.delete(e);
}

    public void deleteDegree(Long id) {
        repository.deleteById(id);
    }
}