package com.mora.library.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mora.library.model.entity.Book;
import com.mora.library.repository.BookRepository;
import com.mora.library.service.interfaces.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book salvar(Book object) {
        return this.bookRepository.save(object);
    }

    @Override
    public Book atualizar(Book object) {
        Book bookPesquisado = buscar(object.getId());

        if (Objects.nonNull(bookPesquisado)) {
            BeanUtils.copyProperties(object, bookPesquisado, "id");
            salvar(object);
        }
        return null;
    }

    @Override
    public Book buscar(Long id) {
        Optional<Book> bookPesquisado = this.bookRepository.findById(id);
        if (bookPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um Livro com o Id: " + id);
        }
        return bookPesquisado.get();
    }

    @Override
    public List<Book> listar() {
        return this.bookRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        this.bookRepository.deleteById(id);
    }

}
