package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.dto.ProductoDto;
import com.nmtech.nmtech_v2.model.Categoria;
import com.nmtech.nmtech_v2.model.Producto;
import com.nmtech.nmtech_v2.repository.CategoriaRepository;
import com.nmtech.nmtech_v2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProductoDto> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public ProductoDto obtenerProductoPorId(int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(this::convertirADto).orElse(null);
    }

    public ProductoDto crearProducto(ProductoDto productoDto) {
        Producto producto = convertirAEntidad(productoDto);
        productoRepository.save(producto);
        return convertirADto(producto);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    // Métodos de conversión

    private ProductoDto convertirADto(Producto producto) {
        ProductoDto dto = new ProductoDto();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagen(producto.getImagen());
        dto.setFechaCreacion(producto.getFechaCreacion());
        dto.setIdCategoria(producto.getCategoria().getIdCategoria());
        return dto;
    }
    
    
    public ProductoDto actualizarProducto(int id, ProductoDto productoDto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            // Actualiza los campos del producto
            producto.setNombre(productoDto.getNombre());
            producto.setDescripcion(productoDto.getDescripcion());
            producto.setPrecio(productoDto.getPrecio());
            producto.setStock(productoDto.getStock());
            producto.setImagen(productoDto.getImagen());
            producto.setFechaCreacion(productoDto.getFechaCreacion());

            // Asignar la categoría usando el idCategoria del DTO
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(productoDto.getIdCategoria());
            if (categoriaOptional.isPresent()) {
                producto.setCategoria(categoriaOptional.get());
            } else {
                producto.setCategoria(null); // O maneja el caso de que no se encuentre la categoría
            }

            Producto productoActualizado = productoRepository.save(producto);
            return new ProductoDto(productoActualizado); // Conversión de Producto a ProductoDto
        } else {
            return null;
        }
    }
    
     public List<Producto> buscarProductos(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return productoRepository.findAll(); // Si no hay término de búsqueda, devolver todos los productos
        } else {
            return productoRepository.buscarPorCodigoNombreDescripcion(searchTerm);
        }
    }

    private Producto convertirAEntidad(ProductoDto dto) {
        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagen(dto.getImagen());

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria()).orElse(null);
        producto.setCategoria(categoria);

        return producto;
    }
}
