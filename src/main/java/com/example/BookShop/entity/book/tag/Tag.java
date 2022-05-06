package com.example.BookShop.entity.book.tag;

import com.example.BookShop.entity.Book;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование тэга
    private String name;

    @ManyToMany(mappedBy = "tags")
//  список книг относящихся к данному тэгу
    private Set<Book> books;
}