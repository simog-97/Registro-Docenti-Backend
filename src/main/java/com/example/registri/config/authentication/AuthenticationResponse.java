package com.example.registri.config.authentication;


import com.example.registri.config.tokenWrapper.TokenWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private TokenWrapper token;
    private Long userId;
    private boolean ok;

    public boolean isOk() {
        return  ok;
    }

}
