package com.example.BookShop.dao;

import com.example.BookShop.entity.book.links.Book2Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Book2GenreRepository extends JpaRepository<Book2Genre, Integer> {
    List<Book2Genre> findAllByOrderById();
}
