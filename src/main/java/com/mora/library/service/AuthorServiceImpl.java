package com.mora.library.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mora.library.model.entity.Author;
import com.mora.library.repository.AuthorRepository;
import com.mora.library.service.interfaces.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author salvar(Author object) {
        return this.authorRepository.save(object);
    }

    @Override
    public Author atualizar(Author object) {
        Author authorPesquisado = buscar(object.getId());
        if (Objects.nonNull(authorPesquisado)) {
            BeanUtils.copyProperties(object, authorPesquisado, "id");
            salvar(object);
        }
        return null;
    }

    @Override
    public Author buscar(Long id) {
        Optional<Author> authorPesquisado = this.authorRepository.findById(id);

        if (authorPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um Autor com o Id: " + id);
        }
        return authorPesquisado.get();
    }

    @Override
    public List<Author> listar() {
        return this.authorRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        buscar(id);
        this.authorRepository.deleteById(id);
    }

    
}
