package com.devjzapata.simpleenvoice.clientes.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "El Campo Nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "El Campo CIF no puede estar vacio")
    private String cif;

    @NotNull(message = "El Campo Telefono no puede estar vacio")
    private int telefono;

    private int telefono2;

    @NotBlank(message = "El Campo email no puede estar vacio")
    private String email;

    @NotBlank(message = "El Campo dirección no puede estar vacio")
    private String direccion;

    private int cp;

    @NotBlank(message = "El Campo Ciudad no puede estar vacio")
    private String ciudad;

    @NotBlank(message = "El Campo Provincia no puede estar vacio")
    private String provincia;

    @NotBlank(message = "El Campo Pais no puede estar vacio")
    private String pais;

}
