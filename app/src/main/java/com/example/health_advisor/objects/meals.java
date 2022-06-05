package com.example.health_advisor.objects;

import java.io.Serializable;

public class meals implements Serializable {
    private String name;
    private int img;
    private String details;
    private int rate;
    private String categorie_name;
    private int id;
    private int color;
    private String favourite;

    public meals(int id,String name, int img, String details, int rate, String categorie_name,  int color,String favourite) {
        this.name = name;
        this.img = img;
        this.details = details;
        this.rate = rate;
        this.categorie_name = categorie_name;
        this.id=id;
        this.favourite=favourite;
        this.color=color;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCategorie_name() {
        return categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        this.categorie_name = categorie_name;
    }
}
