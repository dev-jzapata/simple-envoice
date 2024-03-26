package com.devjzapata.simpleenvoice.lavados.repositories;

import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LavadoRepository extends JpaRepository<Lavado, Long> {



}
