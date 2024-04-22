package com.devjzapata.simpleenvoice.pruebas;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.repositories.AlbaranRepository;
import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import com.devjzapata.simpleenvoice.clientes.repositories.ClienteRepository;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import com.devjzapata.simpleenvoice.facturas.repositories.FacturaRepository;
import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import com.devjzapata.simpleenvoice.lavados.repositories.LavadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


@Component
public class Pruebas01 implements CommandLineRunner {


    @Autowired
    private LavadoRepository lavadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AlbaranRepository albaranRepository;
    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {


        Lavado lavado01 = new Lavado();
        lavado01.setId(1L);
        lavado01.setNombre("Lavado Exterior");
        lavadoRepository.save(lavado01);

        Lavado lavado02 = new Lavado();
        lavado02.setId(2L);
        lavado02.setNombre("Lavado Interior");
        lavadoRepository.save(lavado02);

        Lavado lavado03 = new Lavado();
        lavado03.setId(3L);
        lavado03.setNombre("LAvado Completo");
        lavadoRepository.save(lavado03);

        Lavado lavado04 = new Lavado();
        lavado04.setId(4L);
        lavado04.setNombre("LAvado Completo");
        lavadoRepository.save(lavado04);

        Lavado lavado05 = new Lavado();
        lavado05.setId(5L);
        lavado05.setNombre("LAvado Completo");
        lavadoRepository.save(lavado05);

        Lavado lavado06 = new Lavado();
        lavado06.setId(6L);
        lavado06.setNombre("LAvado Completo");
        lavadoRepository.save(lavado06);

        Lavado lavado07 = new Lavado();
        lavado07.setId(7L);
        lavado07.setNombre("LAvado Completo");
        lavadoRepository.save(lavado07);

        Lavado lavado08 = new Lavado();
        lavado08.setId(8L);
        lavado08.setNombre("LAvado Completo");
        lavadoRepository.save(lavado08);

        Lavado lavado13 = new Lavado();
        lavado13.setId(13L);
        lavado13.setNombre("LAvado Completo");
        lavadoRepository.save(lavado13);

        Lavado lavado09 = new Lavado();
        lavado09.setId(9L);
        lavado09.setNombre("LAvado Completo");
        lavadoRepository.save(lavado09);

        Lavado lavado10 = new Lavado();
        lavado10.setId(10L);
        lavado10.setNombre("LAvado Completo");
        lavadoRepository.save(lavado10);

        Lavado lavado11 = new Lavado();
        lavado11.setId(11L);
        lavado11.setNombre("LAvado Completo");
        lavadoRepository.save(lavado11);

        Lavado lavado12 = new Lavado();
        lavado12.setId(12L);
        lavado12.setNombre("LAvado Completo");
        lavadoRepository.save(lavado12);


        Cliente cliente01 = new Cliente();
        cliente01.setId(1L);
        cliente01.setNombre("Toyota");
        cliente01.setCif("xxxxxx1");
        cliente01.setDireccion("Calle falsa Toyota");
        cliente01.setCp(30300);
        cliente01.setCiudad("Cartagena");
        cliente01.setProvincia("Murcia");
        cliente01.setPais("España");
        cliente01.setEmail("toyota@email.com");
        cliente01.setTelefono(608825110);
        cliente01.setTelefono2(608825111);
        clienteRepository.save(cliente01);

        Cliente cliente02 = new Cliente();
        cliente02.setId(2L);
        cliente02.setNombre("Skoda");
        cliente02.setCif("xxxxxx2");
        cliente02.setDireccion("Calle falsa Skoda");
        cliente02.setCp(30301);
        cliente02.setCiudad("Cartagena");
        cliente02.setProvincia("Murcia");
        cliente02.setPais("España");
        cliente02.setEmail("skoda@email.com");
        cliente02.setTelefono(608000000);
        cliente02.setTelefono2(608000001);
        clienteRepository.save(cliente02);

        Albaran albaran01 = new Albaran();
        albaran01.setId(1L);
        albaran01.setCliente(cliente01);
        albaran01.setLavado(lavado01);
        albaran01.setFecha(LocalDate.of(2024,02,05));
        albaran01.setMatricula("5122CTM");
        albaran01.setPrecio(10.00);
        albaranRepository.save(albaran01);

        Albaran albaran02 = new Albaran();
        albaran02.setId(2L);
        albaran02.setCliente(cliente01);
        albaran02.setLavado(lavado02);
        albaran02.setFecha(LocalDate.of(2024,01,10));
        albaran02.setMatricula("1052FKW");
        albaran02.setPrecio(15.00);
        albaranRepository.save(albaran02);

        Albaran albaran03 = new Albaran();
        albaran03.setId(3L);
        albaran03.setCliente(cliente01);
        albaran03.setLavado(lavado01);
        albaran03.setFecha(LocalDate.of(2024,01,15));
        albaran03.setMatricula("7087KMG");
        albaran03.setPrecio(10.00);
        albaranRepository.save(albaran03);

        Albaran albaran04 = new Albaran();
        albaran04.setId(4L);
        albaran04.setCliente(cliente02);
        albaran04.setLavado(lavado01);
        albaran04.setFecha(LocalDate.of(2024,01,07));
        albaran04.setMatricula("8888KKK");
        albaran04.setPrecio(10.00);
        albaranRepository.save(albaran04);

        Factura factura01 = new Factura();
        factura01.setId(1L);
        factura01.setCodigoFactura("COD01");
        factura01.setCliente(cliente01);
        factura01.setAlbaranes(new ArrayList<Albaran>(Arrays.asList(albaran01, albaran02, albaran03)));
        factura01.setFecha(LocalDate.of(2024,01,30));
        factura01.setTotal(30.00);
        facturaRepository.save(factura01);

        Factura facturas = facturaRepository.findById(1L).orElse(null);

        for(int i=0; i < facturas.getAlbaranes().size();i++) {
            facturas.getAlbaranes().get(i).setFacturado(true);
        }



    }
}
