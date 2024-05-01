package com.devjzapata.simpleenvoice.usuarios.repository;

import com.devjzapata.simpleenvoice.usuarios.entitites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
