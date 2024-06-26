package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{

    Optional<Book> findByIsnb(String isbn);

    void deleteByIsnb(String isbn);

}
