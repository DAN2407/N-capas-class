package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Book;

import java.util.List;

public interface BookService {
    void save(SaveBookDTO info);
    List<Book> FindAll();
    Book findByIsnb(String isbn);
    void deleteByIsnb(String isbn);
}
