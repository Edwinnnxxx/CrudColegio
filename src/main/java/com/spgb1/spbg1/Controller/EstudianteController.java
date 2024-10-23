package com.spgb1.spbg1.Controller;

import com.spgb1.spbg1.InterfaceService.IEstudianteService;
import com.spgb1.spbg1.InterfaceService.IProfesorService;
import com.spgb1.spbg1.InterfaceService.IGrupoService;
import com.spgb1.spbg1.Model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IGrupoService grupoService;

    @GetMapping("/listarestudiantes")
    public String listarEstudiantes(Model model) {
        model.addAttribute("titulo", "Lista de Estudiantes");
        model.addAttribute("cuerpo", "LISTA DE ESTUDIANTES");
        model.addAttribute("datos", estudianteService.listar());
        return "estudiantes"; // Asegúrate de que el nombre de la plantilla sea "estudiantes"
    }



    @GetMapping("/nuevoestudiante")
    public String formAgregarEstudiantes(Model model){
        model.addAttribute("titulo", "Nuevo Estudiante");
        model.addAttribute("cuerpo", "Agregar un nuevo estudiante");
        model.addAttribute("entity", new Estudiante());
        model.addAttribute("guardarUrl", "/api/estudiante/guardar");
        model.addAttribute("listarUrl", "/api/estudiante/listarestudiantes");
        return "nuevo";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante est){
        estudianteService.guardar(est);
        return "redirect:/api/estudiante/listarestudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable("id") int id, Model model){
        Optional<Estudiante> estudiante = estudianteService.editar(id);
        if (estudiante.isPresent()) {
            model.addAttribute("entity", estudiante.get());
            model.addAttribute("guardarUrl", "/api/estudiante/guardar");
            return "nuevo";
        } else {
            return "redirect:/api/estudiante/listarestudiantes";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") int id){
        estudianteService.eliminar(id);
        return "redirect:/api/estudiante/listarestudiantes";
    }

    @GetMapping("/asignar")
    public String formAsignar(Model model) {
        model.addAttribute("titulo", "Asignar Profesor y Grupo");
        model.addAttribute("cuerpo", "Asignar Estudiantes a Profesor y Grupo");
        model.addAttribute("profesores", profesorService.listar());
        model.addAttribute("grupos", grupoService.listar());
        model.addAttribute("estudiantes", estudianteService.listar());
        return "asignar";
    }

    @PostMapping("/guardarAsignacion")
    public String guardarAsignacion(@RequestParam("profesorId") int profesorId,
                                    @RequestParam("grupoId") int grupoId,
                                    @RequestParam("estudiantesIds") List<Integer> estudiantesIds) {
        estudianteService.asignarProfesorYGrupo(profesorId, grupoId, estudiantesIds);
        return "redirect:/";
    }

    @GetMapping("/mostrarRelaciones")
    public String mostrarRelaciones(Model model) {
        model.addAttribute("titulo", "Relaciones de Estudiantes, Profesores y Grupos");
        model.addAttribute("cuerpo", "Vista de Relaciones");
        model.addAttribute("datos", estudianteService.listar());
        return "relaciones";
    }
}
