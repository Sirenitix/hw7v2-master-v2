package com.example.BookShop.controller;

import com.example.BookShop.dao.BooksPageDto;
import com.example.BookShop.dao.GenreDto;
import com.example.BookShop.dao.SearchWordDto;
import com.example.BookShop.dao.TagDto;
import com.example.BookShop.entity.Author;
import com.example.BookShop.entity.Book;
import com.example.BookShop.entity.book.tag.Tag;
import com.example.BookShop.service.AuthorService;
import com.example.BookShop.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(description = "authors data")
@Controller
@RequestMapping("/")
public class MainPageController {


    private final BookService bookService;
    private final AuthorService authorService;
    Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    public MainPageController(AuthorService authorService,BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<Book> bookList(){
        return bookService.getPageOfNoveltyBooks(0, 20).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks(){

        return bookService.getPageOfPopularBooks(0, 20).getContent();
    }

    @ModelAttribute("authorData")
    public Author authorData(){
        return new Author();
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 10).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("tagDto")
    public TagDto tagDto() {
        return new TagDto();
    }

    @ModelAttribute("tags")
    public List<Tag> tags() {
        return bookService.getTags();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("booksByTag")
    public List<Book> booksByTag() {
        return new ArrayList<>();
    }

    @ModelAttribute("booksByGenre")
    public List<Book> booksByGenre() {
        return new ArrayList<>();
    }

    @ModelAttribute("sizeOfSearchResults")
    public List<Book> sizeOfSearchResults() {
        return new ArrayList<>();
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("recommendedBooks");
        model.addAttribute("tags");
        return "index";
    }

    @GetMapping("/genres")
    public String genresPage(Model model) {
        return "genres/genres";
    }


    @GetMapping("/books/slug")
    public String booksSlugPage(Model model) {
        return "books/slug";
    }

    @GetMapping("/documents/slug")
    public String documentsSlugPage(Model model) {
        return "documents/slug";
    }

    @GetMapping("/books/{author}/{id}")
    public String booksAuthorPage(@PathVariable(value = "author", required = false) String  author,
                                  @PathVariable(value = "id") Integer id, Model model) {
//      model.addAttribute()
        model.addAttribute("author", author);
        model.addAttribute("authorBooks", bookService.getBooksByAuthor(0,5,id).getContent());
        return "books/author";
    }

    @GetMapping("/authors")
    public String authorsPage(Model model)
    {
        model.addAttribute("authorData", authorService.getAlphabetAndAuthors() );
        return "authors/authors";
    }

    @GetMapping("/authors/{author}/{id}")
    public String authorsPage(@PathVariable(value = "author", required = false) String  author,
                              @PathVariable(value = "id") Integer id, Model model)
    {
        model.addAttribute("author", author);
        model.addAttribute("authorData",authorService.getAuthorById(id));
        model.addAttribute("authorBooks", bookService.getBooksByAuthor(0,5,id).getContent());
      return "authors/slug";
    }

    @GetMapping("/books/recent")
    public String noveltyPage() {
        return "books/recent";
    }


    @GetMapping("/books/popular")
    public String popularBooksPage() {
        return "books/popular";
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/documents")
    public String docPage() {
        return "documents/index";
    }

    @GetMapping("/about")
    public String aboutAppPage() {
        return "about";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }

    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/search")
    public String searchResultsPage(Model model) {return "search/index";}

    @GetMapping("/postponed")
    public String postponedPage(Model model) {
        return "postponed";
    }

    @ApiOperation("method to get map of authors")
    @ResponseBody
    @GetMapping("/api/authors")
    public Map<String, List<Author>> author(){
        return authorService.getAlphabetAndAuthors();
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping( "/search/{searchWord}")
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto, Model model)
    {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("sizeOfSearchResults", bookService.getBooksByTitle(searchWordDto.getExample()));
        model.addAttribute("searchResults", bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping( "/tags/{tag}/{id}")
    public String getFirstTagResultsPage(@PathVariable(value = "tag") String tag,
                                         @PathVariable(value="id") TagDto tagId,
                                         Model model)
    {
        model.addAttribute("tag", tag);
        model.addAttribute("booksByTag", bookService.getPageOfTagResult(tagId.getTag(),0,10).getContent());
        return "/tags/index";
    }

    @GetMapping("/books/page/tags")
    @ResponseBody
    public BooksPageDto getNextTagPage(@RequestParam("offset") Integer offset,
                                       @RequestParam("limit") Integer limit,
                                       @RequestParam("id") TagDto tagId) {
        return new BooksPageDto(bookService.getPageOfTagResult(tagId.getTag(),offset,limit).getContent());
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }

    @GetMapping("/books/page/recent")
    @ResponseBody
    public BooksPageDto getNextNoveltyPage(@RequestParam("from") String from,
                                           @RequestParam("to") String to,
                                           @RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) throws ParseException {
       return new BooksPageDto(bookService.getPageOfNoveltyResultBooks(from,  to, offset, limit).getContent());
    }

    @GetMapping("/books/page/popular")
    @ResponseBody
    public BooksPageDto getNextPopularPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfPopularResultBooks( offset, limit).getContent());
    }

    @GetMapping("/books/page/genre")
    @ResponseBody
    public BooksPageDto getNextGenrePage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @RequestParam("id") TagDto tagId) {
        return new BooksPageDto(bookService.getPageOfTag(tagId.getTag(),offset,limit).getContent());
    }

    @GetMapping("/genres/{genre}/{id}")
    public String genresSlugPage(@PathVariable(value = "genre") String genre,
                                 @PathVariable(value="id") GenreDto genreId,
                                  Model model) {
        model.addAttribute("genre", genre);
        model.addAttribute("booksByGenre", bookService.getPageOfTag(genreId.getGenre(), 0, 5).getContent());
        return "genres/slug";
    }

    @GetMapping("/tags/all")
    public String allTagsPage() {
        return "tags/all";
    }

    @GetMapping("/books/page/author")
    @ResponseBody
    public BooksPageDto getNextAuthorsBooksPage(@RequestParam("offset") Integer offset,
                                                @RequestParam("limit") Integer limit,
                                                @RequestParam("id") Integer authorId) {
        return new BooksPageDto(bookService.getBooksByAuthor(offset,limit,authorId).getContent());
    }

}
