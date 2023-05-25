package com.reds.sprinboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reds.sprinboot.datajpa.app.models.entity.Client;

public interface IClientDao extends JpaRepository<Client, Long>{

}
