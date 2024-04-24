package com.devjzapata.simpleenvoice.albaranes.controllers;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.devjzapata.simpleenvoice.albaranes.services.AlbaranService;
import com.devjzapata.simpleenvoice.clientes.services.ClienteService;
import com.devjzapata.simpleenvoice.lavados.services.LavadoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/albaranes")
public class AlbaranController {

    @Autowired
    private LavadoService lavadoService;

    @Autowired
    private AlbaranService albaranService;
    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public String getListaAlbaranes(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue = "1")int page){
        System.out.println("Keyword Init: "+ keyword);

        try{

            int size=25;
            List<Albaran> albaranes = new ArrayList<>();

            Pageable paging = PageRequest.of(page -1, size, Sort.by("id").descending());
            Page<Albaran> pageAlbaranes = null;


            if (keyword == null){
                pageAlbaranes = albaranService.obtenerTodos();
            }else{
                pageAlbaranes = albaranService.obtenerPorClienteNombre(keyword, paging);
            }

            int current = pageAlbaranes.getNumber() +1;

            albaranes = pageAlbaranes.getContent();
            model.addAttribute("albaranesLista", albaranes);
            model.addAttribute("currentPage", current);
            model.addAttribute("totalItems", pageAlbaranes.getTotalElements());
            model.addAttribute("totalPages", pageAlbaranes.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("request", "albaranes");
            model.addAttribute("keyword", keyword);

        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }

       return "albaranes/listar";
    }

    @GetMapping("/crear")
    public String formularioAlbaranNuevo(Model model) {


        model.addAttribute("albaran", new Albaran());
        model.addAttribute("accion", "/albaranes/crear");
        model.addAttribute("clientes", clienteService.obtenertodos());
        model.addAttribute("lavados", lavadoService.obtenerTodos());

        return "albaranes/formulario";
    }
    @PostMapping("/crear")
    public String guardarAlbaran(@ModelAttribute Albaran albaran, Model model){
        System.out.println("Albaran guardado: "+ albaran);

        try {
            albaranService.crearAlbaran(albaran);
            return "redirect:/albaranes";

        }catch (ConstraintViolationException e){
            model.addAttribute("error",
                    e.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));

            return "albaranes/formulario";
        }

    }
    @GetMapping("/editar/{id}")
    public String formularioEditarAlbaran(@PathVariable Long id,Model model){
        Albaran albaran = albaranService.obtenerAlbaran(id);
        if (albaran != null){
            model.addAttribute("albaran", albaran);
            model.addAttribute("accion", "/albaranes/editar/"+id);
            model.addAttribute("clientes", clienteService.obtenertodos());
            model.addAttribute("lavados", lavadoService.obtenerTodos());

            return "albaranes/formulario";
        }
        return "redirect:/albaranes";
    }

    @PostMapping("/editar/{id}")
    public String formularioUpdateAlbaran(@PathVariable Long id, @ModelAttribute Albaran albaran, RedirectAttributes redirAttrs){
        try {
            albaranService.updateAlbaran(id, albaran);
            redirAttrs.addFlashAttribute("actualizado","success");
            redirAttrs.addFlashAttribute("message","El Albaran ha sido actualizado con exito!");
        }catch (Exception e){
            redirAttrs.addFlashAttribute("actualizado","error");
            redirAttrs.addFlashAttribute("message","El Albaran no se ha podido actualizar. " + e.getMessage() );
        }

        return "redirect:/albaranes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlbaran(@PathVariable Long id, RedirectAttributes redirAttrs){
        try{
            albaranService.eliminarAlbaran(id);
            redirAttrs.addFlashAttribute("eliminado", "success");
            redirAttrs.addFlashAttribute("message", "El Albaran ha sido eliminado con exito");

        }catch (Exception e){
            redirAttrs.addFlashAttribute("eliminado", "error");
            redirAttrs.addFlashAttribute("message", "El Albaran no se ha eliminado porque " +
                    " facturas asociadas");
        }
        return "redirect:/albaranes";
    }




}
