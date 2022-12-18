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

import com.mora.library.model.entity.Book;
import com.mora.library.service.interfaces.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> salvar(@RequestBody @Valid Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.salvar(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> listar() {
        return ResponseEntity.ok(this.bookService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Book> atualizar(@RequestBody @Valid Book book) {
        return ResponseEntity.ok(this.bookService.atualizar(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.bookService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
