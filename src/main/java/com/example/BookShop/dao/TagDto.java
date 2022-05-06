package com.example.BookShop.dao;

public class TagDto {

    private String tag;

    public TagDto(String tag) {
        this.tag = tag;
    }

    public TagDto(){}

    public int getTag() {
        return Integer.parseInt(tag);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
