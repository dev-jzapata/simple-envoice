package com.devjzapata.simpleenvoice.clientes.services;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import com.devjzapata.simpleenvoice.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Cliente> obtenertodos() {

        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerCliente(Long id) {

        return clienteRepository.findById(id).orElse(null);

    }

    @Override
    public Cliente crearCliente(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {

        Cliente clienteDB = clienteRepository.findById(id).orElse(null);
        if (clienteDB != null){
            clienteDB.setNombre(cliente.getNombre());
            clienteDB.setCif(cliente.getCif());
            clienteDB.setTelefono(cliente.getTelefono());
            clienteDB.setTelefono2(cliente.getTelefono2());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setDireccion(cliente.getDireccion());
            clienteDB.setCp(cliente.getCp());
            clienteDB.setCiudad(cliente.getCiudad());
            clienteDB.setProvincia(cliente.getProvincia());
            clienteDB.setPais(cliente.getPais());

            return clienteRepository.save(clienteDB);
        }

        return null;
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
