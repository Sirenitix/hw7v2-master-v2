package com.example.BookShop.entity.book.links;

import com.example.BookShop.entity.enums.Book2UserTypeEnum;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book2user_type")
public class Book2UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
//  id связи типа книги к юзеру
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  код типа привязки
    private String code;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование типа привязки
    @Enumerated(EnumType.STRING)
    private Book2UserTypeEnum name;
}
