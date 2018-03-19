package com.eshc.backend.models;

import com.eshc.backend.working.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class ActionPoint {
    //*************************************************Properties*******************************************************
    @Id @GeneratedValue
    private Long id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    private Long leadContributor;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> listOfContributors;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> listOfWatchers;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> tasks;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> listOfNotes;

    private Status currentStatus;

    private String description;

    //*************************************************Constructors*****************************************************
    public ActionPoint() {

    }

    public ActionPoint(String title) {
        this.title = title;
        this.currentStatus = Status.UNSTARTED;
    }

    public ActionPoint(String title, Status status) {
        this.title = title;
        this.currentStatus = status;
    }

    //*********************************************Getters and Setters**************************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getLeadContributor() {
        return leadContributor;
    }

    public void setLeadContributor(Long leadContributor) {
        this.leadContributor = leadContributor;
    }

    public Set<Long> getListOfContributors() { return listOfContributors; }

    public void setListOfContributors(Set<Long> listOfContributors) {
        this.listOfContributors = listOfContributors;
    }

    public Set<Long> getListOfWatchers() {
        return listOfWatchers;
    }

    public void setListOfWatchers(Set<Long> listOfWatchers) {
        this.listOfWatchers = listOfWatchers;
    }

    public Set<Long> getListOfNotes() {
        return listOfNotes;
    }

    public void setListOfNotes(Set<Long> listOfNotes) {
        this.listOfNotes = listOfNotes;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Long> tasks) { this.tasks = tasks;
    }
}
