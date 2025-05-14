package com.example.SistemaGestionBibliotecaSpring.exceptions;

import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("Prestamo no encontrado para el id: " + id);
    }

    public PrestamoNoEncontradoException(Libro libro) {
        super("Prestamo no encontrado para el libro de id: " + libro.getId());
    }

    public PrestamoNoEncontradoException(Usuario usuario) {
        super("Prestamo no encontrado para el usuario de id: " + usuario.getId());
    }
}
