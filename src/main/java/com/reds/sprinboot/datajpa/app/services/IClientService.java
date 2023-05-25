package com.reds.sprinboot.datajpa.app.services;

import java.util.List;

import com.reds.sprinboot.datajpa.app.models.entity.Client;

public interface IClientService {
    
    public List<Client> findAll();

    public void save(Client client);

    public Client findOne(Long id);

    public void delete(Long id);
}
