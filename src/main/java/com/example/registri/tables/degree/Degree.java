package com.example.registri.tables.degree;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Degree {

    @Id
    @SequenceGenerator(
        name = "degree_sequence",
        sequenceName = "degree_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "degree_sequence"
    )
    private Long id;

    private String name;
    private String description;

    public Degree() {
    }

    public Degree(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}