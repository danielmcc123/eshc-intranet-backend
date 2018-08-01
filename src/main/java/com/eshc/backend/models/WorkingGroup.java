package com.eshc.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class WorkingGroup {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String coConvener;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private WorkingGroup parentWorkingGroup;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> actionPoints;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> priorityActions;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> nextMeetingActions;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<WorkingGroup> subWorkingGroups;

    public WorkingGroup() {
    }

    public WorkingGroup(String name, WorkingGroup parentWorkingGroup) {
        this();
        this.name = name;
        this.parentWorkingGroup = parentWorkingGroup;
        actionPoints = new HashSet<>();
        priorityActions = new LinkedHashSet<>();
        priorityActions = new LinkedHashSet<>();
        nextMeetingActions = new LinkedHashSet<>();
        subWorkingGroups = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoConvener() {
        return coConvener;
    }

    public WorkingGroup getParentWorkingGroup() {
        return parentWorkingGroup;
    }

    public void setParentWorkingGroup(WorkingGroup parentWorkingGroup) {
        this.parentWorkingGroup = parentWorkingGroup;
    }

    public void setCoConvener(String coConvener) {
        this.coConvener = coConvener;
    }

    public Set<Long> getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(Set<Long> actionPoints) {
        this.actionPoints = actionPoints;
    }

    public Set<Long> getPriorityActions() {
        return priorityActions;
    }

    public void setPriorityActions(SortedSet<Long> priorityActions) {
        this.priorityActions = priorityActions;
    }

    public Set<Long> getNextMeetingActions() {
        return nextMeetingActions;
    }

    public void setNextMeetingActions(SortedSet<Long> nextMeetingActions) {
        this.nextMeetingActions = nextMeetingActions;
    }

    public Set<WorkingGroup> getSubWorkingGroups() {
        return subWorkingGroups;
    }

    public void setSubWorkingGroups(Set<WorkingGroup> subWorkingGroups) {
        this.subWorkingGroups = subWorkingGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingGroup that = (WorkingGroup) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(coConvener, that.coConvener) &&
                Objects.equals(parentWorkingGroup, that.parentWorkingGroup) &&
                Objects.equals(actionPoints, that.actionPoints) &&
                Objects.equals(priorityActions, that.priorityActions) &&
                Objects.equals(nextMeetingActions, that.nextMeetingActions) &&
                Objects.equals(subWorkingGroups, that.subWorkingGroups);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, coConvener, parentWorkingGroup, actionPoints, priorityActions, nextMeetingActions, subWorkingGroups);
    }
}
