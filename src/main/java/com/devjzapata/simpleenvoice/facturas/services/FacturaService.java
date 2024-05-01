package com.devjzapata.simpleenvoice.facturas.services;

import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FacturaService  {

    Page<Factura> obtenerTodos();
    Page<Factura> obtenerPorClienteNombre(String keyword, Pageable pageable);

    Factura crearFactura(Factura factura);

    Factura obtenerFactura(Long id);

    Factura updateFactura(Long id, Factura factura);

    void eliminarFactura(Long id);

    Factura obtenerFacturaPorCodigo(String codigo);


    void albaranFacturado(Factura factura, boolean facturado);
}
