package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Member {

    //*************************************************Properties*******************************************************
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String LastName;
    private String homeAddress;
    private LocalDate dob;

    //*************************************************Constructors*****************************************************
    public Member() {

    }

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    //*********************************************Getters and Setters**************************************************

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

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
