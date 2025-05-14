package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long id);
    Usuario buscarPorEmail(String email);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}
