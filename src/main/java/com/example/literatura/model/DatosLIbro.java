package com.example.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLIbro(
        @JsonAlias("title")
        String titulo,
        @JsonAlias("summaries")
        List<String> resumen, // <-- ahora es una lista
        @JsonAlias("languages")
        String lenguajes,
        @JsonAlias("authors")
        List<DatosAutor> autores

) {}