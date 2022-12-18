package com.mora.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mora.library.model.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
