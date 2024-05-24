package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.domain.entities.BookLoan;
import com.danarg.pncontrollerseccion01.domain.entities.User;
import com.danarg.pncontrollerseccion01.repositories.BookLoanRepository;
import com.danarg.pncontrollerseccion01.services.BookLoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookLoanServiceImpl implements BookLoanService {
    @Autowired

    private final BookLoanRepository bookLoanRepository;

    public BookLoanServiceImpl(BookLoanRepository bookLoanRepository) {
        this.bookLoanRepository = bookLoanRepository;
    }

    @Override
    public void create(Book book, User user, int loanDays) {
        BookLoan bookLoan = new BookLoan();

        bookLoan.setBook(book);
        bookLoan.setUser(user);
        bookLoan.setLoanDate(Date.from(Instant.now()));
        bookLoan.setDueDate(
                Date.from(Instant.now().plusSeconds((long) loanDays * 24 * 60 * 60))
        );

        bookLoanRepository.save(bookLoan);


    }

    @Override
    public boolean isLoaned(Book book) {
        return bookLoanRepository.existsByBookAndReturnDateIsNull(book);
    }

    @Override
    public BookLoan findActiveByBook(Book book) {
        return bookLoanRepository.findByBookAndReturnDateIsNull(book).orElse(null);
    }

    @Override
    public BookLoan findById(UUID id) {
        return bookLoanRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookLoan> findAll() {
        return bookLoanRepository.findAll();
    }

    @Override
    public List<BookLoan> findAllByUser(User user) {
        return bookLoanRepository.findAllByUser(user);
    }

    @Override
    public List<BookLoan> findAllByBook(Book book) {
        return bookLoanRepository.findAllByBook(book);
    }

    @Override
    public List<BookLoan> findAlActive() {
        return bookLoanRepository.findAllByReturnDateIsNull();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void returnBook(BookLoan bookLoan) {
        bookLoan.setReturnDate(Date.from(Instant.now()));
        bookLoanRepository.save(bookLoan);
    }
}
