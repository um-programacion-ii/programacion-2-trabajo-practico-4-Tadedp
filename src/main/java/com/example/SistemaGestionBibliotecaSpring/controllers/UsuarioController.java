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

    /*
    GET /api/usuarios
    ---
    Obtener todos los usuarios
    ---
    200 OK - Devuelve lista de usuarios
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.status(200).body(usuarioService.obtenerTodos());
    }

    /*
    GET /api/usuarios/{id}
    ---
    Buscar usuario por id
    ---
    200 OK - Devuelve el usuario correspondiente al id
    404 Not Found - Sin contenido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(usuarioService.buscarPorId(id));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    GET /api/usuarios/por-email/{email}
    ---
    Buscar usuario por email
    ---
    200 OK - Devuelve el usuario correspondiente al email
    404 Not Found - Sin contenido
     */
    @GetMapping("/por-email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(200).body(usuarioService.buscarPorEmail(email));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    POST /api/usuarios
    ---
    Crear usuario
    ---
    Recibe usuario a crear en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el usuario creado
     */
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(usuarioService.guardar(usuario));
    }

    /*
    PUT /api/usuarios/{id}
    ---
    Modificar usuario por id
    ---
    Recibe usuario modificado en el cuerpo de la solicitud
    ---
    200 OK - Devuelve el usuario modificado
    404 Not Found - Sin contenido
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(200).body(usuarioService.actualizar(id, usuario));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /*
    DELETE /api/usuarios/{id}
    ---
    Eliminar usuario por id
    ---
    204 No Content - Sin contenido
    404 Not Found - Sin contenido
     */
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
