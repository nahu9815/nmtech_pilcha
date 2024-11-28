/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.dto.UsuarioDto;
import com.nmtech.nmtech_v2.model.Usuario;
import com.nmtech.nmtech_v2.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsuarioRepository userRepository; // Repositorio para obtener usuarios

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest request) {
        Optional<Usuario> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.getPassword().equals(request.getPassword())) {
                // Crea el DTO con los datos que quieres devolver al frontend
                UsuarioDto userDto = new UsuarioDto(user.getIdUsuario(), user.getNombre(), user.getApellido(), user.getEmail(), user.getRol());
                return ResponseEntity.ok(userDto);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

}
