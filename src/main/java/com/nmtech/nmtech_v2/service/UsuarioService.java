package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    // Crear o guardar un usuario
    Usuario saveUsuario(Usuario usuario);

    // Obtener todos los usuarios
    List<Usuario> getAllUsuarios();

    // Obtener un usuario por su ID
    Optional<Usuario> getUsuarioById(Integer id);

    // Obtener un usuario por su email
    Optional<Usuario> getUsuarioByEmail(String email);

    // Verificar si existe un usuario con el email dado
    boolean existsByEmail(String email);

    // Actualizar un usuario existente
    Usuario updateUsuario(Usuario usuario);

    // Eliminar un usuario por su ID
    void deleteUsuarioById(Integer id);
}
