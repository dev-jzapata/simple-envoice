package com.devjzapata.simpleenvoice.clientes.services;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {

    Page<Cliente> obtenertodos();

    Cliente obtenerCliente(Long id);

    Cliente crearCliente(Cliente cliente);

    Cliente updateCliente(Long id, Cliente cliente);

    void eliminarCliente(Long id);


}
