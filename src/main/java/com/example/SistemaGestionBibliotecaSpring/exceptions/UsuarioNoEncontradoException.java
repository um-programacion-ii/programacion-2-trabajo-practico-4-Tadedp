package com.example.SistemaGestionBibliotecaSpring.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario no encontrado para el id: " + id);
    }

    public UsuarioNoEncontradoException(String email) {
        super("Usuario no encontrado para el email: " + email);
    }
}
