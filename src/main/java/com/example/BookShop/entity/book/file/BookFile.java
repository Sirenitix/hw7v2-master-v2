package com.example.BookShop.entity.book.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_file")
@NoArgsConstructor
@AllArgsConstructor
public class BookFile {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(columnDefinition = "VARCHAR(255) NOT NULL")
        private String hash;

        @Column(columnDefinition = "VARCHAR(255) NOT NULL")
        private String path;

        @Column(columnDefinition = "INT NOT NULL  DEFAULT 0")
        private String typeId;
}
