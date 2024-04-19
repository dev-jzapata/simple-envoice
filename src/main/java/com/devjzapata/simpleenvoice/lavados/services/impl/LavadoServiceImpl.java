package com.devjzapata.simpleenvoice.lavados.services.impl;

import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import com.devjzapata.simpleenvoice.facturas.repositories.FacturaRepository;
import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import com.devjzapata.simpleenvoice.lavados.repositories.LavadoRepository;
import com.devjzapata.simpleenvoice.lavados.services.LavadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LavadoServiceImpl implements LavadoService {

    @Autowired
    private LavadoRepository lavadoRepository;

    @Override
    public Page<Lavado> obtenerTodos() {
        int size= lavadoRepository.findAll().size();
        Pageable pagin = PageRequest.of(0,25);

        Page<Lavado> lavadoPage = null;
        lavadoPage = lavadoRepository.findAll(pagin);

        return lavadoPage;
    }


    @Override
    public Lavado crearLavado(Lavado lavado) {
        return lavadoRepository.save(lavado);
    }

    @Override
    public Lavado obtenerLavado(Long id) {
        System.out.println("Lavados: "+ lavadoRepository.findById(id).orElse(null));
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
