package com.devjzapata.simpleenvoice.usuarios.services.impl;

import com.devjzapata.simpleenvoice.usuarios.entitites.User;
import com.devjzapata.simpleenvoice.usuarios.repository.UserRepository;
import com.devjzapata.simpleenvoice.usuarios.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(username == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetailsService(user);
    }
}
