package com.danarg.pncontrollerseccion01.controllers;

import com.danarg.pncontrollerseccion01.domain.dtos.GeneralResponse;
import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Category;
import com.danarg.pncontrollerseccion01.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    @Autowired
    BookService bookService;

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
        Category category = categoryService.findCategoryById(info.getCategory());
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


}