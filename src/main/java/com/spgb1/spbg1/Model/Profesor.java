package com.spgb1.spbg1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nombre;

    private String telefono;

    @OneToMany(targetEntity = Estudiante.class, fetch = FetchType.LAZY, mappedBy = "profesor")
    private List<Estudiante> estudiantes;

    @OneToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}
