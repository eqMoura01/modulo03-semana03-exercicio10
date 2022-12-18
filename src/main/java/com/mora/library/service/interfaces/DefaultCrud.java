package com.mora.library.service.interfaces;

import java.util.List;

public interface DefaultCrud<T> {
    T salvar(T object);

    List<T> listar();

    T buscar(Long id);
    
    T atualizar(T object);

    void deletar(Long id);
}
