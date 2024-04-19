package com.devjzapata.simpleenvoice.facturas.services;

import com.devjzapata.simpleenvoice.facturas.entities.Factura;

import java.util.List;

public interface FacturaService  {

    List<Factura> obtenerTodos();

    Factura crearFactura(Factura factura);

    Factura obtenerFactura(Long id);

    Factura updateFactura(Long id, Factura factura);

    void eliminarFactura(Long id);

    Factura obtenerFacturaPorCodigo(String codigo);

    double sumarTotalFactura(Long id);

    void albaranFacturado(Factura factura, boolean facturado);
}
