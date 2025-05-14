package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PrestamoRepositoryTest {
    private PrestamoRepository prestamoRepository;
    private Prestamo prestamo1;
    private Prestamo prestamo2;
    private Libro libro1;
    private Libro libro2;
    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setup() {
        prestamoRepository = new PrestamoRepositoryImpl();
        libro1 = new Libro(null, "9788420471839", "Cien Años De Soledad", "Gabriel García Márquez", LibroEstado.DISPONIBLE);
        libro2 = new Libro(null, "9788494220579", "Drácula", "Bram Stoker", LibroEstado.DISPONIBLE);
        usuario1 = new Usuario(null, "Vicente", "vicente@gmail.com", EstadoUsuario.HABILITADO);
        usuario2 = new Usuario(null, "Tadeo", "tadeo@gmail.com", EstadoUsuario.HABILITADO);
        prestamo1 = new Prestamo(null, libro1, usuario1, LocalDate.now(), LocalDate.now());
        prestamo2 = new Prestamo(null, libro2, usuario2, LocalDate.now(), LocalDate.now());
    }

    @Test
    void testSave() {
        Prestamo prestamoGuardado1 = prestamoRepository.save(prestamo1);

        Assertions.assertEquals(prestamoGuardado1, prestamo1);
        Assertions.assertEquals(1L, prestamoGuardado1.getId());

        prestamo2.setId(1L);
        Prestamo prestamoGuardado2 = prestamoRepository.save(prestamo2);
        List<Prestamo> prestamos = prestamoRepository.findAll();

        Assertions.assertEquals(prestamoGuardado2, prestamo2);
        Assertions.assertEquals(1L, prestamoGuardado2.getId());
        Assertions.assertEquals(1, prestamos.size());

    }

    @Test
    void testFindById() {
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo1);
        Optional<Prestamo> prestamoBuscado1 = prestamoRepository.findById(1L);

        Assertions.assertEquals(prestamoGuardado, prestamoBuscado1.get());
        Assertions.assertEquals("Cien Años De Soledad", prestamoBuscado1.get().getLibro().getTitulo());

        Optional<Prestamo> prestamoBuscado2 = prestamoRepository.findById(2L);

        Assertions.assertTrue(prestamoBuscado2.isEmpty());
    }

    @Test
    void testFindByLibro() {
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo1);
        Optional<Prestamo> prestamoBuscado1 = prestamoRepository.findByLibro(libro1);

        Assertions.assertEquals(prestamoGuardado, prestamoBuscado1.get());
        Assertions.assertEquals("Cien Años De Soledad", prestamoBuscado1.get().getLibro().getTitulo());

        Optional<Prestamo> prestamoBuscado2 = prestamoRepository.findByLibro(libro2);

        Assertions.assertTrue(prestamoBuscado2.isEmpty());
    }

    @Test
    void testFindByUsuario() {
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo1);
        Optional<Prestamo> prestamoBuscado1 = prestamoRepository.findByUsuario(usuario1);

        Assertions.assertEquals(prestamoGuardado, prestamoBuscado1.get());
        Assertions.assertEquals("Vicente", prestamoBuscado1.get().getUsuario().getNombre());

        Optional<Prestamo> prestamoBuscado2 = prestamoRepository.findByUsuario(usuario2);

        Assertions.assertTrue(prestamoBuscado2.isEmpty());
    }

    @Test
    void testFindAll() {
        prestamoRepository.save(prestamo1);
        prestamoRepository.save(prestamo2);
        List<Prestamo> prestamos = prestamoRepository.findAll();

        Assertions.assertEquals(2, prestamos.size());
    }

    @Test
    void testDeleteById() {
        prestamoRepository.save(prestamo1);
        prestamoRepository.save(prestamo2);
        prestamoRepository.deleteById(1L);
        List<Prestamo> prestamos = prestamoRepository.findAll();

        Assertions.assertEquals(1, prestamos.size());

        Optional<Prestamo> prestamoBuscado = prestamoRepository.findById(1L);

        Assertions.assertTrue(prestamoBuscado.isEmpty());
    }

    @Test
    void testExistsById() {
        prestamoRepository.save(prestamo1);
        boolean existe1 = prestamoRepository.existsById(1L);
        boolean existe2 = prestamoRepository.existsById(2L);

        Assertions.assertTrue(existe1);
        Assertions.assertFalse(existe2);
    }
}
