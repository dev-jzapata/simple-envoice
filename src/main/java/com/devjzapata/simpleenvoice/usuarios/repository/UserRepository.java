package com.devjzapata.simpleenvoice.usuarios.repository;

import com.devjzapata.simpleenvoice.usuarios.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username= :username")
    User getUserByUsername(@Param("username") String username);

    User findByUsername(String username);
}
