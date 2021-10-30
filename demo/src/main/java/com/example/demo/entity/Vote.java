package com.example.demo.entity;

import io.swagger.annotations.ApiModel;

@ApiModel("投票信息类")
public class Vote {
    private Integer id;
    private String username;
    private String title;
    private String describe;
    private String selection;

    public Vote(Integer id, String username, String title, String describe, String selection) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.describe = describe;
        this.selection = selection;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", selection='" + selection + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
