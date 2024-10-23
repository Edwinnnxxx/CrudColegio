package com.spgb1.spbg1.Repository;

import com.spgb1.spbg1.Model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RGrupo extends JpaRepository<Grupo, Integer> {

}
