package com.spgb1.spbg1.Repository;

import com.spgb1.spbg1.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REstudiante extends JpaRepository<Estudiante, Integer> {

}
