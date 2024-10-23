package com.spgb1.spbg1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "grupo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_grupo")
    private int noGrupo;

    @Column(nullable = false)
    private String semestre;

    @OneToOne(mappedBy = "grupo")
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
