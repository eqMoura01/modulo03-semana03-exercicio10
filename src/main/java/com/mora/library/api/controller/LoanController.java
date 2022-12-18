package com.mora.library.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mora.library.model.entity.Loan;
import com.mora.library.service.interfaces.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // CRUD

    @PostMapping
    public ResponseEntity<Loan> salvar(@RequestBody @Valid Loan loan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.loanService.salvar(loan));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> listar() {
        return ResponseEntity.ok(this.loanService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Loan> atualizar(@RequestBody @Valid Loan loan) {
        return ResponseEntity.ok(this.loanService.atualizar(loan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.loanService.deletar(id);
        return ResponseEntity.ok().build();
    }

    // Metodos

    @PutMapping("/{id}")
    public ResponseEntity<Loan> pagarEmprestimo(@PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.pagarEmprestimo(id));
    }
}
