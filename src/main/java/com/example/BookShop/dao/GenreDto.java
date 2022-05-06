package com.example.BookShop.dao;

public class GenreDto {
    private String genre;

    public GenreDto(String genre) {
        this.genre = genre;
    }

    public GenreDto(){}

    public int getGenre() {
        return Integer.parseInt(genre);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
