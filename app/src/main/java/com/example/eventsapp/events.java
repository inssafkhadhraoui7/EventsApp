package com.example.eventsapp;

public class events {
    String title;
    String date;
    private String lieu;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public events(){}

    public events(String title, String date, String lieu, String description) {
        this.title = title;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
    }
}
