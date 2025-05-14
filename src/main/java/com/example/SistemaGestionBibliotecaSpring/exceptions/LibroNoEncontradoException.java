package com.example.SistemaGestionBibliotecaSpring.exceptions;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(Long id) {
        super("Libro no encontrado para el id: " + id);
    }

    public LibroNoEncontradoException(String isbn) {
        super("Libro no encontrado para el isbn: " + isbn);
    }
}
