package com.mora.library.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mora.library.model.entity.Card;
import com.mora.library.repository.CardRepository;
import com.mora.library.service.interfaces.CardService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card salvar(Card object) {
        return this.cardRepository.save(object);
    }

    @Override
    public List<Card> listar() {
        return this.cardRepository.findAll();
    }

    @Override
    public Card buscar(Long id) {

        Optional<Card> cardPesquisado = this.cardRepository.findById(id);

        if (cardPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um cartão com o Id: " + id);
        }

        return cardPesquisado.get();
    }

    @Override
    public Card atualizar(Card object) {
        Card cardPesquisado = buscar(object.getId());

        if (Objects.nonNull(cardPesquisado)) {
            BeanUtils.copyProperties(object, cardPesquisado, "id");
            salvar(object);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        this.cardRepository.deleteById(id);
    }

}
