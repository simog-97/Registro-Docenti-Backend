package com.example.registri.tables.register;

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

import com.example.registri.tables.activity.Activity;
import com.example.registri.tables.activity.ActivityService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @Autowired 
    private ActivityService activityService;

    @CrossOrigin
    @GetMapping("/register")
    public ResponseEntity<List<Register>> getAllRegisters() {
        List<Register> entityList = service.getAllRegisters();
        return ResponseEntity.ok(entityList);
    }

    @CrossOrigin
    @GetMapping("/register/{id}")
    public ResponseEntity<Register> getRegister(@PathVariable long id) {
        Optional<Register> entity = service.getRegister(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<Register> addRegister(@Valid @RequestBody Register e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Register entity = service.addRegister(e);
        return ResponseEntity.created(new URI("/api/register" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/register")
    public ResponseEntity<Register> updateRegister(@Valid @RequestBody Register e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Register entity = service.updateRegister(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/register/{id}")
    public ResponseEntity<Void> deleteRegister(@PathVariable long id) {
        if (service.getRegister(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteRegister(id);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin
    @GetMapping("/register/{id}/activities")
    public ResponseEntity<List<Activity>> getRegistersByRegisterId(@PathVariable long id) {
        List<Activity> attivita = activityService.getByRegisterId(id);
        return ResponseEntity.ok(attivita);
    }

}