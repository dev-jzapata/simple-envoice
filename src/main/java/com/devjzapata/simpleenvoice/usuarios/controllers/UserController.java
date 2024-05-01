package com.devjzapata.simpleenvoice.usuarios.controllers;

import com.devjzapata.simpleenvoice.clientes.services.ClienteService;
import com.devjzapata.simpleenvoice.usuarios.entitites.User;
import com.devjzapata.simpleenvoice.usuarios.repository.RoleRepository;
import com.devjzapata.simpleenvoice.usuarios.services.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String obtenerTodosUsuarios(Model model){
        List<User> userList = userService.obtenerTodos();
        model.addAttribute("usuariosLista", userList);
        model.addAttribute("request", "usuarios");
        System.out.println("Usuarios: "+userList);
        return "usuarios/listar";
    }

    @GetMapping("/crear")
    public String formularioUserNuevo(Model model){

        model.addAttribute("usuario", new User());
        model.addAttribute("clientes", clienteService.obtenertodos());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("accion", "/usuarios/crear");

        return "usuarios/form";
    }

    @PostMapping("/crear")
    public String formularioUserGuardar(@ModelAttribute User user, Model model){

        try {
            userService.crearUsuario(user);
            return "redirect:/usuarios";

        }catch (ConstraintViolationException e){
            model.addAttribute("error",
                    e.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));

            return "usuarios/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarUsuario(@PathVariable Long id, Model model){
        User user = userService.obtenerUsuario(id);
        if (user != null){
            model.addAttribute("usuario", user);
            model.addAttribute("clientes", clienteService.obtenertodos());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("accion", "/usuarios/editar/"+id);

            return "usuarios/form";
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/editar/{id}")
    public String formularioUpdateUsuario(@PathVariable Long id, @ModelAttribute User user, RedirectAttributes redirAttrs){
        try {
            userService.updateUsuario(id, user);
            redirAttrs.addFlashAttribute("actualizado","success");
            redirAttrs.addFlashAttribute("message","El Usuario ha sido actualizado con exito!");
        }catch (Exception e){
            redirAttrs.addFlashAttribute("actualizado","error");
            redirAttrs.addFlashAttribute("message","El Usuario no se ha podido actualizar. " + e.getMessage() );
        }

        return "redirect:/usuarios";
    }


}
