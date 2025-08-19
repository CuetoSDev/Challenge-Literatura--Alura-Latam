package com.example.literatura.repository;

import com.example.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores vivos en un año específico
    List<Autor> findByFechaNacimientoBeforeAndFechaMuerteAfter(int year1, int year2);

    // Buscar por nombre
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    Optional<Autor> findByNombre(String nombre);
}
