package com.example.pruebatecnica.models.home;

public class HomeModel {

    private int userId;
    private int id;
    private String title;
    private String body;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private int imgResource;

    public HomeModel(int userId, int id, String title, String body, int imgResource){
        this.userId = userId;
        this.id=id;
        this.title=title;
        this.body=body;
        this.imgResource=imgResource;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
