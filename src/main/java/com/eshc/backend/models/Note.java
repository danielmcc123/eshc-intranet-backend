package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Note {
    //*************************************************Properties*******************************************************
    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    private Member Author;

    private LocalDateTime dateTimeCreated;

    private String noteBody;

    //*************************************************Constructors*****************************************************
    public Note() {

    }

    //*********************************************Getters and Setters**************************************************
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Member getAuthor() {
        return Author;
    }

    public void setAuthor(Member author) {
        Author = author;
    }

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
