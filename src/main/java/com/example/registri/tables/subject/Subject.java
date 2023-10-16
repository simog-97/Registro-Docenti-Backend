package com.example.registri.tables.subject;

import com.example.registri.tables.degree.Degree;

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
public class Subject {

    @Id
    @SequenceGenerator(
        name = "subject_sequence",
        sequenceName = "subject_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "subject_sequence"
    )
    private Long id;

    @Valid
    @ManyToOne
    private Degree degree;

    private String name;
    private Integer cfu;



    public Subject() {
    }

    public Subject(Degree degree, String name, Integer cfu) {
        this.degree = degree;
        this.name = name;
        this.cfu = cfu;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Degree getDegree() {
        return this.degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCfu() {
        return this.cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", degree='" + getDegree() + "'" +
            ", name='" + getName() + "'" +
            ", cfu='" + getCfu() + "'" +
            "}";
    }



}