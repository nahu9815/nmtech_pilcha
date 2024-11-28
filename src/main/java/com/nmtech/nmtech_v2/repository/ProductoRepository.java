package com.nmtech.nmtech_v2.repository;

import com.nmtech.nmtech_v2.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p FROM Producto p WHERE  p.nombre LIKE %:searchTerm% OR p.descripcion LIKE %:searchTerm%")
    List<Producto> buscarPorCodigoNombreDescripcion(String searchTerm);
}
