package com.example.registri.tables.degree;

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
public class DegreeController {

    @Autowired
    private DegreeService service;

    @CrossOrigin
    @GetMapping("/degree")
    public ResponseEntity<List<Degree>> getAllDegrees() {
        List<Degree> entityList = service.getAllDegrees();
        return ResponseEntity.ok(entityList);
    }

    @CrossOrigin
    @GetMapping("/degree/{id}")
    public ResponseEntity<Degree> getDegree(@PathVariable long id) {
        Optional<Degree> entity = service.getDegree(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin    
    @PostMapping("/degree")
    public ResponseEntity<Degree> addDegree(@Valid @RequestBody Degree e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Degree entity = service.addDegree(e);
        return ResponseEntity.created(new URI("/api/degree" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/degree")
    public ResponseEntity<Degree> updateDegree(@Valid @RequestBody Degree e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Degree entity = service.updateDegree(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/degree/{id}")
    public ResponseEntity<Void> deleteDegree(@PathVariable long id) {
        if (service.getDegree(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteDegree(id);
        return ResponseEntity.ok().build();
    }
}