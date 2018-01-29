package com.eshc.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    public Member() {

    }

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String LastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
