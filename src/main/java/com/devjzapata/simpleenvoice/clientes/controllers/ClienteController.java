package com.devjzapata.simpleenvoice.clientes.controllers;

import com.devjzapata.simpleenvoice.clientes.entities.Cliente;
import com.devjzapata.simpleenvoice.clientes.repositories.ClienteRepository;
import com.devjzapata.simpleenvoice.clientes.services.ClienteService;
import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;
    @GetMapping()
    public String getListaClientes(Model model, @RequestParam(defaultValue = "1")int page){

        try{
            int size=25;
            List<Cliente> clientes = new ArrayList<>();
            Pageable paging = PageRequest.of(page -1, size);
            Page<Cliente> pageCientes = null;

            pageCientes = clienteRepository.findAll(paging);
            int current = pageCientes.getNumber() +1;
            System.out.println("Page: "+ page);
            System.out.println("TotalPages: "+ pageCientes.getTotalPages());
            clientes = pageCientes.getContent();
            model.addAttribute("clientesLista", clientes);
            model.addAttribute("currentPage", current);
            model.addAttribute("totalItems", pageCientes.getTotalElements());
            model.addAttribute("totalPages", pageCientes.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("request", "clientes");
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "clientes/listar";
    }

    @GetMapping("/crear")
    public String formularioClienteNuevo(Model model) {

        model.addAttribute("cliente", new Cliente());
        model.addAttribute("accion", "/clientes/crear");
        return "clientes/formulario";
    }
    @PostMapping("/crear")
    public String guardarCliente(@ModelAttribute Cliente cliente, Model model){
        try {
            clienteService.crearCliente(cliente);
            return "redirect:/clientes";

        }catch (ConstraintViolationException e){
            model.addAttribute("error",
                    e.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));

            return "clientes/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarCliente(@PathVariable Long id,Model model){
        Cliente cliente = clienteService.obtenerCliente(id);
        if (cliente != null){
            model.addAttribute("cliente", cliente);
            model.addAttribute("accion", "/clientes/editar/"+id);

            return "clientes/formulario";
        }
        return "redirect:/clientes";
    }


    @PostMapping("/editar/{id}")
    public String formularioUpdateCliente(@PathVariable Long id, @ModelAttribute Cliente cliente, RedirectAttributes redirAttrs){
        try {
            clienteService.updateCliente(id, cliente);
            redirAttrs.addFlashAttribute("actualizado","success");
            redirAttrs.addFlashAttribute("message","El Cliente ha sido actualizado con exito!");
        }catch (Exception e){
            redirAttrs.addFlashAttribute("actualizado","error");
            redirAttrs.addFlashAttribute("message","El Cliente no se ha podido actualizar. " + e.getMessage() );
        }

        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirAttrs){
        try{
            clienteService.eliminarCliente(id);
            redirAttrs.addFlashAttribute("eliminado", "success");
            redirAttrs.addFlashAttribute("message", "El Cliente ha sido eliminado con exito");

        }catch (Exception e){
            redirAttrs.addFlashAttribute("eliminado", "error");
            redirAttrs.addFlashAttribute("message", "El Cliente no se ha eliminado porque " +
                    "tiene albaranes o facturas asociadas");
        }
        return "redirect:/clientes";
    }
}
