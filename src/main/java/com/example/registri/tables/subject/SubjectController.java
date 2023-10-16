package com.example.registri.tables.subject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @CrossOrigin
    @GetMapping("/subject")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> entityList = service.getAllSubjects();
        return ResponseEntity.ok(entityList);
    }

    @CrossOrigin
    @GetMapping("/subject/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable long id) {
        Optional<Subject> entity = service.getSubject(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/subject")
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody Subject e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Subject entity = service.addSubject(e);
        return ResponseEntity.created(new URI("/api/subject" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/subject")
    public ResponseEntity<Subject> updateSubject(@Valid @RequestBody Subject e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Subject entity = service.updateSubject(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/subject/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable long id) {
        if (service.getSubject(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteSubject(id);
        return ResponseEntity.ok().build();
    }
}