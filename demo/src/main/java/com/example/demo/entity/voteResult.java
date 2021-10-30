package com.example.demo.entity;

import java.util.List;

public class voteResult {
    private Integer id;
    private String username;
    private String title;
    private String describe;
    private List<Select> select;

    public voteResult(Integer id, String username, String title, String describe, List<Select> select) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.describe = describe;
        this.select = select;
    }

    @Override
    public String toString() {
        return "voteResult{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", select=" + select +
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

    public List<Select> getSelect() {
        return select;
    }

    public void setSelect(List<Select> select) {
        this.select = select;
    }
}
