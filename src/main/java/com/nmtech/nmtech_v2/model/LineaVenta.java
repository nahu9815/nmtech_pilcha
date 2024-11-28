package com.nmtech.nmtech_v2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "linea_venta")
public class LineaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idLineaVenta")
    private int idLineaVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    @JsonBackReference
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonProperty("producto")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    @JsonProperty("cantidad")
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    @JsonProperty("precioUnitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false)
    @JsonProperty("subtotal")
    private BigDecimal subtotal;

    // Getters y Setters

    public int getIdLineaVenta() {
        return idLineaVenta;
    }

    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    
}
