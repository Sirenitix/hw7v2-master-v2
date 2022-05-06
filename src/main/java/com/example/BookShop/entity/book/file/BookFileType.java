package com.example.BookShop.entity.book.file;

import com.example.BookShop.entity.enums.BookFileTypeEnum;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book_file_type")
public class BookFileType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @Enumerated(EnumType.STRING)
    private BookFileTypeEnum name;
}