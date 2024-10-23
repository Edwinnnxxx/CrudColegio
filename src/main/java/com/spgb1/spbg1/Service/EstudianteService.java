package com.spgb1.spbg1.Service;

import com.spgb1.spbg1.InterfaceService.IEstudianteService;
import com.spgb1.spbg1.Model.Estudiante;
import com.spgb1.spbg1.Model.Profesor;
import com.spgb1.spbg1.Model.Grupo;
import com.spgb1.spbg1.Repository.REstudiante;
import com.spgb1.spbg1.Repository.RProfesor;
import com.spgb1.spbg1.Repository.RGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    private REstudiante repositorio;

    @Autowired
    private RProfesor profesorRepository;

    @Autowired
    private RGrupo grupoRepository;

    @Override
    public List<Estudiante> listar() {
        return repositorio.findAll();
    }

    @Override
    public void guardar(Estudiante est) {
        repositorio.save(est);
    }

    @Override
    public Optional<Estudiante> editar(int id) {
        return repositorio.findById(id);
    }

    @Override
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    @Override
    public void asignarProfesorYGrupo(int profesorId, int noGrupo, List<Integer> estudiantesIds) {
        Optional<Profesor> profesorOpt = profesorRepository.findById(profesorId);
        Optional<Grupo> grupoOpt = grupoRepository.findById(noGrupo);

        if (profesorOpt.isPresent() && grupoOpt.isPresent()) {
            Profesor profesor = profesorOpt.get();
            Grupo grupo = grupoOpt.get();
            List<Estudiante> estudiantes = repositorio.findAllById(estudiantesIds);

            for (Estudiante estudiante : estudiantes) {
                estudiante.setProfesor(profesor);
                estudiante.setGrupo(grupo); // Asignar el grupo al estudiante
            }
            repositorio.saveAll(estudiantes);
        }
    }
}
