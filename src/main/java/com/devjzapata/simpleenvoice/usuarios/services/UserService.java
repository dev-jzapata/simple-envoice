package com.devjzapata.simpleenvoice.usuarios.services;

import com.devjzapata.simpleenvoice.usuarios.entitites.User;

import java.util.List;

public interface UserService {

    List<User> obtenerTodos();

    User crearUsuario(User user);

    User obtenerUsuario(Long id);

    User updateUsuario(Long id, User user);

    void deleteUsuario(Long id);
}
