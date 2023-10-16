package com.example.registri.tables.professor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.example.registri.config.AuthenticationService;
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

import com.example.registri.tables.register.Register;
import com.example.registri.tables.register.RegisterService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AuthenticationService authService;

    @CrossOrigin
    @GetMapping("/professor")
    public ResponseEntity getAllProfessors(@RequestHeader("Authorization") String token) {
        if (!authService.isTokenValid(token)) //Controlla se il token è valido
            return ResponseEntity.status(403).build();

        List<Professor> entityList = service.getAllProfessors();
        return ResponseEntity.ok(entityList);
    }

    @CrossOrigin
    @GetMapping("/professor/{id}")
    public ResponseEntity<Professor> getProfessor(
            @RequestHeader("Authorization") String token,
            @PathVariable long id
    ) {
        if (!authService.isTokenValid(token)) //Controlla se il token è valido
            return ResponseEntity.status(403).build();

        if(!authService.checkTokenMatch(token, id)) //Controlla che il token sia associato al giusto user
            return ResponseEntity.status(403).build();


        Optional<Professor> entity = service.getProfessor(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/professor")
    public ResponseEntity<Professor> addProfessor(@Valid @RequestBody Professor e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Professor entity = service.addProfessor(e);
        return ResponseEntity.created(new URI("/api/professor" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/professor")
    public ResponseEntity<Professor> updateProfessor(@Valid @RequestBody Professor e, @RequestHeader("Authorization") String token) {
        if (!authService.isTokenValid(token)) //Controlla se il token è valido
            return ResponseEntity.status(403).build();
        if(!authService.checkTokenMatch(token, e.getId())) //Controlla che il token sia associato al giusto user
            return ResponseEntity.status(403).build();
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Professor entity = service.updateProfessor(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/professor/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable long id, @RequestHeader("Authorization") String token) {
        if (!authService.isTokenValid(token)) //Controlla se il token è valido
            return ResponseEntity.status(403).build();
        if(!authService.checkTokenMatch(token, id)) //Controlla che il token sia associato al giusto user
            return ResponseEntity.status(403).build();

        if (service.getProfessor(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteProfessor(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/professor/{id}/registers")
    public ResponseEntity<List<Register>> getRegistersByProfessorId(@PathVariable long id, @RequestHeader("Authorization") String token) {
        if (!authService.isTokenValid(token)) //Controlla se il token è valido
            return ResponseEntity.status(403).build();
        if(!authService.checkTokenMatch(token, id)) //Controlla che il token sia associato al giusto user
            return ResponseEntity.status(403).build();

        List<Register> registri = registerService.getByProfessorId(id);
        return ResponseEntity.ok(registri);
    }
}