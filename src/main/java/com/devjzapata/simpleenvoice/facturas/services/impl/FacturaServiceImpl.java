package com.devjzapata.simpleenvoice.facturas.services.impl;

import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import com.devjzapata.simpleenvoice.facturas.repositories.FacturaRepository;
import com.devjzapata.simpleenvoice.facturas.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> obtenerTodos() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura crearFactura(Factura factura) {
        factura.setTotal(sumarTotalFactura(factura.getId()));
        albaranFacturado(factura, true);
        return facturaRepository.save(factura);
    }

    @Override
    public Factura obtenerFactura(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public Factura updateFactura(Long id, Factura factura) {

        Factura facturaDB = facturaRepository.findById(id).orElse(null);
        if (facturaDB != null){
            facturaDB.setFecha(factura.getFecha());
            facturaDB.setCodigoFactura(factura.getCodigoFactura());
            facturaDB.setCliente(factura.getCliente());
            facturaDB.setAlbaranes(factura.getAlbaranes());
            facturaDB.setTotal(sumarTotalFactura(factura.getId()));
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

    public double sumarTotalFactura(Long id){

        double precio =0;

        Factura facturas = facturaRepository.findById(1L).orElse(null);

        for(int i=0; i < facturas.getAlbaranes().size();i++) {
            precio += facturas.getAlbaranes().get(i).getPrecio();
        }

        return precio;
    }

    public void albaranFacturado(Factura factura, boolean facturado){

        for(int i=0; i < factura.getAlbaranes().size();i++) {
            factura.getAlbaranes().get(i).setFacturado(facturado);
        }
    }
}
