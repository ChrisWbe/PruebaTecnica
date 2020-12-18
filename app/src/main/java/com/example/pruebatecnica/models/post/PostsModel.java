package com.example.pruebatecnica.models.post;

import org.json.JSONException;
import org.json.JSONObject;

public class PostsModel {

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

    public PostsModel(int userId, int id, String title, String body, String name, String username, String email, String phone, String website, int imgResource){
        this.userId = userId;
        this.id=id;
        this.title=title;
        this.body=body;
        this.name=name;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.website=website;
        this.imgResource=imgResource;
    }

    public PostsModel(JSONObject item) throws JSONException {
        this.userId=item.getInt("userId");
        this.id=item.getInt("id");
        this.title=item.getString("title");
        this.body=item.getString("body");
        this.name=item.getString("name");
        this.username=item.getString("username");
        this.email=item.getString("email");
        this.phone=item.getString("phone");
        this.website=item.getString("website");
        this.imgResource=item.getInt("imgResource");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public JSONObject getObject() throws JSONException {
        JSONObject item = new JSONObject();
        item.put("userId",this.userId);
        item.put("id",this.id);
        item.put("title",this.title);
        item.put("body",this.body);
        item.put("name",this.name);
        item.put("username",this.username);
        item.put("email",this.email);
        item.put("phone",this.phone);
        item.put("website",this.website);
        item.put("imgResource", this.imgResource);
        return item;
    }
}
