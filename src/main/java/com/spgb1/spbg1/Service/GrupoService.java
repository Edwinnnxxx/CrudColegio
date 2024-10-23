package com.spgb1.spbg1.Service;

import com.spgb1.spbg1.InterfaceService.IGrupoService;
import com.spgb1.spbg1.Model.Grupo;
import com.spgb1.spbg1.Repository.RGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class GrupoService implements IGrupoService {
    @Autowired
    private RGrupo repositorio;

    @Override
    public List<Grupo> listar() {
        return repositorio.findAll();
    }

    @Override
    public void guardar(Grupo grupo) {
        repositorio.save(grupo);
    }

    @Override
    public Optional<Grupo> editar(int id) {
        return repositorio.findById(id);
    }

    @Override
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    public void asignarEstudiantesAGrupo(int grupoId, List<Integer> estudiantesIds) {
        Optional<Grupo> grupoOpt = repositorio.findById(grupoId);
        if (grupoOpt.isPresent()) {
            Grupo grupo = grupoOpt.get();
            // Aquí puedes cargar los estudiantes y asignarlos al grupo
        }
    }
}
