package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.services.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
}
