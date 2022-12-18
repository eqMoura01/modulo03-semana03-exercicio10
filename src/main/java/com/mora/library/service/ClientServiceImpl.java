package com.mora.library.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mora.library.model.entity.Client;
import com.mora.library.repository.ClientRepository;
import com.mora.library.service.interfaces.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client salvar(Client object) {
        cpfValido(object.getCpf());
        return clientRepository.save(object);
    }

    @Override
    public List<Client> listar() {
        return clientRepository.findAll();
    }

    @Override
    public Client buscar(Long id) {
        Optional<Client> clientPesquisado = this.clientRepository.findById(id);
        if (clientPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar um Cliente com o Id: " + id);
        }
        return clientPesquisado.get();
    }

    @Override
    public Client atualizar(Client object) {

        Client clientPesquisado = buscar(object.getId());

        if (Objects.nonNull(clientPesquisado)) {
            BeanUtils.copyProperties(object, clientPesquisado, "id");
            salvar(object);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        this.clientRepository.deleteById(id);
    }

    // Metodos

    private Boolean cpfValido(String cpf) {
        if (cpf.length() != 11) {
            throw new EntityNotFoundException("Tamanho de CPF invalido!");
        }
        return true;
    }
}
