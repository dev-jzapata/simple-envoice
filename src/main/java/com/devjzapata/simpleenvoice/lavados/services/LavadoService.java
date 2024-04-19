package com.devjzapata.simpleenvoice.lavados.services;

import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LavadoService {

    Page<Lavado> obtenerTodos();

    Lavado crearLavado(Lavado lavado);

    Lavado obtenerLavado(Long id);

    Lavado updateLavado(Long id, Lavado lavado);

    void eliminarLavado(Long id);
}
