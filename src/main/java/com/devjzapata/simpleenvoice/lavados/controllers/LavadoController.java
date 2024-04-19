package com.devjzapata.simpleenvoice.lavados.controllers;

import com.devjzapata.simpleenvoice.lavados.entities.Lavado;
import com.devjzapata.simpleenvoice.lavados.repositories.LavadoRepository;
import com.devjzapata.simpleenvoice.lavados.services.LavadoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping({"/lavados"})
@Controller
public class LavadoController {

    @Autowired
    private LavadoService lavadoService;

    @Autowired
    LavadoRepository lavadoRepository;

    @GetMapping()
    public String listarLavados(Model model, @RequestParam(defaultValue = "1")int page){

        try{
            int size=25;
            List<Lavado> lavados = new ArrayList<>();
            Pageable paging = PageRequest.of(page -1, size);
            Page<Lavado> pageLavados = null;

            pageLavados = lavadoRepository.findAll(paging);
            int current = pageLavados.getNumber() +1;
            System.out.println("Page: "+ page);
            System.out.println("TotalPages: "+ pageLavados.getTotalPages());
            lavados = pageLavados.getContent();
            model.addAttribute("lavadosLista", lavados);
            model.addAttribute("currentPage", current);
            model.addAttribute("totalItems", pageLavados.getTotalElements());
            model.addAttribute("totalPages", pageLavados.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("request", "lavados");

        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }

        return "lavados/listar";
    }

    @GetMapping("/crear")
    public String formularioLavadoNuevo(Model model){

        model.addAttribute("lavado", new Lavado());
        model.addAttribute("accion","/lavados/crear");
        return "lavados/formulario";
    }

    @PostMapping("/crear")
    public String guardarLavado(@ModelAttribute Lavado lavado, Model model){
        try {
            lavadoService.crearLavado(lavado);
            return "redirect:/lavados";

        }catch (ConstraintViolationException e){
            model.addAttribute("error",
                    e.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList()));

            return "lavados/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarLavado(@PathVariable Long id,Model model){
        Lavado lavado = lavadoService.obtenerLavado(id);
        if (lavado != null){
            model.addAttribute("lavado", lavado);
            model.addAttribute("accion", "/lavados/editar/"+id);
            System.out.println("Lavado Controller: "+ lavado);
            return "lavados/formulario";
        }
        return "redirect:/lavados";
    }

    @PostMapping("/editar/{id}")
    public String formularioUpdateLavado(@PathVariable Long id, @ModelAttribute Lavado lavado, RedirectAttributes redirAttrs){
        try {
            lavadoService.updateLavado(id, lavado);
            redirAttrs.addFlashAttribute("actualizado","success");
            redirAttrs.addFlashAttribute("message","El Lavado ha sido actualizado con exito!");
        }catch (Exception e){
            redirAttrs.addFlashAttribute("actualizado","error");
            redirAttrs.addFlashAttribute("message","El lavado no se ha podido actualizar. " + e.getMessage() );
        }

        return "redirect:/lavados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLavado(@PathVariable Long id, RedirectAttributes redirAttrs){
        try{
            lavadoService.eliminarLavado(id);
            redirAttrs.addFlashAttribute("eliminado", "success");
            redirAttrs.addFlashAttribute("message", "El lavado ha sido eliminado con exito");

        }catch (Exception e){
            redirAttrs.addFlashAttribute("eliminado", "error");
            redirAttrs.addFlashAttribute("message", "El lavado no se ha eliminado porque " +
                    "tiene albaranes o facturas asociadas");
        }
        return "redirect:/lavados";
    }
}
