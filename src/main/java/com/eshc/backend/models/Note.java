package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Note extends BaseEntity {
    //*************************************************Properties*******************************************************
    private Long author;

    @NotBlank
    private String noteBody;

    //*************************************************Constructors*****************************************************
    public Note() {

    }
    //*********************************************Getters and Setters**************************************************

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
