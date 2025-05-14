package com.example.SistemaGestionBibliotecaSpring.repositories;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryTest {
    private UsuarioRepository usuarioRepository;
    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setup() {
        usuarioRepository = new UsuarioRepositoryImpl();
        usuario1 = new Usuario(null, "Vicente", "vicente@gmail.com", EstadoUsuario.HABILITADO);
        usuario2 = new Usuario(null, "Tadeo", "tadeo@gmail.com", EstadoUsuario.HABILITADO);
    }

    @Test
    void testSave() {
        Usuario usuarioGuardado1 = usuarioRepository.save(usuario1);

        Assertions.assertEquals(usuarioGuardado1, usuario1);
        Assertions.assertEquals(1L, usuarioGuardado1.getId());

        usuario2.setId(1L);
        Usuario usuarioGuardado2 = usuarioRepository.save(usuario2);
        List<Usuario> usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(usuarioGuardado2, usuario2);
        Assertions.assertEquals(1L, usuarioGuardado2.getId());
        Assertions.assertEquals(1, usuarios.size());
    }

    @Test
    void testFindById() {
        Usuario usuarioGuardado = usuarioRepository.save(usuario1);
        Optional<Usuario> usuarioBuscado1 = usuarioRepository.findById(1L);

        Assertions.assertEquals(usuarioGuardado, usuarioBuscado1.get());
        Assertions.assertEquals("Vicente", usuarioBuscado1.get().getNombre());

        Optional<Usuario> usuarioBuscado2 = usuarioRepository.findById(2L);

        Assertions.assertTrue(usuarioBuscado2.isEmpty());
    }

    @Test
    void testFindByEmail() {
        Usuario usuarioGuardado = usuarioRepository.save(usuario1);
        Optional<Usuario> usuarioBuscado1 = usuarioRepository.findByEmail("vicente@gmail.com");

        Assertions.assertEquals(usuarioGuardado, usuarioBuscado1.get());
        Assertions.assertEquals("Vicente", usuarioBuscado1.get().getNombre());

        Optional<Usuario> usuarioBuscado2 = usuarioRepository.findByEmail("tadeo@gmail.com");

        Assertions.assertTrue(usuarioBuscado2.isEmpty());
    }

    @Test
    void testFindAll() {
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        List<Usuario> usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(2, usuarios.size());
    }

    @Test
    void testDeleteById() {
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        usuarioRepository.deleteById(1L);
        List<Usuario> usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(1, usuarios.size());

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(1L);

        Assertions.assertTrue(usuarioBuscado.isEmpty());
    }

    @Test
    void testExistsById() {
        usuarioRepository.save(usuario1);
        boolean existe1 = usuarioRepository.existsById(1L);
        boolean existe2 = usuarioRepository.existsById(2L);

        Assertions.assertTrue(existe1);
        Assertions.assertFalse(existe2);
    }
}
