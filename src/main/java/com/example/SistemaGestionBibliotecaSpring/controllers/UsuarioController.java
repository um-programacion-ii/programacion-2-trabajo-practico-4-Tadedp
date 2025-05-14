package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.status(200).body(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(usuarioService.buscarPorId(id));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/por-email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(200).body(usuarioService.buscarPorEmail(email));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(200).body(usuarioService.actualizar(id, usuario));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.status(204).body(null);
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
