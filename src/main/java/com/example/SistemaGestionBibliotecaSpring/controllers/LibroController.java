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

    /*
    GET /api/libros
    ---
    Obtener todos los libros
    ---
    200 OK - Devuelve lista de libros
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return ResponseEntity.status(200).body(libroService.obtenerTodos());
    }

    /*
    GET /api/libros/{id}
    ---
    Buscar libro por id
    ---
    200 OK - Devuelve el libro correspondiente al id
    404 Not Found - Sin contenido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(libroService.buscarPorId(id));
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    GET /api/libros/por-isbn/{isbn}
    ---
    Buscar libro por isbn
    ---
    200 OK - Devuelve el libro correspondiente al isbn
    404 Not Found - Sin contenido
     */
    @GetMapping("/por-isbn/{isbn}")
    public ResponseEntity<Libro> buscarPorIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.status(200).body(libroService.buscarPorIsbn(isbn));
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    POST /api/libros
    ---
    Crear libro
    ---
    Recibe libro a crear en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el libro creado
     */
    @PostMapping
    public ResponseEntity<Libro> crear(@RequestBody Libro libro) {
        return ResponseEntity.status(200).body(libroService.guardar(libro));
    }

    /*
    PUT /api/libros/{id}
    ---
    Modificar libro por id
    ---
    Recibe libro modificado en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el libro modificado
    404 Not Found - Sin contenido
     */
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            return ResponseEntity.status(200).body(libroService.actualizar(id, libro));
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    DELETE /api/libros/{id}
    ---
    Eliminar libro por id
    ---
    204 No Content - Sin contenido
    404 Not Found - Sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.status(204).body(null);
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
