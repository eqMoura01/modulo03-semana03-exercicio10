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

import com.mora.library.model.entity.Author;
import com.mora.library.service.interfaces.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> salvar(@RequestBody @Valid Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.salvar(author));
    }

    @GetMapping
    public ResponseEntity<List<Author>> listar() {
        return ResponseEntity.ok(this.authorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.authorService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Author> atualizar(@RequestBody @Valid Author author) {
        return ResponseEntity.ok(this.authorService.atualizar(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.authorService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
