package com.devjzapata.simpleenvoice.albaranes.repositories;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbaranRepository extends JpaRepository<Albaran,Long> {

    Page<Albaran> findByClienteNombreContainingIgnoreCase(String keyword, Pageable pageable);

    List<Albaran> findByClienteIdAndFacturado(Long cliente, Boolean facturado);



}
