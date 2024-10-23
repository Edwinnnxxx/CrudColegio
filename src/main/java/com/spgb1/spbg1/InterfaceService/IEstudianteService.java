package com.spgb1.spbg1.InterfaceService;

import com.spgb1.spbg1.Model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    List<Estudiante> listar();

    void guardar(Estudiante est);

    Optional<Estudiante> editar(int id);

    void eliminar(int id);

    void asignarProfesorYGrupo(int profesorId, int noGrupo, List<Integer> estudiantesIds);
}
