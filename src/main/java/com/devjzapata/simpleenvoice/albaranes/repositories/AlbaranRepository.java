package com.devjzapata.simpleenvoice.albaranes.repositories;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AlbaranRepository extends JpaRepository<Albaran,Long> {

    List<Albaran> findByLavadoId(Long lavadoId);
    Page<Albaran> findByClienteContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Albaran> findByClienteNombreContainingIgnoreCase(String keyword, Pageable pageable);



}
