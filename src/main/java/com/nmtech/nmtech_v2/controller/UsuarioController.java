package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.model.Usuario;
import com.nmtech.nmtech_v2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.existsByEmail(usuario.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);  // Si ya existe un usuario con ese email
        }
        Usuario newUsuario = usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(id);
        return usuarioOpt.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener un usuario por Email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioByEmail(email);
        return usuarioOpt.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuarioToUpdate = usuarioOpt.get();
            usuarioToUpdate.setNombre(usuarioDetails.getNombre());
            usuarioToUpdate.setApellido(usuarioDetails.getApellido());
            usuarioToUpdate.setEmail(usuarioDetails.getEmail());
            usuarioToUpdate.setPassword(usuarioDetails.getPassword());  // Considera agregar encriptación aquí
            usuarioToUpdate.setRol(usuarioDetails.getRol());

            Usuario updatedUsuario = usuarioService.updateUsuario(usuarioToUpdate);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(id);

        if (usuarioOpt.isPresent()) {
            usuarioService.deleteUsuarioById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
