package com.example.alvin.myapplication;

public class Stores {

    public String Bsname;
    public String email;
    public String location;
    public String spinner1;

    //required default constructor
    public Stores() {
    }

    public Stores(String Bsname, String email, String location, String spinner1) {
        this.Bsname = Bsname;
        this.email = email;
        this.location = location;
        this.spinner1 = spinner1;
    }

    public String getBsname() {
        return Bsname;
    }

    public void setBsname(String Bsname) {
        this.Bsname = Bsname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpinner1() {
        return spinner1;
    }

    public void setSpinner1(String spinner1) {
        this.spinner1 = spinner1;
    }
}