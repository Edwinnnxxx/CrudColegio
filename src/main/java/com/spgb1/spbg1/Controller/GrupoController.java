package com.spgb1.spbg1.Controller;

import com.spgb1.spbg1.InterfaceService.IGrupoService;
import com.spgb1.spbg1.Model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/grupo")
public class GrupoController {

    @Autowired
    private IGrupoService service;

    @GetMapping("/listargrupos")
    public String listarGrupos(Model model){
        model.addAttribute("titulo", "Spring DB - Grupos");
        model.addAttribute("cuerpo", "LISTA DE GRUPOS");
        model.addAttribute("datos", service.listar());
        return "grupos"; // Cambia esto para que coincida con el nombre de la plantilla que crearemos
    }

    @GetMapping("/nuevogrupo")
    public String formAgregarGrupos(Model model){
        model.addAttribute("titulo", "Nuevo Grupo");
        model.addAttribute("cuerpo", "Agregar un nuevo Grupo");
        model.addAttribute("entity", new Grupo());
        model.addAttribute("guardarUrl", "/api/grupo/guardar");
        model.addAttribute("listarUrl", "/api/grupo/listargrupos");
        return "nuevo1"; // Asegúrate que este nombre coincida con la plantilla real
    }

    @PostMapping("/guardar")
    public String guardarGrupo(@ModelAttribute Grupo grupo){
        service.guardar(grupo);
        return "redirect:/api/grupo/listargrupos";
    }

    @GetMapping("/editar/{noGrupo}")
    public String editarGrupo(@PathVariable("noGrupo") int noGrupo, Model model){
        Optional<Grupo> grupo = service.editar(noGrupo);
        if (grupo.isPresent()) {
            model.addAttribute("entity", grupo.get());
            model.addAttribute("guardarUrl", "/api/grupo/guardar");
            return "nuevo1";
        } else {
            return "redirect:/api/grupo/listargrupos";
        }
    }

    @GetMapping("/eliminar/{noGrupo}")
    public String eliminarGrupo(@PathVariable("noGrupo") int noGrupo){
        service.eliminar(noGrupo);
        return "redirect:/api/grupo/listargrupos";
    }
}
