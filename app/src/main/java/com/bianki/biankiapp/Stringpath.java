package com.bianki.biankiapp;

public class Stringpath {

    String path;
    String type;
    String text;
    String name ;
    String photo ;

    public Stringpath(String path, String type, String text , String name , String photo) {
        this.path = path;
        this.type = type ;
        this.text = text ;
        this.name = name;
        this.photo = photo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
