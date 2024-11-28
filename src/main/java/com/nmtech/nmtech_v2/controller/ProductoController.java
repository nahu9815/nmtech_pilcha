package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.dto.ProductoDto;
import com.nmtech.nmtech_v2.model.Producto;
import com.nmtech.nmtech_v2.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

       
    @Autowired
    private ProductoService productoService;
 
    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\nmart\\repos\\proyectos\\react_apps\\nmtech_v1\\public\\images\\";
/*
   @PostMapping("/uploadImage")
    @Operation(summary = "Sube una imagen", description = "Carga una imagen y la guarda en una carpeta específica")
    public ResponseEntity<String> uploadImage(
            @Parameter(description = "Archivo de imagen a cargar") @RequestParam("file") MultipartFile file) {
        try {
            // Guardar la imagen en la carpeta /public/images
            Path path = Paths.get(IMAGE_UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            // Retornar el nombre del archivo para guardarlo en la base de datos
            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir la imagen.");
        }
    }

*/
    
    private static final String UPLOAD_DIR = "uploads"; // Carpeta relativa

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Crear el directorio si no existe
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Guardar la imagen en el directorio relativo
            Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.write(path, file.getBytes());

            // Retornar el nombre del archivo (o la ruta relativa si prefieres)
            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir la imagen.");
        }
    }
     // Endpoint para buscar productos
    @GetMapping("/buscar/{search}")
    public List<Producto> buscarProductos(@PathVariable("search") String searchTerm) {
        return productoService.buscarProductos(searchTerm);
    }
    
    @GetMapping
    public List<ProductoDto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable int id) {
        ProductoDto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto) {
        ProductoDto nuevoProducto = productoService.crearProducto(productoDto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable int id, @RequestBody ProductoDto productoDto) {
        ProductoDto productoActualizado = productoService.actualizarProducto(id, productoDto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

