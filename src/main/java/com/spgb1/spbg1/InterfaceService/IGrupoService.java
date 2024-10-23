package com.spgb1.spbg1.InterfaceService;

import com.spgb1.spbg1.Model.Grupo;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    List<Grupo> listar();

    void guardar(Grupo grupo);

    Optional<Grupo> editar(int id);

    void eliminar(int id);
}
