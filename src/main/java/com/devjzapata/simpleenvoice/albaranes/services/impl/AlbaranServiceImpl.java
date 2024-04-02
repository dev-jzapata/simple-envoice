package com.devjzapata.simpleenvoice.albaranes.services.impl;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import com.devjzapata.simpleenvoice.albaranes.services.AlbaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbaranServiceImpl implements AlbaranService {


    @Autowired
    AlbaranRepository albaranRepository;

    @Override
    public List<Albaran> obtenerTodos() {
        return albaranRepository.findAll();
    }

    @Override
    public Albaran crearAlbaran(Albaran albaran) {
        return albaranRepository.save(albaran);
    }

    @Override
    public Albaran obtenerAlbaran(Long id) {

        return albaranRepository.findById(id).orElse(null);
    }

    @Override
    public Albaran updateAlbaran(Long id, Albaran albaran) {
        Albaran albaranDB = albaranRepository.findById(id).orElse(null);

        if (albaranDB != null){
            albaranDB.setFecha(albaran.getFecha());
            albaranDB.setCliente(albaran.getCliente());
            albaranDB.setMatricula(albaran.getMatricula());
            albaranDB.setPrecio(albaran.getPrecio());
            albaranDB.setLavado(albaran.getLavado());
            albaranDB.setFacturado(albaran.isFacturado());
            albaranRepository.save(albaranDB);
        }

        return null;
    }

    @Override
    public void eliminarAlbaran(Long id) {
        albaranRepository.deleteById(id);
    }
}
