package com.example.BookShop.service;

import com.example.BookShop.dao.AuthorRepository;
import com.example.BookShop.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<Author>> getAlphabetAndAuthors() {
        return authorRepository.findAll().stream().collect(Collectors.groupingBy((Author author) -> author.getName().substring(0, 1)));
    }

    public Author getAuthorById(Integer authorId){
        return authorRepository.findAuthorById(authorId);
    }

}
