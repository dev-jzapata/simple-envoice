package com.devjzapata.simpleenvoice.albaranes.repositories;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbaranRepository extends JpaRepository<Albaran,Long> {
}
