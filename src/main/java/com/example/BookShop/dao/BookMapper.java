package com.example.BookShop.dao;

import com.example.BookShop.entity.Author;
import com.example.BookShop.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper implements RowMapper<Book> {

    //нужен здесь т.к. авторы храняться в отдельной таблице
    private final JdbcTemplate jdbcTemplate;
    private final  List<Author> authorData = new ArrayList<>();

    @Autowired
    public BookMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
//         получим автора из таблицы авторов по id указанной в таблице book
        Author author = jdbcTemplate.queryForObject(
                "SELECT * FROM author WHERE id = \n" + "(SELECT author_id FROM book2author WHERE book_id = ? LIMIT 1)",
                     new AuthorMapper(),
                     rs.getInt("id")
        );

        assert author != null;
        authorData.add(author);

        return new Book(
                rs.getInt("id"),
                authorData,
                rs.getInt("isBestseller"),
                rs.getString("slug"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("title"),
                rs.getInt("price"),
                rs.getInt("discount")
        );
    }


}