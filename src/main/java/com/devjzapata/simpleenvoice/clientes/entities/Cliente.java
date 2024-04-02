package com.devjzapata.simpleenvoice.clientes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String cif;

    private int telefono;

    private int telefono2;

    private String email;

    private String direccion;

    private int cp;

    private String ciudad;

    private String provincia;

    private String pais;

}
