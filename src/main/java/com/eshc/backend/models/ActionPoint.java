package com.eshc.backend.models;

import com.eshc.backend.working.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Audited
public class ActionPoint {
    //*************************************************Properties*******************************************************
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @CreationTimestamp
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @UpdateTimestamp
    private LocalDateTime lastModified;

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

    private String category;


    //*************************************************Constructors*****************************************************
    public ActionPoint() {
        this.currentStatus = Status.UNSTARTED;
        tasks = new HashSet<>();
        listOfContributors = new HashSet<>();
        listOfWatchers = new HashSet<>();
        listOfNotes = new HashSet<>();
    }

    public ActionPoint(String title) {
        this();
        this.title = title;

    }

    public ActionPoint(String title, Status status) {
        this(title);
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public void setTasks(Set<Long> tasks) {
        this.tasks = tasks;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionPoint that = (ActionPoint) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(created, that.created) &&
                Objects.equals(lastModified, that.lastModified) &&
                Objects.equals(leadContributor, that.leadContributor) &&
                Objects.equals(listOfContributors, that.listOfContributors) &&
                Objects.equals(listOfWatchers, that.listOfWatchers) &&
                Objects.equals(tasks, that.tasks) &&
                Objects.equals(listOfNotes, that.listOfNotes) &&
                currentStatus == that.currentStatus &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, created, lastModified, leadContributor, listOfContributors, listOfWatchers, tasks, listOfNotes, currentStatus, description, category);
    }
}
