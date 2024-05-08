package com.example.eventsapp;

public class etud {
    String title;
    String date;
    private String lieu;
    private String description;

public etud(){}

    public etud(String title, String date, String lieu, String description){
        this.date=date;
        this.title=title;
        this.lieu = lieu;
        this.description=description;
    }

    public  String getDate() {
        return date;
    }

    public  String getTitle() {
        return title;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
