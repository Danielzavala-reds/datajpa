package com.reds.sprinboot.datajpa.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reds.sprinboot.datajpa.app.models.entity.Client;

public interface IClientService {
    
    public List<Client> findAll();

    public Page<Client> findAll(Pageable pageable);
    
    public void save(Client client);

    public Client findOne(Long id);

    public void delete(Long id);
}
