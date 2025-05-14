package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.repositories.PrestamoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceTest {
    Libro libro1;
    Libro libro2;
    Usuario usuario1;
    Usuario usuario2;
    Prestamo prestamo1;
    Prestamo prestamo2;

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @BeforeEach
    void setup() {
        libro1 = new Libro(null, "9788420471839", "Cien Años De Soledad", "Gabriel García Márquez", LibroEstado.DISPONIBLE);
        libro2 = new Libro(null, "9788494220579", "Drácula", "Bram Stoker", LibroEstado.DISPONIBLE);
        usuario1 = new Usuario(null, "Vicente", "vicente@gmail.com", EstadoUsuario.HABILITADO);
        usuario2 = new Usuario(null, "Tadeo", "tadeo@gmail.com", EstadoUsuario.HABILITADO);
        prestamo1 = new Prestamo(null, libro1, usuario1, LocalDate.now(), LocalDate.now());
        prestamo2 = new Prestamo(null, libro2, usuario2, LocalDate.now(), LocalDate.now());
    }

    @Test
    void testBuscarPorId() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo1));

        Prestamo prestamoBuscado1 = prestamoService.buscarPorId(1L);

        Assertions.assertEquals(prestamo1, prestamoBuscado1);
        Assertions.assertEquals("Cien Años De Soledad", prestamoBuscado1.getLibro().getTitulo());
        verify(prestamoRepository).findById(1L);

        when(prestamoRepository.findById(2L)).thenReturn(Optional.empty());
        PrestamoNoEncontradoException e1 = Assertions.assertThrows(PrestamoNoEncontradoException.class, () -> {
            Prestamo prestamoBuscado2 = prestamoService.buscarPorId(2L);
        });

        Assertions.assertEquals("Prestamo no encontrado para el id: 2", e1.getMessage());
        verify(prestamoRepository).findById(2L);
    }

    @Test
    void testBuscarPorLibro() {
        when(prestamoRepository.findByLibro(libro1)).thenReturn(Optional.of(prestamo1));

        Prestamo prestamoBuscado1 = prestamoService.buscarPorLibro(libro1);

        Assertions.assertEquals(prestamo1, prestamoBuscado1);
        Assertions.assertEquals("Cien Años De Soledad", prestamoBuscado1.getLibro().getTitulo());
        verify(prestamoRepository).findByLibro(libro1);

        libro2.setId(2L);
        when(prestamoRepository.findByLibro(libro2)).thenReturn(Optional.empty());
        PrestamoNoEncontradoException e1 = Assertions.assertThrows(PrestamoNoEncontradoException.class, () -> {
            Prestamo prestamoBuscado2 = prestamoService.buscarPorLibro(libro2);
        });

        Assertions.assertEquals("Prestamo no encontrado para el libro de id: 2", e1.getMessage());
        verify(prestamoRepository).findByLibro(libro2);
    }

    @Test
    void testBuscarPorUsuario() {
        when(prestamoRepository.findByUsuario(usuario1)).thenReturn(Optional.of(prestamo1));

        Prestamo prestamoBuscado1 = prestamoService.buscarPorUsuario(usuario1);

        Assertions.assertEquals(prestamo1, prestamoBuscado1);
        Assertions.assertEquals("Vicente", prestamoBuscado1.getUsuario().getNombre());
        verify(prestamoRepository).findByUsuario(usuario1);

        usuario2.setId(2L);
        when(prestamoRepository.findByUsuario(usuario2)).thenReturn(Optional.empty());
        PrestamoNoEncontradoException e1 = Assertions.assertThrows(PrestamoNoEncontradoException.class, () -> {
            Prestamo prestamoBuscado2 = prestamoService.buscarPorUsuario(usuario2);
        });

        Assertions.assertEquals("Prestamo no encontrado para el usuario de id: 2", e1.getMessage());
        verify(prestamoRepository).findByUsuario(usuario2);
    }

    @Test
    void testObtenerTodos() {
        List<Prestamo> mockPrestamos = List.of(prestamo1, prestamo2);
        when(prestamoRepository.findAll()).thenReturn(mockPrestamos);

        List<Prestamo> prestamos = prestamoService.obtenerTodos();

        Assertions.assertEquals(mockPrestamos, prestamos);
        Assertions.assertEquals(2, prestamos.size());
        verify(prestamoRepository).findAll();
    }

    @Test
    void testGuardar() {
        prestamo1.setId(1L);
        when(prestamoRepository.save(prestamo1)).thenReturn(prestamo1);

        Prestamo prestamoGuardado1 = prestamoService.guardar(prestamo1);

        Assertions.assertEquals(prestamoGuardado1, prestamo1);
        Assertions.assertEquals(1L, prestamoGuardado1.getId());
        verify(prestamoRepository).save(prestamo1);
    }

    @Test
    void testEliminar() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(prestamoRepository).deleteById(1L);

        prestamoService.eliminar(1L);


        verify(prestamoRepository).existsById(1L);
        verify(prestamoRepository).deleteById(1L);

        when(prestamoRepository.existsById(2L)).thenReturn(false);

        PrestamoNoEncontradoException e1 = Assertions.assertThrows(PrestamoNoEncontradoException.class, () -> {
            prestamoService.eliminar(2L);
        });

        Assertions.assertEquals("Prestamo no encontrado para el id: 2", e1.getMessage());
        verify(prestamoRepository).existsById(2L);
    }

    @Test
    void testActualizar() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        when(prestamoRepository.save(prestamo1)).thenReturn(prestamo1);

        Prestamo prestamoActualizado = prestamoService.actualizar(1L, prestamo1);

        Assertions.assertEquals(prestamo1, prestamoActualizado);
        Assertions.assertEquals(1L, prestamoActualizado.getId());
        verify(prestamoRepository).existsById(1L);
        verify(prestamoRepository).save(prestamo1);

        when(prestamoRepository.existsById(2L)).thenReturn(false);

        PrestamoNoEncontradoException e1 = Assertions.assertThrows(PrestamoNoEncontradoException.class, () -> {
            prestamoService.eliminar(2L);
        });

        Assertions.assertEquals("Prestamo no encontrado para el id: 2", e1.getMessage());
        verify(prestamoRepository).existsById(2L);
    }
}
