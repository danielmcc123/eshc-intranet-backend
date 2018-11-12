package com.eshc.backend.models;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Audited
public class Task extends BaseEntity {
    //*************************************************Properties*******************************************************
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> notes;

    private Long leadContributor;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> listOfContributors;
    //*************************************************Constructors*****************************************************
    public Task() {
    }
    //*********************************************Getters and Setters**************************************************

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getNotes() {
        return notes;
    }

    public void setNotes(Set<Long> notes) {
        this.notes = notes;
    }

    public Long getLeadContributor() {
        return leadContributor;
    }

    public void setLeadContributor(Long leadContributor) {
        this.leadContributor = leadContributor;
    }

    public Set<Long> getListOfContributors() {
        return listOfContributors;
    }

    public void setListOfContributors(Set<Long> listOfContributors) {
        this.listOfContributors = listOfContributors;
    }
}
