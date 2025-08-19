package com.example.literatura.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate nacimiento;
    private LocalDate muerte;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros = new ArrayList<>();

    public Autor(DatosAutor d) {
        this.nombre = d.nombre();
        this.nacimiento = d.nacimiento();
        this.muerte = d.muerte();
    }

    public Autor(String nombre, LocalDate nacimiento, LocalDate muerte) {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public LocalDate getMuerte() {
        return muerte;
    }

    public void setMuerte(LocalDate muerte) {
        this.muerte = muerte;
    }


}
