package com.example.registri.config.tokenWrapper;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class TokenWrapperController {

    @Autowired
    private TokenWrapperService service;

    @CrossOrigin
    @GetMapping("/token")
    public ResponseEntity<List<TokenWrapper>> getAllTokenWrappers() {
        List<TokenWrapper> entityList = service.getAllTokenWrappers();
        return ResponseEntity.ok(entityList);
    }

    @CrossOrigin
    @GetMapping("/token/{id}")
    public ResponseEntity<TokenWrapper> getTokenWrapper(@PathVariable long id) {
        Optional<TokenWrapper> entity = service.getTokenWrapper(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/token")
    public ResponseEntity<TokenWrapper> addTokenWrapper(@Valid @RequestBody TokenWrapper e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        TokenWrapper entity = service.addTokenWrapper(e);
        return ResponseEntity.created(new URI("/api/auth/token" + entity.getId())).body(entity);
    }

    @CrossOrigin
    @PutMapping("/token")
    public ResponseEntity<TokenWrapper> updateTokenWrapper(@Valid @RequestBody TokenWrapper e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        TokenWrapper entity = service.updateTokenWrapper(e);
        return ResponseEntity.ok(entity);
    }

    @CrossOrigin
    @DeleteMapping("/token/{id}")
    public ResponseEntity<Void> deleteTokenWrapper(@PathVariable long id) {
        if (service.getTokenWrapper(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteTokenWrapper(id);
        return ResponseEntity.ok().build();
    }


}
