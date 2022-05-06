package com.example.BookShop.entity.genre;

import com.example.BookShop.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование жанра
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование жанра
    private String path;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  мнемонический код жанра, используемый в ссылках на страницу данного жанра
    private String slug;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
//  список книг относящихся к данному жанру
    private Set<Book> books;
}