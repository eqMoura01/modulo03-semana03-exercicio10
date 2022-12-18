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

import com.mora.library.model.entity.Client;
import com.mora.library.service.interfaces.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> salvar(@RequestBody @Valid Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.salvar(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> listar() {
        return ResponseEntity.ok(this.clientService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<Client> atualizar(@RequestBody Client client) {
        return ResponseEntity.ok(this.clientService.atualizar(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.clientService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
