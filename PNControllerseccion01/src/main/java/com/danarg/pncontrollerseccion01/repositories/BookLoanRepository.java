package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.domain.entities.BookLoan;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {
    boolean existsByBookAndReturnDateIsNull(Book book);
    Optional<BookLoan> findByBookAndReturnDateIsNull(Book book);
    List<BookLoan> findAllByUser(User user);
    List<BookLoan> findAllByBook(Book book);
    List<BookLoan> findAllByReturnDateIsNull();
}
