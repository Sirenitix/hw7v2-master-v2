package com.example.BookShop.dao;

import com.example.BookShop.entity.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int num) throws SQLException {
        return new Author(rs.getInt("id"), rs.getString("name"), rs.getString("photo"),rs.getString("slug"),rs.getString("description"));
    }
}