package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.domain.entities.BookLoan;
import com.danarg.pncontrollerseccion01.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface BookLoanService {
    void create(Book book, User user, int loanDays);

    boolean isLoaned(Book book);

    BookLoan findActiveByBook(Book book);
    BookLoan findById(UUID id);


    List<BookLoan> findAll();
    List<BookLoan> findAllByUser(User user);
    List<BookLoan> findAllByBook(Book book);
    List<BookLoan> findAlActive();

    void returnBook(BookLoan bookLoan);
}
