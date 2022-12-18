package com.mora.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mora.library.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
