package com.devjzapata.simpleenvoice.albaranes.services;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbaranService {

    Page<Albaran> obtenerTodos();

    Albaran crearAlbaran(Albaran albaran);

    Albaran obtenerAlbaran(Long id);

    Albaran updateAlbaran(Long id, Albaran albaran);

    void eliminarAlbaran(Long id);

}
