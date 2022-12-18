package com.mora.library.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mora.library.model.entity.Loan;
import com.mora.library.repository.LoanRepository;
import com.mora.library.service.interfaces.BookService;
import com.mora.library.service.interfaces.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookService bookService;

    // CRUD

    @Override
    public Loan salvar(Loan object) {

        if (Objects.nonNull(this.bookService.buscar(object.getBook().getId()))) {
            if (object.getBook().getQuantity() <= 0) {
                throw new EntityNotFoundException("Este livro não está disponivel");
            }
        }

        return this.loanRepository.save(object);
    }

    @Override
    public List<Loan> listar() {
        return this.loanRepository.findAll();
    }

    @Override
    public Loan buscar(Long id) {

        Optional<Loan> loanPesquisado = this.loanRepository.findById(id);

        if (loanPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um emprestimo com o Id: " + id);
        }

        return loanPesquisado.get();
    }

    @Override
    public Loan atualizar(Loan object) {
        Loan loanPesquisado = buscar(object.getId());

        if (Objects.nonNull(object)) {
            BeanUtils.copyProperties(object, loanPesquisado, "id");
            salvar(object);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        this.loanRepository.deleteById(id);
    }

    // Metodos

    @Override
    public Loan pagarEmprestimo(Long id) {

        Loan loanPesquisado = buscar(id);

        clientePossuiCartao(loanPesquisado);
        possuiSaldoCartao(loanPesquisado);

        loanPesquisado.setStatus(true);
        
        salvar(loanPesquisado);

        return loanPesquisado;
    }

    // Testa se o cliente possui um cartão cadastrado.
    private Boolean clientePossuiCartao(Loan loan) {
        if (Objects.isNull(loan.getClient().getCard())) {
            throw new EntityNotFoundException("O cliente não tem um cartão cadastrado.");
        }

        return true;
    }

    // Testa se o cartão possui saldo.
    private Boolean possuiSaldoCartao(Loan loan) {

        Double valorTotal = loan.getCost()
                * (java.time.Duration.between(loan.getStartDate(), loan.getEndDate()).toDays());

        Double saldoAtual = loan.getClient().getCard().getLimite() - loan.getClient().getCard().getLimiteAtual();

        if (saldoAtual < valorTotal) {
            throw new EntityNotFoundException("Cartão sem saldo.");
        }

        return true;
    }
}
