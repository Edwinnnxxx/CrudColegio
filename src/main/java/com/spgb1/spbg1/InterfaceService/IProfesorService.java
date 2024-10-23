package com.spgb1.spbg1.InterfaceService;

import com.spgb1.spbg1.Model.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    List<Profesor> listar();

    void guardar(Profesor pro);

    Optional<Profesor> editar(int id);

    void eliminar(int id);
}
