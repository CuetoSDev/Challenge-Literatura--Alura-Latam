package com.example.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    @Enumerated(EnumType.STRING)
    private String lenguajes;
    @ManyToMany
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    // Constructor que recibe el record DatosLIbro
    public Libro(DatosLIbro datos) {
        this.titulo = datos.titulo();
        this.lenguajes = datos.lenguajes();

        // convertir DatosAutor -> Autor
        if (datos.autores() != null) {
            this.autores = datos.autores().stream()
                    .map(datoAutor -> new Autor(datoAutor)) // <- necesitas un constructor en Autor
                    .collect(Collectors.toList());
        }
    }

    public Libro() {

    }

    public Libro(Object o, List<Autor> autoresEntidad) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

}
