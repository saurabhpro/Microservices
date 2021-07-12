package com.saurabh.rxjavatutorial.repositories;

import com.saurabh.rxjavatutorial.models.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
