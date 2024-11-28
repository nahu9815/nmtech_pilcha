/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmtech.nmtech_v2.repository;

import com.nmtech.nmtech_v2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nmart
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente,Integer>{
    
}
