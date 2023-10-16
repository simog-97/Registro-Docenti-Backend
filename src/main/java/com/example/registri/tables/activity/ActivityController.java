package com.example.registri.tables.activity;

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
public class ActivityController {

    @Autowired
    private ActivityService service;

    @CrossOrigin
    @GetMapping("/activity")
    public ResponseEntity<List<Activity>> getAllActivitys() {
        List<Activity> entityList = service.getAllActivitys();
        return ResponseEntity.ok(entityList);
    }
    
    @CrossOrigin
    @GetMapping("/activity/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable long id) {
        Optional<Activity> entity = service.getActivity(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/activity")
    public ResponseEntity<Activity> addActivity(@Valid @RequestBody Activity e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Activity entity = service.addActivity(e);
        return ResponseEntity.created(new URI("/api/activity" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/activity")
    public ResponseEntity<Activity> updateActivity(@Valid @RequestBody Activity e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Activity entity = service.updateActivity(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable long id) {
        if (service.getActivity(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteActivity(id);
        return ResponseEntity.ok().build();
    }
}