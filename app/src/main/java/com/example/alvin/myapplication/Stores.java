package com.example.alvin.myapplication;

public class Stores {

    public  String key;
    public String bsname;
    public String email;
    public String location;
    public String spinner1;


    public Stores(String key, String bsname, String email, String location, String spinner1) {
        this.bsname = bsname;
        this.email = email;
        this.location = location;
        this.spinner1 = spinner1;
    }


    public String getBsname() {
        return bsname;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }


    public String getSpinner1() {
        return spinner1;
    }

    public String getKey() {
        return key;
    }
}