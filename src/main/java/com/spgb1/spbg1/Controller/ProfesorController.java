package com.spgb1.spbg1.Controller;

import com.spgb1.spbg1.InterfaceService.IProfesorService;
import com.spgb1.spbg1.Model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    private IProfesorService service;

    @GetMapping("/listarprofesores")
    public String listarProfesores(Model model){
        model.addAttribute("titulo", "Spring DB - Profesores");
        model.addAttribute("cuerpo", "LISTA DE PROFESORES");
        List<Profesor> listaProfesores = service.listar();
        model.addAttribute("datos", listaProfesores);
        model.addAttribute("nuevoUrl", "/api/profesor/nuevoprofesor");
        model.addAttribute("volverUrl", "/"); // URL para volver
        return "profesores"; // Asegúrate que coincida con el nombre de tu plantilla
    }

    @GetMapping("/nuevoprofesor")
    public String formAgregarProfesores(Model model){
        model.addAttribute("titulo", "Nuevo Profesor");
        model.addAttribute("cuerpo", "Agregar un nuevo profesor");
        model.addAttribute("entity", new Profesor());
        model.addAttribute("guardarUrl", "/api/profesor/guardar");
        model.addAttribute("listarUrl", "/api/profesor/listarprofesores");
        return "nuevo";
    }

    @PostMapping("/guardar")
    public String guardarProfesor(@ModelAttribute Profesor prof){
        service.guardar(prof);
        return "redirect:/api/profesor/listarprofesores";
    }

    @GetMapping("/editar/{id}")
    public String editarProfesor(@PathVariable("id") int id, Model model){
        Optional<Profesor> profesor = service.editar(id);
        if (profesor.isPresent()) {
            model.addAttribute("entity", profesor.get());
            model.addAttribute("guardarUrl", "/api/profesor/guardar");
            return "nuevo";
        } else {
            return "redirect:/api/profesor/listarprofesores";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable("id") int id) {
        try {
            service.eliminar(id);
        } catch (Exception e) {
            return "redirect:/api/profesor/listarprofesores?error=true";
        }
        return "redirect:/api/profesor/listarprofesores";
    }

}
