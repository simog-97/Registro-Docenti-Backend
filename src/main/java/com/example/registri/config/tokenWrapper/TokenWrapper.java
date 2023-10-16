package com.example.registri.config.tokenWrapper;


import com.example.registri.tables.professor.Professor;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenWrapper {


    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private boolean isExpired;

    @Valid
    @ManyToOne
    private Professor professor;


}
