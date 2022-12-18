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

import com.mora.library.model.entity.Card;
import com.mora.library.service.interfaces.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> salvar(@RequestBody @Valid Card card) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cardService.salvar(card));
    }

    @GetMapping
    public ResponseEntity<List<Card>> listar() {
        return ResponseEntity.ok(this.cardService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Card> atualizar(@RequestBody @Valid Card card) {
        return ResponseEntity.ok(this.cardService.atualizar(card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.cardService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
