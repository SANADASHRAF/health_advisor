package com.example.health_advisor.objects;


import java.io.Serializable;

public class favourite_meal implements Serializable {
    private String name;
    private String categorie;
    private int img;
    private int rate;
    private String details;
    int id;

    public favourite_meal(int id,String name, int img, String details, int rate, String categorie_name) {
        this.name = name;
        this.categorie = categorie;
        this.rate = rate;
        this.details = details;
        this.img=img;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
