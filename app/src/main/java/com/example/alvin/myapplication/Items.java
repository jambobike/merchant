package com.example.alvin.myapplication;

public class Items {

    public  String key;
    public String editText;
    public String editText2;
    public String editText3;


    public Items(String key, String editText, String editText2, String editText3) {
        this.editText = editText;
        this.editText2 = editText2;
        this.editText3 = editText3;
    }


    public String getEditText() {
        return editText;
    }

    public String getEditText2() {
        return editText2;
    }

    public String getEditText3() {
        return editText3;
    }


    public String getKey() {
        return key;
    }

}
