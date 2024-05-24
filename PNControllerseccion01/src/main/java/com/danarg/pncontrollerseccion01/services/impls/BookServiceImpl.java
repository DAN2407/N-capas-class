package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.dtos.SaveBookDTO;
import com.danarg.pncontrollerseccion01.domain.entities.Book;
import com.danarg.pncontrollerseccion01.repositories.BookRepository;
import com.danarg.pncontrollerseccion01.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void save(SaveBookDTO info) {
        Book book = this.findByIsnb(info.getISBN());

        if(book == null){
            book = new Book();
        }
    }

    @Override
    public List<Book> FindAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsnb(String isbn) {
        return bookRepository.findByIsnb(isbn).orElse(null);
    }

    @Override
    public void deleteByIsnb(String isbn) {
        bookRepository.deleteByIsnb(isbn);
    }
}
