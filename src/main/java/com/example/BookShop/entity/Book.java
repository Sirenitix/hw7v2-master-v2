package com.example.BookShop.entity;

import com.example.BookShop.entity.book.review.BookReview;
import com.example.BookShop.entity.book.tag.Tag;
import com.example.BookShop.entity.genre.Genre;
import com.example.BookShop.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
//  id книги
    private Integer id;

    @Column(columnDefinition = "TEXT")
//  описание книги
    private String description;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
//  скидка в процентах или 0, если её нет
    private Integer discount;

    @Column(columnDefinition = "VARCHAR(255)")
//  изображение обложки
    private String image;

    @Column(name = "is_bestseller",
            columnDefinition = "SMALLINT NOT NULL")
//  книга очень популярна, является бестселлером
    private Integer isBestseller;

    @Column(columnDefinition = "INT NOT NULL")
//  цена в рублях основная
    private Integer price;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
//  дата публикации
    private LocalDate pubDate;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  мнемонический идентификатор книги
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  название книги
    private String title;

    @Column(name = "soldbooks", columnDefinition = "INT")
    @JsonProperty("soldbooks")
    @ApiModelProperty("number of users who bought the book.")
    private int soldBooks;

    @Column(name = "booksincart", columnDefinition = "INT")
    @JsonProperty("booksincart")
    @ApiModelProperty("Number of users with the book in the shopping cart")
    private int booksInCart;

    @Column(name = "deferredbooks", columnDefinition = "INT")
    @JsonProperty("deferredbooks")
    @ApiModelProperty("Number of users whose book is deferred")
    private int deferredbooks;

    @ManyToMany
    @JoinTable(name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
//  список авторов данной книги
    private List<Author> authors;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
//  список жанров к которым относится данная книга
    private List<Genre> genres;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book2tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
//  список тэгов к которым относится данная книга
    private List<Tag> tags;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей имеющих связь с данной книгой
    private List<UserEntity> users;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "file_download",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//  список пользователей скачавших данную книгу
    private List<UserEntity> usersDownloadedBooks;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей имеющих транзакции с книгой (списание или зачисление)
    private List<UserEntity> usersBalanceTransactions;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей оставивших отзывы о книге
    private List<UserEntity> usersBookReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
//  список отзывов данной книги
    private List<BookReview> bookReviewList;

    @JsonProperty
    public Integer discountPrice() {
        return price - Math.toIntExact(Math.round(price * ((float)discount / 100)));
    }

    public Book(int id, List<Author> authors,Integer isBestseller, String slug, String image, String description, String title, int price, int discount) {
        this.id = id;
        this.authors = authors;
        this.isBestseller = isBestseller;
        this.slug = slug;
        this.image = image;
        this.description = description;
        this.title = title;
        this.price = price;
        this.discount = discount;
    }
}
