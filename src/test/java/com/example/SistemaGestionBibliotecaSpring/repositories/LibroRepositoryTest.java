package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class LibroRepositoryTest {
    private LibroRepository libroRepository;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setup() {
        libroRepository = new LibroRepositoryImpl();
        libro1 = new Libro(null, "9788420471839", "Cien Años De Soledad", "Gabriel García Márquez", LibroEstado.DISPONIBLE);
        libro2 = new Libro(null, "9788494220579", "Drácula", "Bram Stoker", LibroEstado.DISPONIBLE);
    }

    @Test
    void testSave() {
        Libro libroGuardado1 = libroRepository.save(libro1);

        Assertions.assertEquals(libroGuardado1, libro1);
        Assertions.assertEquals(1L, libroGuardado1.getId());

        libro2.setId(1L);
        Libro libroGuardado2 = libroRepository.save(libro2);
        List<Libro> libros = libroRepository.findAll();

        Assertions.assertEquals(libroGuardado2, libro2);
        Assertions.assertEquals(1L, libroGuardado2.getId());
        Assertions.assertEquals(1, libros.size());
    }

    @Test
    void testFindById() {
        Libro libroGuardado = libroRepository.save(libro1);
        Optional<Libro> libroBuscado1 = libroRepository.findById(1L);

        Assertions.assertEquals(libroGuardado, libroBuscado1.get());
        Assertions.assertEquals("Cien Años De Soledad", libroBuscado1.get().getTitulo());

        Optional<Libro> libroBuscado2 = libroRepository.findById(2L);

        Assertions.assertTrue(libroBuscado2.isEmpty());
    }

    @Test
    void testFindByIsbn() {
        Libro libroGuardado = libroRepository.save(libro1);
        Optional<Libro> libroBuscado1 = libroRepository.findByIsbn("9788420471839");

        Assertions.assertEquals(libroGuardado, libroBuscado1.get());
        Assertions.assertEquals("Cien Años De Soledad", libroBuscado1.get().getTitulo());

        Optional<Libro> libroBuscado2 = libroRepository.findByIsbn("9788494220579");

        Assertions.assertTrue(libroBuscado2.isEmpty());
    }

    @Test
    void testFindAll() {
        libroRepository.save(libro1);
        libroRepository.save(libro2);
        List<Libro> libros = libroRepository.findAll();

        Assertions.assertEquals(2, libros.size());
    }

    @Test
    void testDeleteById() {
        libroRepository.save(libro1);
        libroRepository.save(libro2);
        libroRepository.deleteById(1L);
        List<Libro> libros = libroRepository.findAll();

        Assertions.assertEquals(1, libros.size());

        Optional<Libro> libroBuscado = libroRepository.findById(1L);

        Assertions.assertTrue(libroBuscado.isEmpty());
    }

    @Test
    void testExistsById() {
        libroRepository.save(libro1);
        boolean existe1 = libroRepository.existsById(1L);
        boolean existe2 = libroRepository.existsById(2L);

        Assertions.assertTrue(existe1);
        Assertions.assertFalse(existe2);
    }
}
