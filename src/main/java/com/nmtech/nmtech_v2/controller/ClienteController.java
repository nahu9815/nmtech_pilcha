package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.model.Cliente;
import com.nmtech.nmtech_v2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.saveCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteService.getClienteById(id);
        return clienteOpt.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> clienteOpt = clienteService.getClienteById(id);

        if (clienteOpt.isPresent()) {
            Cliente clienteToUpdate = clienteOpt.get();
            clienteToUpdate.setNombre(clienteDetails.getNombre());
            clienteToUpdate.setApellido(clienteDetails.getApellido());
            clienteToUpdate.setTelefono(clienteDetails.getTelefono());
            clienteToUpdate.setEmail(clienteDetails.getEmail());
            clienteToUpdate.setDireccion(clienteDetails.getDireccion());

            Cliente updatedCliente = clienteService.updateCliente(clienteToUpdate);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteService.getClienteById(id);

        if (clienteOpt.isPresent()) {
            clienteService.deleteClienteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
