package com.eshc.backend.models;

import com.eshc.backend.working.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ActionPoint {
    //*************************************************Properties*******************************************************
    @Id @GeneratedValue
    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    @OneToOne
    private Member leadContributor;

    @OneToMany
    private List<Member> listOfContributors;

    @OneToMany
    private List<Member> listOfWatchers;

    @OneToMany
    private List<Task> tasks;

    @OneToMany
    private List<Note> listOfNotes;

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

    public List<Member> getListOfWatchers() {
        return listOfWatchers;
    }

    public void setListOfWatchers(List<Member> listOfWatchers) {
        this.listOfWatchers = listOfWatchers;
    }

    public List<Note> getListOfNotes() {
        return listOfNotes;
    }

    public void setListOfNotes(List<Note> listOfNotes) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
