package com.example.registri.config;

import com.example.registri.config.authentication.AuthenticationResponse;
import com.example.registri.config.tokenWrapper.TokenWrapper;
import com.example.registri.config.tokenWrapper.TokenWrapperService;
import com.example.registri.tables.professor.Professor;
import com.example.registri.tables.professor.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private TokenWrapperService tokenService;
    @Autowired
    private ProfessorService professorService;

    private List<TokenWrapper> validTokens;


    private boolean containsName(List<TokenWrapper> list, String tkn) {
        return list.stream().anyMatch(p -> p.getToken().equals(tkn));
    }

//    private String generateToken(String name, String pwd, Long id) {
//        log.info("generazione del token");
//        Random rand = new Random();
//        String tkn = new String("Token_");
//        Integer randint = rand.nextInt()%1000;
////        tkn = tkn.concat(name.substring(0,4) + "_").concat(id.toString() + "_").concat(randint.toString());
//        tkn = tkn.concat(randint.toString()).concat("_pId-").concat(id.toString());
//        return  tkn;
//    }

    private String generateTokenString(Long tokenId, Long profId) {
        String tkn = new String("Token_");
        tkn = tkn.concat(tokenId.toString()).concat("_P-").concat(profId.toString());
        return  tkn;
    }

//    private void removeExpiredtokens() {
//        validTokens = tokenService.getAllTokenWrappers();
//        for(TokenWrapper tok : validTokens) {
//            if(tok.isExpired()) {
//                tokenService.deleteTokenWrapper(tok.getId());
//            }
//        }
//    }

    public boolean isTokenValid (String token) {
//        removeExpiredtokens();
        validTokens = tokenService.getAllTokenWrappers();
        if(containsName(validTokens, token))
            return true;
        return false;
    }

    public boolean checkTokenMatch (String tokenString , Long profId ) {
        validTokens = tokenService.getAllTokenWrappers();
        Optional<TokenWrapper> token = validTokens.stream()
                .filter(t -> t.getToken().equals(tokenString))
                .findFirst();

        if(token.isPresent() ) {
            Long profIdToCompare = token.get().getProfessor().getId();
            if (profIdToCompare == profId) return true;
        }
        return false;
    }


    public AuthenticationResponse authenticate(String username, String password) {
//        removeExpiredtokens();
        List<Professor> professorList = professorService.getAllProfessors();
        String tkn;
        AuthenticationResponse resp;
        for (Professor prof : professorList) {
            if(prof.getEmail().equals(username) && prof.getPassword().equals(password)){
//                tkn = generateToken(username, password, prof.getId());

                TokenWrapper token = TokenWrapper.builder()
                        .professor(prof)
                        .build();

                tokenService.addTokenWrapper(token);

                tkn = generateTokenString(token.getId(), prof.getId());
                token.setToken(tkn);
                tokenService.updateTokenWrapper(token);

                resp = AuthenticationResponse.builder()
                        .token(token)
                        .userId(prof.getId())
                        .ok(true)
                        .build();
                return resp;
            }
        }
        resp = AuthenticationResponse.builder().ok(false).build();
        return resp;
    }



    public boolean deauthenticate (TokenWrapper token) {
        Optional<TokenWrapper>  tokenToDelete = tokenService.getTokenWrapper(token.getId());
        if(tokenToDelete.isPresent()){
//            log.info("trovato token: " + tokenToDelete.get().toString());
            tokenService.deleteTokenWrapper(tokenToDelete.get().getId());
            return true;
        }
//        log.info("Token non trovato");
        return false;
    }
}



