package com.example.registri.tables.register;


import com.example.registri.tables.professor.Professor;
import com.example.registri.tables.subject.Subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table
public class Register {

    @Id
    @SequenceGenerator(
        name = "register_sequence",
        sequenceName = "register_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "register_sequence"
    )
    private Long id;

    @Valid
    @ManyToOne
    private Professor professor;

    @Valid
    @ManyToOne
    private Subject subject;

    Integer year;

    public Register() {
    }


    public Register(Professor professor, Subject subject, Integer year) {
        this.professor = professor;
        this.subject = subject;
        this.year = year;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", professor='" + getProfessor() + "'" +
            ", subject='" + getSubject() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }
}