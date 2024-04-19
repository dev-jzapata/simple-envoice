package com.devjzapata.simpleenvoice.albaranes.entities;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "albaran")
public class Albaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(nullable = false)
    private String matricula;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lavado_id", referencedColumnName = "id")
    private Lavado lavado;

    @Column(nullable = false)
    private double precio;

    @ManyToMany(mappedBy = "albaranes")
    private List<Factura> facturas = new ArrayList<>();

    private boolean facturado;
}
