package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;

import java.util.List;

public interface PrestamoService {
    Prestamo buscarPorId(Long id);
    Prestamo buscarPorLibro(Libro libro);
    Prestamo buscarPorUsuario(Usuario usuario);
    List<Prestamo> obtenerTodos();
    Prestamo guardar(Prestamo prestamo);
    void eliminar(Long id);
    Prestamo actualizar(Long id, Prestamo prestamo);
}
