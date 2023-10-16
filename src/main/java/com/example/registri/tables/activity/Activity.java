package com.example.registri.tables.activity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.registri.tables.register.Register;

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
public class Activity {

    @Id
    @SequenceGenerator(
        name = "activity_sequence",
        sequenceName = "activity_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "activity_sequence"
    )
    private Long id;

    @Valid
    @ManyToOne
    private Register register;

    private String activityType;
    private String topics;
    private String classroom;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Activity() {
    }


    public Activity(Register register, String activityType, String topics, String classroom, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.register = register;
        this.activityType = activityType;
        this.topics = topics;
        this.classroom = classroom;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Register getRegister() {
        return this.register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTopics() {
        return this.topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getClassroom() {
        return this.classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", register='" + getRegister() + "'" +
            ", activityType='" + getActivityType() + "'" +
            ", topics='" + getTopics() + "'" +
            ", classroom='" + getClassroom() + "'" +
            ", date='" + getDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }




}