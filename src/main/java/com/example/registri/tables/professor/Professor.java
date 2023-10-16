package com.example.registri.tables.professor;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Professor {

    @Id
    @SequenceGenerator(
        name = "professor_sequence",
        sequenceName = "professor_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "professor_sequence"
    )
    private Long id;

    private String name;
    private String surname;
    private LocalDate dob;
    private String email;
    private String password;

}