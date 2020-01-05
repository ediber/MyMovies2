package com.mymovies2;

import java.util.Date;

public class Movie {

    private String name;
    private String description;
    private Date issueDate;


    public Movie(String name, String description, Date issueDate) {
        this.name = name;
        this.description = description;
        this.issueDate = issueDate;
    }

    public String getName() {
        return name;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

}