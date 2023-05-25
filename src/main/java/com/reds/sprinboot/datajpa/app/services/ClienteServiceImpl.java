package com.reds.sprinboot.datajpa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reds.sprinboot.datajpa.app.models.dao.IClientDao;
import com.reds.sprinboot.datajpa.app.models.entity.Client;

@Service
public class ClienteServiceImpl implements IClientService {

    @Autowired
    private IClientDao iClientDao;


    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) iClientDao.findAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
        iClientDao.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findOne(Long id) {
        return iClientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iClientDao.deleteById(id);

    }
    
}
