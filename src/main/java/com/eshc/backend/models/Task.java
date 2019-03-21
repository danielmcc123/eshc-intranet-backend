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
    private Set<Long> contributors;

    private boolean completed;
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

    public Set<Long> getContributors() {
        return contributors;
    }

    public void setContributors(Set<Long> contributors) {
        this.contributors = contributors;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
