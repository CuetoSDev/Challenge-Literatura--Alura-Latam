package com.example.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        LocalDate nacimiento,
        @JsonAlias("death_year")
        LocalDate muerte
        ) {
}
