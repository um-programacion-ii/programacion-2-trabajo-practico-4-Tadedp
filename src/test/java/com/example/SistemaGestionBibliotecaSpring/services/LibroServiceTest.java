package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.repositories.LibroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {
    Libro libro1;
    Libro libro2;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @BeforeEach
    void setup() {
        libro1 = new Libro(null, "9788420471839", "Cien Años De Soledad", "Gabriel García Márquez", LibroEstado.DISPONIBLE);
        libro2 = new Libro(null, "9788494220579", "Drácula", "Bram Stoker", LibroEstado.DISPONIBLE);
    }

    @Test
    void testBuscarPorId() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro1));

        Libro libroBuscado1 = libroService.buscarPorId(1L);

        Assertions.assertEquals(libro1, libroBuscado1);
        Assertions.assertEquals("Cien Años De Soledad", libroBuscado1.getTitulo());
        verify(libroRepository).findById(1L);

        when(libroRepository.findById(2L)).thenReturn(Optional.empty());
        LibroNoEncontradoException e1 = Assertions.assertThrows(LibroNoEncontradoException.class, () -> {
            Libro libroBuscado2 = libroService.buscarPorId(2L);
        });

        Assertions.assertEquals("Libro no encontrado para el id: 2", e1.getMessage());
        verify(libroRepository).findById(2L);
    }

    @Test
    void testBuscarPorIsbn() {
        when(libroRepository.findByIsbn("9788420471839")).thenReturn(Optional.of(libro1));

        Libro libroBuscado1 = libroService.buscarPorIsbn("9788420471839");

        Assertions.assertEquals(libro1, libroBuscado1);
        Assertions.assertEquals("Cien Años De Soledad", libroBuscado1.getTitulo());
        verify(libroRepository).findByIsbn("9788420471839");

        when(libroRepository.findByIsbn("9788494220579")).thenReturn(Optional.empty());
        LibroNoEncontradoException e1 = Assertions.assertThrows(LibroNoEncontradoException.class, () -> {
            Libro libroBuscado2 = libroService.buscarPorIsbn("9788494220579");
        });

        Assertions.assertEquals("Libro no encontrado para el isbn: 9788494220579", e1.getMessage());
        verify(libroRepository).findByIsbn("9788494220579");
    }

    @Test
    void testObtenerTodos() {
        List<Libro> mockLibros = List.of(libro1, libro2);
        when(libroRepository.findAll()).thenReturn(mockLibros);

        List<Libro> libros = libroService.obtenerTodos();

        Assertions.assertEquals(mockLibros, libros);
        Assertions.assertEquals(2, libros.size());
        verify(libroRepository).findAll();
    }

    @Test
    void testGuardar() {
        libro1.setId(1L);
        when(libroRepository.save(libro1)).thenReturn(libro1);

        Libro libroGuardado1 = libroService.guardar(libro1);

        Assertions.assertEquals(libroGuardado1, libro1);
        Assertions.assertEquals(1L, libroGuardado1.getId());
        verify(libroRepository).save(libro1);
    }

    @Test
    void testEliminar() {
        when(libroRepository.existsById(1L)).thenReturn(true);
        doNothing().when(libroRepository).deleteById(1L);

        libroService.eliminar(1L);


        verify(libroRepository).existsById(1L);
        verify(libroRepository).deleteById(1L);

        when(libroRepository.existsById(2L)).thenReturn(false);

        LibroNoEncontradoException e1 = Assertions.assertThrows(LibroNoEncontradoException.class, () -> {
            libroService.eliminar(2L);
        });

        Assertions.assertEquals("Libro no encontrado para el id: 2", e1.getMessage());
        verify(libroRepository).existsById(2L);
    }

    @Test
    void testActualizar() {
        when(libroRepository.existsById(1L)).thenReturn(true);
        when(libroRepository.save(libro1)).thenReturn(libro1);

        Libro libroActualizado = libroService.actualizar(1L, libro1);

        Assertions.assertEquals(libro1, libroActualizado);
        Assertions.assertEquals(1L, libroActualizado.getId());
        verify(libroRepository).existsById(1L);
        verify(libroRepository).save(libro1);

        when(libroRepository.existsById(2L)).thenReturn(false);

        LibroNoEncontradoException e1 = Assertions.assertThrows(LibroNoEncontradoException.class, () -> {
            libroService.eliminar(2L);
        });

        Assertions.assertEquals("Libro no encontrado para el id: 2", e1.getMessage());
        verify(libroRepository).existsById(2L);
    }
}
