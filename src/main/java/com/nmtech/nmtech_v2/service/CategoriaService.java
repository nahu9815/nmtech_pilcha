package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.dto.CategoriaDto;
import com.nmtech.nmtech_v2.model.Categoria;
import com.nmtech.nmtech_v2.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public CategoriaDto obtenerCategoriaPorId(int id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(this::convertirADto).orElse(null);
    }

    public CategoriaDto crearCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = convertirAEntidad(categoriaDto);
        categoriaRepository.save(categoria);
        return convertirADto(categoria);
    }

    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }

    // Métodos de conversión

    private CategoriaDto convertirADto(Categoria categoria) {
        CategoriaDto dto = new CategoriaDto();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }

    private Categoria convertirAEntidad(CategoriaDto dto) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoria;
    }
    
    public CategoriaDto modificarCategoria(int id, CategoriaDto categoriaDto) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNombre(categoriaDto.getNombre());
            categoria.setDescripcion(categoriaDto.getDescripcion());
            categoriaRepository.save(categoria);
            return convertirADto(categoria);
        } else {
            return null; // O lanzar una excepción personalizada si es necesario
        }
    }
    
}
