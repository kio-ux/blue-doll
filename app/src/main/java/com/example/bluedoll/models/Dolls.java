package com.example.bluedoll.models;

public class Dolls {
    private String id;
    private String namaBoneka;
    private String imageDolls;
    private String dollDesc;
    private String dollCreator;

    public Dolls(String id, String namaBoneka, String imageDolls, String dollDesc, String dollCreator) {
        this.id = id;
        this.namaBoneka = namaBoneka;
        this.imageDolls = imageDolls;
        this.dollDesc = dollDesc;
        this.dollCreator = dollCreator;
    }

    @Override
    public String toString() {
        return "Dolls{" +
                "id='" + id + '\'' +
                ", namaBoneka='" + namaBoneka + '\'' +
                ", imageDolls='" + imageDolls + '\'' +
                ", dollDesc='" + dollDesc + '\'' +
                ", dollCreator='" + dollCreator + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaBoneka() {
        return namaBoneka;
    }

    public void setNamaBoneka(String namaBoneka) {
        this.namaBoneka = namaBoneka;
    }

    public String getImageDolls() {
        return imageDolls;
    }

    public void setImageDolls(String imageDolls) {
        this.imageDolls = imageDolls;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dolls dolls = (Dolls) o;
        return getId().equals(dolls.getId()) && getNamaBoneka().equals(dolls.getNamaBoneka()) && getImageDolls().equals(dolls.getImageDolls()) && getDollDesc().equals(dolls.getDollDesc()) && getDollCreator().equals(dolls.getDollCreator());
    }

}
