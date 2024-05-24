package com.danarg.pncontrollerseccion01.controllers;

import com.danarg.pncontrollerseccion01.domain.dtos.CreateBookLoanDTO;
import com.danarg.pncontrollerseccion01.domain.dtos.GeneralResponse;
import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.domain.entities.BookLoan;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.services.BookLoanService;
import com.danarg.pncontrollerseccion01.services.BookService;
import com.danarg.pncontrollerseccion01.services.CategoryService;
import com.danarg.pncontrollerseccion01.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final BookLoanService loanService;
    private final UserService userService;


    public LibraryRestController(BookService bookService, CategoryService categoryService, BookLoanService loanService, UserService userService){
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.loanService = loanService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Books found", bookService.FindAll());
    }

    @GetMapping("/rest")
    public ResponseEntity<?> mainRest(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Books found", bookService.FindAll());
    }

    @GetMapping("/")
    public ResponseEntity<?> getSaveForm(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Books found", bookService.FindAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@ModelAttribute @Valid SaveBookDTO info, BindingResult errors){
        if(errors.hasErrors()){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Errors in the form", errors.getAllErrors());
        }
        bookService.save(info);
        return GeneralResponse.getResponse(HttpStatus.OK, "Book saved", null);
    }

    @GetMapping("/find/{isbn}")
    public ResponseEntity<?> findBook(@PathVariable String isbn){
        return GeneralResponse.getResponse(HttpStatus.OK, "Book found", bookService.findByIsnb(isbn));
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        bookService.deleteByIsnb(isbn);
        return GeneralResponse.getResponse(HttpStatus.OK, "Book deleted", null);
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @ModelAttribute @Valid SaveBookDTO info, BindingResult errors){
        if(errors.hasErrors()){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Errors in the form", errors.getAllErrors());
        }
        bookService.deleteByIsnb(isbn);
        bookService.save(info);
        return GeneralResponse.getResponse(HttpStatus.OK, "Book updated", null);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Categories found", bookService.getCategories());

    }

    @PostMapping("/categories")
    public ResponseEntity<?> saveCategory(@RequestParam String name){
        bookService.saveCategory(name);
        return GeneralResponse.getResponse(HttpStatus.OK, "Category saved", null);
    }

    @DeleteMapping("/categories/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable String name){
        bookService.deleteCategory(name);
        return GeneralResponse.getResponse(HttpStatus.OK, "Category deleted", null);
    }

    @PutMapping("/categories/{name}")
    public ResponseEntity<?> updateCategory(@PathVariable String name, @RequestParam String newName){
        bookService.updateCategory(name, newName);
        return GeneralResponse.getResponse(HttpStatus.OK, "Category updated", null);
    }

//Loans
    @PostMapping("/loan-book")
    public ResponseEntity<GeneralResponse> loanBook(@RequestBody @Valid CreateBookLoanDTO info){
        User user = userService.findByUsernameOrEmail(info.getUsername());
        if(user == null){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User not found", null);
        }

        Book book = bookService.findByIsnb(info.getISBN());
        if(book == null){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Book not found", null);
        }

        if(loanService.isLoaned(book)){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Book is already loaned", null);
        }

        loanService.create(book, user, info.getLoanDays());
        return GeneralResponse.getResponse(HttpStatus.OK, "Book loaned", null);
    }

    @PostMapping("/return-book/{isbn}")
    public ResponseEntity<GeneralResponse> returnBook(@PathVariable String isbn){
        Book book = bookService.findByIsnb(isbn);
        if(book == null){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Book not found", null);
        }

        BookLoan bookLoan = loanService.findActiveByBook(book);
        if(bookLoan == null){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Book is not loaned", null);
        }

        loanService.returnBook(bookLoan);
        return GeneralResponse.getResponse(HttpStatus.OK, "Book returned", null);
    }

    @GetMapping("/loans")
    public ResponseEntity<GeneralResponse> getLoans(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Loans found", loanService.findAll());
    }

    @GetMapping("/active-loans")
    public ResponseEntity<GeneralResponse> getActiveLoans(){
        return GeneralResponse.getResponse(HttpStatus.OK, "Active loans found", loanService.findAlActive());
    }




}