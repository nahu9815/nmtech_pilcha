package com.nmtech.nmtech_v2.controller;

import com.nmtech.nmtech_v2.model.Venta;
import com.nmtech.nmtech_v2.service.VentaService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable int id) {
        return ventaService.getVentaById(id);
    }

    @PostMapping
    public Venta saveVenta(@RequestBody Venta venta) {
        return ventaService.saveVenta(venta);
    }

    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable int id) {
        ventaService.deleteVenta(id);
    }

    @GetMapping("/diarias")
    public BigDecimal obtenerVentasDiarias() {
        return ventaService.calcularVentasDiarias();
    }

    @GetMapping("/semanales")
    public BigDecimal obtenerVentasSemanales() {
        return ventaService.calcularVentasSemanales();
    }

    @GetMapping("/mensuales")
    public BigDecimal obtenerVentasMensuales() {
        return ventaService.calcularVentasMensuales();
    }

    @GetMapping("/anuales") 
    public BigDecimal obtenerVentasAnuales() {
        return ventaService.calcularVentasAnuales();
    }

    @GetMapping("/ventas/totales-mensuales")
    public ResponseEntity<Map<String, BigDecimal>> getVentasTotalesMensuales() {
        Map<String, BigDecimal> ventasMensuales = (Map<String, BigDecimal>) ventaService.calcularVentasTotalesMensuales();
        return ResponseEntity.ok(ventasMensuales);
    }

}
