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

    @GetMapping
    public ResponseEntity<List<Prestamo>> obtenerTodos() {
        return ResponseEntity.status(200).body(prestamoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorId(id));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/por-libro")
    public ResponseEntity<Prestamo> buscarPorLibro(@RequestBody Libro libro) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorLibro(libro));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/por-usuario")
    public ResponseEntity<Prestamo> buscarPorUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(200).body(prestamoService.buscarPorUsuario(usuario));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        return ResponseEntity.status(200).body(prestamoService.guardar(prestamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(200).body(prestamoService.actualizar(id, prestamo));
        } catch (PrestamoNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

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
