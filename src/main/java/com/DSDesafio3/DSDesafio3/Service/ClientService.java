package com.DSDesafio3.DSDesafio3.Service;

import com.DSDesafio3.DSDesafio3.DTO.ClientDTO;
import com.DSDesafio3.DSDesafio3.Repository.ClientRepository;
import com.DSDesafio3.DSDesafio3.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.Cleaner;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO insert(ClientDTO clientDTO) {
        Client entity = new Client();
        DtoToEnitiy(clientDTO, entity);
        return new ClientDTO(repository.save(entity));
    }

    private void DtoToEnitiy(ClientDTO clientDTO, Client entity) {
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());
    }

}
