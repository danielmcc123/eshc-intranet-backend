package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long Id;

    LocalDateTime dateTime;

    String noteBody;

    public Note() {

    }
}
