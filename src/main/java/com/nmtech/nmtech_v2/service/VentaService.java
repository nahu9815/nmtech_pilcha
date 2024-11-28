package com.nmtech.nmtech_v2.service;

import com.nmtech.nmtech_v2.model.LineaVenta;
import com.nmtech.nmtech_v2.model.Producto;
import com.nmtech.nmtech_v2.model.Venta;
import com.nmtech.nmtech_v2.repository.ProductoRepository;
import com.nmtech.nmtech_v2.repository.VentaRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Venta getVentaById(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Venta saveVenta(Venta venta) {
        // Itera sobre las líneas de venta para descontar el stock
        for (LineaVenta lineaVenta : venta.getLineasVenta()) {

            System.out.println("ID PRODUCTO: " + lineaVenta.getProducto().getIdProducto());

            Optional<Producto> producto_search = productoRepository.findById(lineaVenta.getProducto().getIdProducto());

            // Verifica que haya suficiente stock antes de descontar
            int cantidadVendida = lineaVenta.getCantidad();
            if (producto_search.get().getStock() < cantidadVendida) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto_search.get().getNombre());
            }

            // Descuenta el stock
            producto_search.get().setStock(producto_search.get().getStock() - cantidadVendida);

            // Guarda el producto actualizado en la base de datos
            productoRepository.save(producto_search.get());
        }

        // Guarda la venta en la base de datos
        return ventaRepository.save(venta);
    }

    public void deleteVenta(int id) {
        ventaRepository.deleteById(id);
    }

    public BigDecimal calcularVentasDiarias() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        List<Venta> ventas = ventaRepository.findVentasByDay(startOfDay, endOfDay);
        return ventas.stream().map(Venta::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularVentasSemanales() {
        LocalDateTime startOfWeek = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1);
        List<Venta> ventas = ventaRepository.findVentasByWeek(startOfWeek, endOfWeek);
        return ventas.stream().map(Venta::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularVentasMensuales() {
        LocalDateTime startOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);
        List<Venta> ventas = ventaRepository.findVentasByMonth(startOfMonth, endOfMonth);
        return ventas.stream().map(Venta::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularVentasAnuales() {
        LocalDateTime startOfYear = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear())
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfYear = startOfYear.plusYears(1);
        List<Venta> ventas = ventaRepository.findVentasByYear(startOfYear, endOfYear);
        return ventas.stream().map(Venta::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> calcularVentasTotalesMensuales() {
        Map<String, BigDecimal> ventasMensuales = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            LocalDateTime startOfMonth = LocalDateTime.now().withMonth(i).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.getMonth().length(startOfMonth.toLocalDate().isLeapYear()));

            BigDecimal total = ventaRepository.calcularTotalVentasPorPeriodo(startOfMonth, endOfMonth);
            ventasMensuales.put(Month.of(i).name(), total != null ? total : BigDecimal.ZERO);
        }

        return ventasMensuales;
    }

}
