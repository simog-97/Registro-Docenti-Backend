package com.example.registri.config.tokenWrapper;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenWrapperService {

    @Autowired
    private TokenWrapperRepository repository;

    public TokenWrapper addTokenWrapper(TokenWrapper e) {
        return repository.save(e);
    }

    public Optional<TokenWrapper> getTokenWrapper(Long id) {
        return repository.findById(id);
    }

    public List<TokenWrapper> getAllTokenWrappers() {
        List<TokenWrapper> output = new ArrayList<TokenWrapper>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public TokenWrapper updateTokenWrapper(TokenWrapper e) {
        return repository.save(e);
    }

    public void deleteTokenWrapper(TokenWrapper e) {
        repository.delete(e);
    }

    public void deleteTokenWrapper(Long id) {
        repository.deleteById(id);
    }

}