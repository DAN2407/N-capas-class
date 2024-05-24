package com.danarg.pncontrollerseccion01.controllers;

import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.services.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/library")
@Slf4j
public class LibraryController {
    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/all")
    public String getALlBooks(Model model){
        model.addAttribute("books", bookService.FindAll());
        return "book-list";
    }

    @GetMapping("/rest")
    public String mainRest(Model model){
        model.addAttribute("books", bookService.FindAll());
        return "index-rest";
    }

    @GetMapping("/")
    public String getSaveForm(){
        return "save-book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid SaveBookDTO info, BindingResult errors){
        if(errors.hasErrors()){
            log.error("Hay errores en el formuladrio. {}", errors.getAllErrors());
            return "error-sin";
        }

        return "redirect:/library/all";
    }

    @GetMapping("/delete")
    public String deleteBook(String isbn){
        bookService.deleteByIsnb(isbn);
        return "redirect:/library/all";
    }

    @GetMapping("/edit")
    public String editBook(String isbn, Model model){
        Book book = bookService.findByIsnb(isbn);
        model.addAttribute("book", book);
        return "save-book";
    }
}


