package com.spring.mvc.service;

import java.util.List;

import com.spring.mvc.entity.Client;
import com.spring.mvc.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {
    @Autowired
    ClientRepository repository;

    public void save(Client client) {
        repository.save(client);
    }

    //rename to allClients
    public List<Client> listAll() {
        return (List<Client>) repository.findAll();
    }

    public Client get(int id) {
        return repository.findById(id).get();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Client> search(String keyword){
        return repository.search(keyword);
    }
}
