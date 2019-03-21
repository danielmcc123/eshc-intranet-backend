package com.eshc.backend.models;

import com.eshc.backend.working.Status;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Audited
public class ActionPoint extends BaseEntity {
    //*************************************************Properties*******************************************************

    @NotBlank(message = "Cannot be blank")
    @Size(min = 2, message = "Please supply a useful title")
    private String title;

    private Long leadContributor;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> contributors;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> watchers;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> tasks;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> comments;

    private Status status;

    @Column(columnDefinition="TEXT")
    private String description;

    private String category;


    //*************************************************Constructors*****************************************************
    public ActionPoint() {
        this.status = Status.UNSTARTED;
        tasks = new HashSet<>();
        contributors = new HashSet<>();
        watchers = new HashSet<>();
        comments = new HashSet<>();
    }

    public ActionPoint(String title) {
        this();
        this.title = title;

    }

    public ActionPoint(String title, Status status) {
        this(title);
        this.status = status;
    }

    //*********************************************Getters and Setters**************************************************
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<Long> getWatchers() {
        return watchers;
    }

    public void setWatchers(Set<Long> watchers) {
        this.watchers = watchers;
    }

    public Set<Long> getListOfComments() {
        return comments;
    }

    public void setComments(Set<Long> comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                Objects.equals(leadContributor, that.leadContributor) &&
                Objects.equals(contributors, that.contributors) &&
                Objects.equals(watchers, that.watchers) &&
                Objects.equals(tasks, that.tasks) &&
                Objects.equals(comments, that.comments) &&
                status == that.status &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, leadContributor, contributors, watchers, tasks, comments, status, description, category);
    }
}
