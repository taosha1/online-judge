package com.tc.domain;

import java.io.Serializable;

public class Competition implements Serializable{
    private Integer id;
    private User sponsor;
    private String title;
    private String startDate;
    private boolean isOpen;
    private String endDate;
    private String languageType;
    private String discription;

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", sponsor=" + sponsor +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", isOpen=" + isOpen +
                ", endDate='" + endDate + '\'' +
                ", languageType='" + languageType + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }

    public Competition(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
