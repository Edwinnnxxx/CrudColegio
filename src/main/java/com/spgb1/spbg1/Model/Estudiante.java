package com.spgb1.spbg1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "codigo_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "no_grupo")
    private Grupo grupo;

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}