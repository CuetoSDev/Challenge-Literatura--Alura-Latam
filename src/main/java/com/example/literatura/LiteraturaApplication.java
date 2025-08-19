package com.example.literatura;

import com.example.literatura.model.DatosLIbro;
import com.example.literatura.model.DatosResultados;
import com.example.literatura.repository.AutorRepository;
import com.example.literatura.service.ConsumoApi;
import com.example.literatura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
    public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.mostrarMenu();

    }
}
