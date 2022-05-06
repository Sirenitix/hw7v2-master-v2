package com.example.BookShop.dao;

import com.example.BookShop.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String> {

    List<Author> findAll();

    Author findAuthorById(Integer authorId);
}
