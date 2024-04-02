package com.devjzapata.simpleenvoice.facturas.repositories;

import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByCodigoFactura(String codigoFactura);

}
