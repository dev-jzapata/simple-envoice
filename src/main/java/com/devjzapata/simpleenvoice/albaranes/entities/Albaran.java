package com.devjzapata.simpleenvoice.albaranes.entities;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Albaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;


    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(nullable = false)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "lavado_id", referencedColumnName = "id")
    private Lavado lavado;

    @Column(nullable = false)
    private double precio;
}
