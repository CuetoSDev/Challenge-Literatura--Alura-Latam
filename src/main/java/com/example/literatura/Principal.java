package com.example.literatura;

import com.example.literatura.model.*;
import com.example.literatura.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Principal {
    private final String urlBase = "https://gutendex.com/books/?search=";

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private Object DatosLibro;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    private final String message = """
    Elija la opción a través de su número:
1 - buscar libro por título
2 - listar libros registrados
3 - listar autores registrados
4 - listar autores vivos en un determinado año
5 - listar libros por idioma
0 - salir

""";

    public void mostrarMenu() {
        Scanner teclado = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("0")) {
            System.out.println(message);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.println("Ingrese el nombre del libro:");
                    String nombre = teclado.nextLine().trim().replace(" ", "%20").toLowerCase();
                    buscarYGuardarLibro(nombre);
                }
                case "2" -> listarLibros();
                case "3" -> listarAutores();
                case "4" -> {
                    System.out.println("Ingrese el año:");
                    int anio = teclado.nextInt();
                    teclado.nextLine();
                    listarAutoresVivosEn(anio);
                }
                case "5" -> {
                    System.out.println("Ingrese el idioma (ej: en, es, fr):");
                    String idioma = teclado.nextLine().trim().toLowerCase();
                    listarLibrosPorIdioma(idioma);
                }
                case "0" -> System.out.println("Adiós!");
                default -> System.out.println("Ingrese una opción correcta.");
            }
        }
    }

    private void buscarYGuardarLibro(String nombre) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlBase + nombre))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            DatosResultados datosResultados = mapper.readValue(response.body(), DatosResultados.class);

            if (datosResultados.libro().isEmpty()) {
                System.out.println("No se encontró el libro.");
                return;
            }

            DatosLIbro datosLibro = datosResultados.libro().get(0);

            // Convertimos autores del record DatosAutor a entidades Autor
            List<Autor> autoresEntidad = new ArrayList<>();
            for (DatosAutor da : datosLibro.autores()) {
                Autor autor = autorRepository.findByNombre(da.nombre())
                        .orElseGet(() -> {
                            Autor nuevo = new Autor(da);
                            autorRepository.save(nuevo);
                            return nuevo;
                        });
                autoresEntidad.add(autor);
            }

            // Creamos Libro y lo guardamos usando tu constructor
            Libro libro = new Libro(DatosLibro, autoresEntidad);
            libroRepository.save(libro);

            System.out.println("Libro guardado: " + libro.getTitulo());

        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void listarLibros() {
        libroRepository.findAll().forEach(libro ->
                System.out.println("📖 " + libro.getTitulo() + " (" + libro.getLenguajes() + ")")
        );
    }

    private void listarAutores() {
        autorRepository.findAll().forEach(autor ->
                System.out.println("👤 " + autor.getNombre() +
                        " (" + autor.getNacimiento() + " - " + autor.getMuerte() + ")")
        );
    }

    private void listarAutoresVivosEn(int anio) {
        autorRepository.findAll().stream()
                .filter(a -> false)
                .forEach(a -> System.out.println("👤 " + a.getNombre()));
    }

    private void listarLibrosPorIdioma(String idioma) {
        libroRepository.findByLenguajes(idioma).forEach(libro ->
                System.out.println("📖 " + libro.getTitulo())
        );
    }
}
