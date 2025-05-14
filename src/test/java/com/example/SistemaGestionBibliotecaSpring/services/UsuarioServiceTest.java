package com.example.SistemaGestionBibliotecaSpring.services;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.repositories.UsuarioRepository;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    Usuario usuario1;
    Usuario usuario2;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setup() {
        usuario1 = new Usuario(null, "Vicente", "vicente@gmail.com", EstadoUsuario.HABILITADO);
        usuario2 = new Usuario(null, "Tadeo", "tadeo@gmail.com", EstadoUsuario.HABILITADO);
    }

    @Test
    void testBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        Usuario usuarioBuscado1 = usuarioService.buscarPorId(1L);

        Assertions.assertEquals(usuario1, usuarioBuscado1);
        Assertions.assertEquals("Vicente", usuarioBuscado1.getNombre());
        verify(usuarioRepository).findById(1L);

        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());
        UsuarioNoEncontradoException e1 = Assertions.assertThrows(UsuarioNoEncontradoException.class, () -> {
            Usuario usuarioBuscado2 = usuarioService.buscarPorId(2L);
        });

        Assertions.assertEquals("Usuario no encontrado para el id: 2", e1.getMessage());
        verify(usuarioRepository).findById(2L);
    }

    @Test
    void testBuscarPorEmail() {
        when(usuarioRepository.findByEmail("vicente@gmail.com")).thenReturn(Optional.of(usuario1));

        Usuario usuarioBuscado1 = usuarioService.buscarPorEmail("vicente@gmail.com");

        Assertions.assertEquals(usuario1, usuarioBuscado1);
        Assertions.assertEquals("Vicente", usuarioBuscado1.getNombre());
        verify(usuarioRepository).findByEmail("vicente@gmail.com");

        when(usuarioRepository.findByEmail("tadeo@gmail.com")).thenReturn(Optional.empty());
        UsuarioNoEncontradoException e1 = Assertions.assertThrows(UsuarioNoEncontradoException.class, () -> {
            Usuario usuarioBuscado2 = usuarioService.buscarPorEmail("tadeo@gmail.com");
        });

        Assertions.assertEquals("Usuario no encontrado para el email: tadeo@gmail.com", e1.getMessage());
        verify(usuarioRepository).findByEmail("tadeo@gmail.com");
    }

    @Test
    void testObtenerTodos() {
        List<Usuario> mockUsuarios = List.of(usuario1, usuario2);
        when(usuarioRepository.findAll()).thenReturn(mockUsuarios);

        List<Usuario> usuarios = usuarioService.obtenerTodos();

        Assertions.assertEquals(mockUsuarios, usuarios);
        Assertions.assertEquals(2, usuarios.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testGuardar() {
        usuario1.setId(1L);
        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);

        Usuario usuarioGuardado1 = usuarioService.guardar(usuario1);

        Assertions.assertEquals(usuarioGuardado1, usuario1);
        Assertions.assertEquals(1L, usuarioGuardado1.getId());
        verify(usuarioRepository).save(usuario1);
    }

    @Test
    void testEliminar() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.eliminar(1L);


        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).deleteById(1L);

        when(usuarioRepository.existsById(2L)).thenReturn(false);

        UsuarioNoEncontradoException e1 = Assertions.assertThrows(UsuarioNoEncontradoException.class, () -> {
            usuarioService.eliminar(2L);
        });

        Assertions.assertEquals("Usuario no encontrado para el id: 2", e1.getMessage());
        verify(usuarioRepository).existsById(2L);
    }

    @Test
    void testActualizar() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);

        Usuario usuarioActualizado = usuarioService.actualizar(1L, usuario1);

        Assertions.assertEquals(usuario1, usuarioActualizado);
        Assertions.assertEquals(1L, usuarioActualizado.getId());
        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).save(usuario1);

        when(usuarioRepository.existsById(2L)).thenReturn(false);

        UsuarioNoEncontradoException e1 = Assertions.assertThrows(UsuarioNoEncontradoException.class, () -> {
            usuarioService.eliminar(2L);
        });

        Assertions.assertEquals("Usuario no encontrado para el id: 2", e1.getMessage());
        verify(usuarioRepository).existsById(2L);
    }
}
