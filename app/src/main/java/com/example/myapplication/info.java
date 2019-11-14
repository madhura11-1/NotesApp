package com.example.myapplication;

public class info {

    public String notes;
    private String id;

    public info(String notes) {
        this.notes=notes;
        id="";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }
}
