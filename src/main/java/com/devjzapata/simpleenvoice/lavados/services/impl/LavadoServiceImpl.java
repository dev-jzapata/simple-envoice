package com.devjzapata.simpleenvoice.lavados.services.impl;

import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import com.devjzapata.simpleenvoice.lavados.repositories.LavadoRepository;
import com.devjzapata.simpleenvoice.lavados.services.LavadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LavadoServiceImpl implements LavadoService {

    @Autowired
    private LavadoRepository lavadoRepository;

    @Override
    public List<Lavado> obtenerTodos() {
        return lavadoRepository.findAll();
    }

    @Override
    public Lavado crearLavado(Lavado lavado) {
        return lavadoRepository.save(lavado);
    }

    @Override
    public Lavado obtenerLavado(Long id) {

        return lavadoRepository.findById(id).orElse(null);
    }

    @Override
    public Lavado updateLavado(Long id, Lavado lavado) {
        Lavado lavadoDB = lavadoRepository.findById(id).orElse(null);
        if (lavadoDB != null){
            lavadoDB.setNombre(lavado.getNombre());
            lavadoRepository.save(lavadoDB);
        }
        return null;
    }

    @Override
    public void eliminarLavado(Long id) {
        lavadoRepository.deleteById(id);
    }
}
