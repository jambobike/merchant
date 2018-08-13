package com.example.alvin.myapplication;

public class Users {
    public String fnames;
    public String email;

    public String getFnames() {
        return fnames;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String phone;

    public Users(String fnames, String email, String phone) {
        this.fnames = fnames;
        this.email = email;
        this.phone = phone;
    }
}
