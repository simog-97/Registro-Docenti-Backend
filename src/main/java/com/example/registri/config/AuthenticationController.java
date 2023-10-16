package com.example.registri.config;

import com.example.registri.config.authentication.AuthenticationRequest;
import com.example.registri.config.authentication.AuthenticationResponse;
import com.example.registri.config.tokenWrapper.TokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        String username = request.getUsername();
        String password = request.getPassword();
        AuthenticationResponse res = authenticationService.authenticate(username, password);
        if(!res.isOk())
            return ResponseEntity.status(403).body(res);
        return ResponseEntity.ok(res);
    }
    @CrossOrigin
    @PostMapping("/deauthenticate")
    public ResponseEntity deauthenticate(
            @RequestBody TokenWrapper token
    ) {
        boolean res = authenticationService.deauthenticate(token);
        if(!res)
            return ResponseEntity.status(400).build();
        return ResponseEntity.ok().build();
    }


}
