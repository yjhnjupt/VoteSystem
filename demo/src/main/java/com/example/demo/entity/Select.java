package com.example.demo.entity;

import io.swagger.annotations.ApiModel;

@ApiModel("选项信息类")
public class Select {
    private Integer num;
    private Integer count;
    private String selectionText;

    public Select(Integer num, Integer count, String selectionText) {
        this.num = num;
        this.count = count;
        this.selectionText = selectionText;
    }

    @Override
    public String toString() {
        return "Select{" +
                "num=" + num +
                ", count=" + count +
                ", selectionText='" + selectionText + '\'' +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }
}
