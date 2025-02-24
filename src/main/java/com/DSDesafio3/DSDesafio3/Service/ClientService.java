package com.DSDesafio3.DSDesafio3.Service;

import com.DSDesafio3.DSDesafio3.DTO.ClientDTO;
import com.DSDesafio3.DSDesafio3.Repository.ClientRepository;
import com.DSDesafio3.DSDesafio3.Service.exception.DSException;
import com.DSDesafio3.DSDesafio3.Service.exception.DatabaseException;
import com.DSDesafio3.DSDesafio3.Service.exception.DeleteUserNotFoundException;
import com.DSDesafio3.DSDesafio3.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.Cleaner;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    //Validacao
    @Transactional(readOnly = true)
    public ClientDTO insert(ClientDTO clientDTO) {
        Client entity = new Client();
        DtoToEnitiy(clientDTO, entity);
        return new ClientDTO(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
    return new ClientDTO(repository.findById(id).orElseThrow(() -> new DSException("Recurso nao encontrado")));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return  repository.findAll(pageable).map(ClientDTO::new);

    }
    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client entity = repository.getReferenceById(id);
        DtoToEnitiy(clientDTO, entity);
        return new ClientDTO(repository.save(entity));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DeleteUserNotFoundException("Id do usuario nao encontrado");
        } try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha na integridade referencial");
        }

    }

    private void DtoToEnitiy(ClientDTO clientDTO, Client entity) {
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());
    }

}
