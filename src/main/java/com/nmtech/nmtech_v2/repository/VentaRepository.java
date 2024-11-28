package com.nmtech.nmtech_v2.repository;

import com.nmtech.nmtech_v2.model.Venta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT v FROM Venta v WHERE v.fechaVenta >= :startOfDay AND v.fechaVenta < :endOfDay")
    List<Venta> findVentasByDay(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT v FROM Venta v WHERE v.fechaVenta >= :startOfWeek AND v.fechaVenta < :endOfWeek")
    List<Venta> findVentasByWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

    @Query("SELECT v FROM Venta v WHERE v.fechaVenta >= :startOfMonth AND v.fechaVenta < :endOfMonth")
    List<Venta> findVentasByMonth(LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    @Query("SELECT v FROM Venta v WHERE v.fechaVenta >= :startOfYear AND v.fechaVenta < :endOfYear")
    List<Venta> findVentasByYear(LocalDateTime startOfYear, LocalDateTime endOfYear);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fechaVenta BETWEEN :start AND :end")
    BigDecimal calcularTotalVentasPorPeriodo(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
