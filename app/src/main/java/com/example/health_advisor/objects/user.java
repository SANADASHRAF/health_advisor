package com.example.health_advisor.objects;

public class user {
    private String username;
    private String password;
    private String location;
    private String phone;
    private String img_uri;


    public user(String username, String password, String phone, String location,String img_uri) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.img_uri=img_uri;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
