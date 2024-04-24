package com.devjzapata.simpleenvoice.albaranes.services.impl;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import com.devjzapata.simpleenvoice.albaranes.services.AlbaranService;
import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbaranServiceImpl implements AlbaranService {



    @Autowired
    AlbaranRepository albaranRepository;

    @Override
    public Page<Albaran> obtenerTodos() {

        Pageable pagin = PageRequest.of(0,25);

        return albaranRepository.findAll(pagin);

    }
    @Override
    public Page<Albaran> obtenerPorClienteNombre(String keyword, Pageable pageable) {
        Pageable pagin = PageRequest.of(0,25);

        Page<Albaran> albaranPage = null;
        albaranPage = albaranRepository.findByClienteNombreContainingIgnoreCase(keyword, pageable);

        return albaranPage;
    }

    public List<Albaran> obtenerPorClienteYFacturado(Long id, Boolean facturado){
        List<Albaran> albaranList = new ArrayList<>();
        albaranList = albaranRepository.findByClienteIdAndFacturado(id, facturado);

        return  albaranList;
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
