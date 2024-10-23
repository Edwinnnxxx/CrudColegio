package com.spgb1.spbg1.Service;

import com.spgb1.spbg1.InterfaceService.IProfesorService;
import com.spgb1.spbg1.Model.Profesor;
import com.spgb1.spbg1.Repository.RProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ProfesorService implements IProfesorService {
    @Autowired
    private RProfesor repositorio;

    @Override
    public List<Profesor> listar() {
        return repositorio.findAll();
    }

    @Override
    public void guardar(Profesor pro) {
        repositorio.save(pro);
    }

    @Override
    public Optional<Profesor> editar(int id) {
        return repositorio.findById(id);
    }

    @Override
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
