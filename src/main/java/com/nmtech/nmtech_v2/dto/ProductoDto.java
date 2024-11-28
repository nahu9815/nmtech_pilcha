package com.nmtech.nmtech_v2.dto;

import com.nmtech.nmtech_v2.model.Producto;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductoDto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String imagen;
    private Timestamp fechaCreacion;
    private int idCategoria;

    // Getters and Setters

    public ProductoDto(){
        
    }
    // Constructor que convierte un Producto a ProductoDto
    public ProductoDto(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.stock = producto.getStock();
        this.imagen = producto.getImagen();
        this.fechaCreacion = producto.getFechaCreacion();
        this.idCategoria = producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : 0;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
