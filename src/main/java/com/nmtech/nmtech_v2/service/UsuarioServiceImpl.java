package com.nmtech.nmtech_v2.service.impl;

import com.nmtech.nmtech_v2.model.Usuario;
import com.nmtech.nmtech_v2.repository.UsuarioRepository;
import com.nmtech.nmtech_v2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario); // JpaRepository gestiona la actualización si el ID ya existe
    }

    @Override
    public void deleteUsuarioById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
