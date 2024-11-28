/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.model.Cliente;
import com.nmtech.nmtech_v2.repository.ClienteDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmart
 */
@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteDAO clientedao;
    
    @Override
    public Cliente saveCliente(Cliente cliente) {
        return this.clientedao.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return this.clientedao.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Integer id) {
        return this.clientedao.findById(id);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return this.clientedao.saveAndFlush(cliente);
    }

    @Override
    public void deleteClienteById(Integer id) {
        this.clientedao.deleteById(id);
    }
    
}
