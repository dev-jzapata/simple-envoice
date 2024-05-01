package com.devjzapata.simpleenvoice.facturas.services.impl;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import com.devjzapata.simpleenvoice.facturas.repositories.FacturaRepository;
import com.devjzapata.simpleenvoice.facturas.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private AlbaranRepository albaranRepository;


    @Override
    public Page<Factura> obtenerTodos() {
        Pageable pagin = PageRequest.of(0,25);

        return facturaRepository.findAll(pagin);
    }

    @Override
    public Page<Factura> obtenerPorClienteNombre(String keyword, Pageable pageable) {

        return facturaRepository.findByClienteNombreContainingIgnoreCase(keyword, pageable);

    }

    @Override
    public Factura crearFactura(Factura factura) {
        if(factura.getCodigoFactura() == ""){
            factura.setCodigoFactura(null);
        }
        albaranFacturado(factura, true);
        return facturaRepository.save(factura);
    }

    @Override
    public Factura obtenerFactura(Long id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        if (factura != null){
            return factura;
        }
        return null;
    }

    @Override
    public Factura updateFactura(Long id, Factura factura) {

        Factura facturaDB = facturaRepository.findById(id).orElse(null);
        if (facturaDB != null){
            facturaDB.setFecha(factura.getFecha());
            facturaDB.setCodigoFactura(factura.getCodigoFactura());
            facturaDB.setCliente(factura.getCliente());
            facturaDB.setAlbaranes(factura.getAlbaranes());
            facturaDB.setIva(factura.getIva());
            facturaDB.setTotal(factura.getTotal());
            albaranFacturado(factura, true);
            facturaRepository.save(facturaDB);
        }

        return null;
    }

    @Override
    public void eliminarFactura(Long id) {

        facturaRepository.deleteById(id);

    }

    @Override
    public Factura obtenerFacturaPorCodigo(String codigo) {
        return facturaRepository.findByCodigoFactura(codigo).orElse(null);
    }


    @Override
    public void albaranFacturado(Factura factura, boolean facturado){

        for(int i=0; i < factura.getAlbaranes().size();i++) {
            Albaran albaran = factura.getAlbaranes().get(i);
            albaran.setFacturado(facturado);
            albaranRepository.save(albaran);
        }

    }
}
