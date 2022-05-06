package com.example.BookShop.dao;

import com.example.BookShop.entity.book.links.Book2Tag;
import com.example.BookShop.entity.book.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface Book2TagRepository extends JpaRepository<Book2Tag,Integer> {

    List<Book2Tag> findAllByOrderById();
}
