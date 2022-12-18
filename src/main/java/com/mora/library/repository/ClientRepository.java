package com.mora.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mora.library.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
