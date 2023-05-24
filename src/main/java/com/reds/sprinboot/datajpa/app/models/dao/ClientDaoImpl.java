package com.reds.sprinboot.datajpa.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reds.sprinboot.datajpa.app.models.entity.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class ClientDaoImpl implements IClientDao{
    
    @PersistenceContext
    private EntityManager eManager;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {
        
        return eManager.createQuery("from Client").getResultList();
    }

    @Override
    @Transactional
    public void save(Client client) {
        if(client.getId() != null && client.getId() > 0){
            eManager.merge(client);
        } else{
            eManager.persist(client);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Client findOne(Long id) {
        return eManager.find(Client.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Client client = findOne(id);
        eManager.remove(findOne(id));
    }
    
}
