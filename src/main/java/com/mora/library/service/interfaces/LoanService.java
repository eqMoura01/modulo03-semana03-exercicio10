package com.mora.library.service.interfaces;

import com.mora.library.model.entity.Loan;

public interface LoanService extends DefaultCrud<Loan> {
    Loan pagarEmprestimo(Long id);
}
