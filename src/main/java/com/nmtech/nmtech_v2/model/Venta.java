package com.nmtech.nmtech_v2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idVenta")
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonProperty("cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonProperty("usuario")
    private Usuario usuario;

    @Column(name = "fecha_venta")
    @JsonProperty("fechaVenta")
    private LocalDateTime fechaVenta;

    @Column(name = "total", nullable = false)
    @JsonProperty("total")
    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("lineasVenta")
    private List<LineaVenta> lineasVenta;

    // Getters y Setters
    // Método para obtener las líneas de venta
    public List<LineaVenta> getLineasVenta() {
        return lineasVenta;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
