package com.example.BookShop.service;

import com.example.BookShop.dao.*;
import com.example.BookShop.entity.Book;
import com.example.BookShop.entity.book.links.Book2Genre;
import com.example.BookShop.entity.book.links.Book2Tag;
import com.example.BookShop.entity.book.tag.Tag;
import com.example.BookShop.entity.genre.Genre;
import com.example.BookShop.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final TagRepository tagRepository;
    private final Book2TagRepository book2TagRepository;
    private final Book2GenreRepository book2GenreRepository;
    Logger logger = LoggerFactory.getLogger(BookService.class);


    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, TagRepository tagRepository, Book2TagRepository book2TagRepository, Book2GenreRepository book2GenreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.tagRepository = tagRepository;
        this.book2TagRepository = book2TagRepository;
        this.book2GenreRepository = book2GenreRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.customFindAllBooks();
    }


    public Page<Book> getBooksByAuthor(Integer offset, Integer limit,Integer authorId){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getBooksByAuthorId(authorId, nextPage);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContainingIgnoreCase(title);
    }

    public List<Tag> getTags(){ return tagRepository.findAllByOrderById();}

    public List<Book2Tag> getTagsId(){ return book2TagRepository.findAllByOrderById();}

    public List<Genre> getGenres(){
        return genreRepository.findAllByOrderByIdAsc();
    }

    public List<Book> getBooksWithPriceIsBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceIsBetween(min,max);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestseller(){
        return bookRepository.getBooksByPopularity();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getBooksByPopularity(nextPage);
    }

    public Page<Book> getPageOfNoveltyBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getBooks(nextPage);
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getTheMostPopularBook(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBooksByTitleContainingIgnoreCase(searchWord,nextPage);
    }

    public Page<Book> getPageOfNoveltyResultBooks(String from, String to, Integer offset, Integer limit) throws ParseException {
        Converter fromTypeString = new Converter(from);
        Converter toTypeString = new Converter(to);
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.getBooksByPubDateBetween(fromTypeString.getDateType(), toTypeString.getDateType(), nextPage);
    }

    public Page<Book> getPageOfPopularResultBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getTheMostPopularBook(nextPage);
    }

    public Page<Book> getPageOfTagResult(Integer tagId, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBooksByTagsEquals(tagId,nextPage);
    }

    public Page<Book> getPageOfTag(Integer genreId, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        String genreIdString = "%." + genreId + ".%";
        logger.info(genreIdString);
        return bookRepository.getBooksByIdContains(genreIdString, nextPage);
    }


    public List<Book2Genre> getBooksGenreId() {
        return book2GenreRepository.findAllByOrderById();
    }
}
