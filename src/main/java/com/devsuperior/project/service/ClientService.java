package com.devsuperior.project.service;

import com.devsuperior.project.DTO.ClientDTO;
import com.devsuperior.project.entities.Client;
import com.devsuperior.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> client = repository.findById(id);
        Client entity = client.get();
        return new ClientDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findPages(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO inputClient(ClientDTO dto) {
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.getOne(id);
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}