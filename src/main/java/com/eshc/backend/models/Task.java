package com.eshc.backend.models;

import javax.persistence.*;
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
}
