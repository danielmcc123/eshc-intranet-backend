package com.eshc.backend.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Comment extends BaseEntity {
    //*************************************************Properties*******************************************************
    private Long author;

    @NotBlank(message = "Comment must not be empty")
    private String body;

    //*************************************************Constructors*****************************************************
    public Comment() {

    }
    //*********************************************Getters and Setters**************************************************

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
