package com.example.bluedoll.models;

public class Dolls {
    private Integer id;
    private String dollName;
    private String dollDesc;
    private String dollCreator;
    private byte [] dollImage;


    public Dolls(Integer id, String dollName, String dollDesc, String dollCreator, byte [] dollImage) {
        this.id = id;
        this.dollName = dollName;
        this.dollDesc = dollDesc;
        this.dollCreator = dollCreator;
        this.dollImage = dollImage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName;
    }

    public String getDollDesc() {
        return dollDesc;
    }

    public void setDollDesc(String dollDesc) {
        this.dollDesc = dollDesc;
    }

    public String getDollCreator() {
        return dollCreator;
    }

    public void setDollCreator(String dollCreator) {
        this.dollCreator = dollCreator;
    }

    public byte[] getDollImage() {
        return dollImage;
    }

    public void setDollImage(byte[] dollImage) {
        this.dollImage = dollImage;
    }
}
