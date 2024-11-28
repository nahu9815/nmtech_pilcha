package com.nmtech.nmtech_v2.repository;

import com.nmtech.nmtech_v2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método para buscar un usuario por su email
    Optional<Usuario> findByEmail(String email);

    // Verifica si un usuario existe por su email
    boolean existsByEmail(String email);
}
