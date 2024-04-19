package com.devjzapata.simpleenvoice.albaranes.repositories;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbaranRepository extends JpaRepository<Albaran,Long> {

    List<Albaran> findByLavadoId(Long lavadoId);

}
