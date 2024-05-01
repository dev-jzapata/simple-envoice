package com.devjzapata.simpleenvoice.security.configurations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "zapata";
        String encodedPassoword = encoder.encode(rawPassword);
        System.out.println(encodedPassoword);
    }
}
