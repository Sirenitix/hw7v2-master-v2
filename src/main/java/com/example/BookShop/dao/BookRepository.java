package com.example.BookShop.dao;

import com.example.BookShop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("from Book")
    List<Book> customFindAllBooks();

    List<Book> findBooksByTitleContainingIgnoreCase(String bookTitle);

    List<Book> findBooksByPriceIsBetween(Integer min, Integer max);

    @Query(value="SELECT * FROM book ORDER BY pub_date DESC", nativeQuery=true)
    Page<Book> getBooks(Pageable nextPage);


    @Query(value="SELECT * FROM book WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery=true)
    List<Book> getBooksWithMaxDiscount();

    @Query(value="SELECT * FROM book ORDER BY (soldbooks+((7*booksincart)/10)+((4*deferredbooks)/10)) DESC", nativeQuery=true)
    Page<Book> getTheMostPopularBook(Pageable nextPage);

    @Query(value="SELECT * FROM book ORDER BY (soldbooks+((7*booksincart)/10)+((4*deferredbooks)/10)) DESC", nativeQuery=true)
    List<Book> getBooksByPopularity();

    Page<Book> findBooksByTitleContainingIgnoreCase(String bookTitle, Pageable nextPage);

    @Query(value="SELECT * FROM book where id in (SELECT book_id FROM book2tag where tag_id = ?1) ", nativeQuery=true)
    Page<Book> findBooksByTagsEquals(Integer tag, Pageable nextPage);

    @Query(value="SELECT * FROM book where pub_date BETWEEN ?1 AND ?2 ORDER BY pub_date ASC ", nativeQuery=true)
    Page<Book> getBooksByPubDateBetween(LocalDate pubDateFrom, LocalDate pubDateTo, Pageable pageable);

    @Query(value="SELECT * FROM book where is_bestseller = 1", nativeQuery=true)
    Page<Book> getBooksByPopularity(Pageable nextPage); // by popularity

    @Query(value="SELECT * FROM book WHERE id in (SELECT book_id FROM book2author WHERE author_id = ?1)", nativeQuery=true)
    Page<Book> getBooksByAuthorId(Integer authorId, Pageable nextPage); //by author id

    @Query(value="SELECT * FROM book where id in (SELECT book_id FROM book2genre where genre_id in (SELECT id FROM genre WHERE path LIKE ?1))", nativeQuery=true)
    Page<Book>  getBooksByIdContains(String genreId, Pageable nextPage); // by genre

    Book findBookBySlug(String slug);
}
