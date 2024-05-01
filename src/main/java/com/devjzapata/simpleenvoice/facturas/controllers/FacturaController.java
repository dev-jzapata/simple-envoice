package com.devjzapata.simpleenvoice.facturas.controllers;

import com.devjzapata.simpleenvoice.albaranes.services.AlbaranService;
import com.devjzapata.simpleenvoice.clientes.services.ClienteService;
import com.devjzapata.simpleenvoice.facturas.entities.Factura;
import com.devjzapata.simpleenvoice.facturas.repositories.FacturaRepository;
import com.devjzapata.simpleenvoice.facturas.services.FacturaService;
import com.devjzapata.simpleenvoice.reports.FacturaExporterPDF;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/facturas")
public class FacturaController {


    @Autowired
    private FacturaService facturaService;

    @Autowired
    private AlbaranService albaranService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public String getListaFacturas(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue = "1")int page)
    {
        try {

            int size = 25;
            List<Factura> facturas = new ArrayList<>();

            Pageable paging = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<Factura> pageFacturas = null;


            if (keyword == null) {
                pageFacturas = facturaService.obtenerTodos();
            } else {
                pageFacturas = facturaService.obtenerPorClienteNombre(keyword, paging);
            }

            int current = pageFacturas.getNumber() + 1;

            facturas = pageFacturas.getContent();
            model.addAttribute("facturasLista", facturas);
            model.addAttribute("currentPage", current);
            model.addAttribute("totalItems", pageFacturas.getTotalElements());
            model.addAttribute("totalPages", pageFacturas.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("request", "facturas");
            model.addAttribute("keyword", keyword);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Keyword Error: " + keyword);
            System.out.println("Keyword Error: " + e.getMessage());
        }
        System.out.println("keyword: " + keyword);

        return "facturas/listar";
    }

    @GetMapping("/seleccionar-cliente")
    public String formularioSeleccionarCliente(Model model) {
        model.addAttribute("clientes", clienteService.obtenertodos());
        model.addAttribute("accion", "/facturas/crear");
        return "facturas/formulario-clientes";
    }
    @PostMapping("/crear")
    public String formularioClienteSeleccionado(Model model, @RequestParam Long cliente) {

        model.addAttribute("factura", new Factura());
        model.addAttribute("accion", "/facturas/guardar");
        model.addAttribute("albaranes", albaranService.obtenerPorClienteYFacturado(cliente, false));
        model.addAttribute("clientes", clienteService.obtenerCliente(cliente));

        return "facturas/formulario";

    }

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura, Model model){
        System.out.println("Factura: "+ factura);
        System.out.println("Factura: "+ factura.getAlbaranes());
        System.out.println("Model: "+ model);

        try {
            facturaService.crearFactura(factura);
            return "redirect:/facturas";


        }catch (Exception e){
            System.out.println("ERROR: "+ e);

            return "redirect:/facturas";
        }
    }

    @GetMapping("/ver/{id}")
    public String formularioVerFactura(@PathVariable Long id,Model model){
        Factura factura = facturaService.obtenerFactura(id);
        if (factura != null){
            model.addAttribute("factura", factura);
            model.addAttribute("accion", "/factura/editar/"+id);

            return "facturas/formulario-ver";
        }
        return "redirect:/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id, RedirectAttributes redirAttrs){
        try{
            facturaService.eliminarFactura(id);
            redirAttrs.addFlashAttribute("eliminado", "success");
            redirAttrs.addFlashAttribute("message", "Factura ha sido eliminado con exito");

        }catch (Exception e){
            redirAttrs.addFlashAttribute("eliminado", "error");
            redirAttrs.addFlashAttribute("message", "Factura no se ha eliminado porque " +
                    " Albaranes asociados");
        }
        return "redirect:/facturas";
    }
    @GetMapping("/pdf/{id}")
    public void generarReportePdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("Application/Pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String  headerValue = "attachment; filename=factura"+currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);

        Factura factura = facturaService.obtenerFactura(id);

        FacturaExporterPDF cursoExporterPDF = new FacturaExporterPDF(factura);
        cursoExporterPDF.export(response);
    }



}
