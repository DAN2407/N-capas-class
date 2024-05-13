package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.Book;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{

    Optional<Book> findByISBN(String ISBN);
    Optional<Book> deleteBy(String ISBN);

    Optional<Book> findAll(String ISBN);

    Optional<Book> save(String ISBN);

}
