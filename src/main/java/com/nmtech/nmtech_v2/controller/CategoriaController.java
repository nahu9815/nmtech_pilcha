package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.dto.CategoriaDto;
import com.nmtech.nmtech_v2.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> obtenerCategoriaPorId(@PathVariable int id) {
        CategoriaDto categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto nuevaCategoria = categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> modificarCategoria(@PathVariable int id, @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto categoriaActualizada = categoriaService.modificarCategoria(id, categoriaDto);
        if (categoriaActualizada != null) {
            return ResponseEntity.ok(categoriaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
