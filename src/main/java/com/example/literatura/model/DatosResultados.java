package com.example.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultados(
        @JsonAlias("count")
        int cantidad,
        @JsonAlias("results")
        List<DatosLIbro> libro
) {}