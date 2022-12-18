package com.mora.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mora.library.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
