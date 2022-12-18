package com.mora.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mora.library.model.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
    
}
