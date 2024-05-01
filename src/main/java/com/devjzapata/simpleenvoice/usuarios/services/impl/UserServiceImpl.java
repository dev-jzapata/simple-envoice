package com.devjzapata.simpleenvoice.usuarios.services.impl;

import com.devjzapata.simpleenvoice.usuarios.entitites.User;
import com.devjzapata.simpleenvoice.usuarios.repository.UserRepository;
import com.devjzapata.simpleenvoice.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> obtenerTodos() {

        return userRepository.findAll();
    }

    @Override
    public User crearUsuario(User user) {
        User usuario = new User();
        usuario.setId(user.getId());
        usuario.setUsername(user.getUsername());
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));
        usuario.setRoles(user.getRoles());
        usuario.setEnabled(user.getEnabled());
        usuario.setCliente(user.getCliente());
        return userRepository.save(usuario);
    }

    @Override
    public User obtenerUsuario(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUsuario(Long id, User user) {
        User usuario = userRepository.findById(id).orElse(null);
        if (usuario != null){
            usuario.setUsername(user.getUsername());
            usuario.setPassword(user.getPassword());
            usuario.setEnabled(user.getEnabled());
            usuario.setCliente(user.getCliente());
            usuario.setRoles(user.getRoles());
            userRepository.save(usuario);
        }

        return null;
    }

    @Override
    public void deleteUsuario(Long id) {
        userRepository.deleteById(id);
    }
}
