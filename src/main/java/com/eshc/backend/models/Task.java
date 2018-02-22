package com.eshc.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task {
    //*************************************************Properties*******************************************************
    @Id @GeneratedValue
    private Long Id;

    private String description;

    @OneToMany
    private List<Note> notes;

    @OneToOne
    private Member leadContributor;

    @OneToMany
    private List<Member> listOfContributors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTimeCreated;

    //*************************************************Constructors*****************************************************
    public Task() {
    }

    //*********************************************Getters and Setters**************************************************
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Member getLeadContributor() {
        return leadContributor;
    }

    public void setLeadContributor(Member leadContributor) {
        this.leadContributor = leadContributor;
    }

    public List<Member> getListOfContributors() {
        return listOfContributors;
    }

    public void setListOfContributors(List<Member> listOfContributors) {
        this.listOfContributors = listOfContributors;
    }


    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }
}
