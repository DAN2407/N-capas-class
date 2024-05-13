package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.repositories.BookRepository;
import com.danarg.pncontrollerseccion01.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class BookServiceImpl (BookRepository bookRepository) {
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(SaveBookDTO info) {
        Book book = this.findByIsnb(info.getISBN());

        if(book == null){
            book = new Book();
        }

        book.setISBN(info.getISBN());
        book.setTitle(info.getTitle());
        book.setCategory(category);

        bookRepository.save(book);

    }

    @Override
    public List<Book> FindAll() {
        return bookRepository.findAll();

    }

    @Override
    public Book findByIsnb(String isbn) {
        return bookRepository.findByISBN(isbn).orElse(null);
    }

    @Override
    public void deleteByIsnb(String isbn) {
        Book book = this.findByIsnb(isbn);
        if(book != null){
            bookRepository.deleteBy(book);
        }
    }


}
