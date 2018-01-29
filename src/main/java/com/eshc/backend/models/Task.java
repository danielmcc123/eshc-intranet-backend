package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Task {

    @Id @GeneratedValue
    private Long Id;

    String description;

    @OneToMany
    List<Note> notes;

    public Task() {
    }
}
