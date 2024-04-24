package com.devjzapata.simpleenvoice.albaranes.services;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbaranService {

    Page<Albaran> obtenerTodos();

    Page<Albaran> obtenerPorClienteNombre(String keyword, Pageable pageable);

    List<Albaran> obtenerPorClienteYFacturado(Long cliente, Boolean facturado);


    Albaran crearAlbaran(Albaran albaran);

    Albaran obtenerAlbaran(Long id);

    Albaran updateAlbaran(Long id, Albaran albaran);

    void eliminarAlbaran(Long id);

}
