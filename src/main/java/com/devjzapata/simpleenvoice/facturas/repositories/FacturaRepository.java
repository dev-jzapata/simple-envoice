package com.devjzapata.simpleenvoice.facturas.repositories;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByCodigoFactura(String codigoFactura);

    Page<Factura> findByClienteNombreContainingIgnoreCase(String keyword, Pageable pageable);
}
