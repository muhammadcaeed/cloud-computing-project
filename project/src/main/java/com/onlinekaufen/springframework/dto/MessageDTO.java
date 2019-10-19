package com.onlinekaufen.springframework.dto;


import java.sql.Date;

public class MessageDTO {


    private String firstName;
    private String lastName;
    private String emailID;
    private long id;
    private String sent_date;
    private String message;
    private long author_id;
    private boolean read;


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSent_date() {
        return this.sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public boolean isRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


}
