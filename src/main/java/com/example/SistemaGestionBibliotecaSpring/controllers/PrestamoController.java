package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.services.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /*
    GET /api/prestamos
    ---
    Obtener todos los prestamos
    ---
    200 OK - Devuelve lista de prestamos
     */
    @GetMapping
    public ResponseEntity<List<Prestamo>> obtenerTodos() {
        return ResponseEntity.status(200).body(prestamoService.obtenerTodos());
    }

    /*
    GET /api/prestamos/{id}
    ---
    Buscar préstamo por id
    ---
    200 OK - Devuelve el préstamo correspondiente al id
    404 Not Found - Sin contenido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorId(id));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    POST /api/prestamos/por-libro
    ---
    Buscar préstamo por libro
    ---
    Recibe libro buscado en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el préstamo correspondiente al libro
    404 Not Found - Sin contenido
     */
    @PostMapping("/por-libro")
    public ResponseEntity<Prestamo> buscarPorLibro(@RequestBody Libro libro) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorLibro(libro));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    POST /api/prestamos/por-usuario
    ---
    Buscar préstamo por usuario
    ---
    Recibe usuario buscado en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el préstamo correspondiente al usuario
    404 Not Found - Sin contenido
     */
    @PostMapping("/por-usuario")
    public ResponseEntity<Prestamo> buscarPorUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorUsuario(usuario));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    POST /api/prestamos
    ---
    Crear préstamo
    ---
    Recibe préstamo a crear en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el préstamo creado
     */
    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        return ResponseEntity.status(200).body(prestamoService.guardar(prestamo));
    }

    /*
    PUT /api/prestamos/{id}
    ---
    Modificar préstamo por id
    ---
    Recibe préstamo modificado en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el préstamo modificado
    404 Not Found - Sin contenido
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(200).body(prestamoService.actualizar(id, prestamo));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    DELETE /api/prestamos/{id}
    ---
    Eliminar préstamo por id
    ---
    204 No Content - Sin contenido
    404 Not Found - Sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            prestamoService.eliminar(id);
            return ResponseEntity.status(204).body(null);
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
