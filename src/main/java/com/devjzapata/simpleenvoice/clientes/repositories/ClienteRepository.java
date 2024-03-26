package com.devjzapata.simpleenvoice.clientes.repositories;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
