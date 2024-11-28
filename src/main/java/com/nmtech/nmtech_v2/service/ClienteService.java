package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    
    // Método para guardar un nuevo cliente
    Cliente saveCliente(Cliente cliente);
    
    // Método para obtener todos los clientes
    List<Cliente> getAllClientes();
    
    // Método para obtener un cliente por ID
    Optional<Cliente> getClienteById(Integer id);
    
    // Método para actualizar un cliente existente
    Cliente updateCliente(Cliente cliente);
    
    // Método para eliminar un cliente por ID
    void deleteClienteById(Integer id);
}
