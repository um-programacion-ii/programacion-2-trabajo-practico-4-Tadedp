package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.exceptions.UsuarioNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    Usuario usuario1;
    Usuario usuario2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        usuario1 = new Usuario(null, "Vicente", "vicente@gmail.com", EstadoUsuario.HABILITADO);
        usuario2 = new Usuario(null, "Tadeo", "tadeo@gmail.com", EstadoUsuario.HABILITADO);
    }

    @Test
    void testGetUsuarios() throws Exception{
        usuario1.setId(1L);
        usuario2.setId(2L);
        List<Usuario> usuarios = List.of(usuario1, usuario2);

        when(usuarioService.obtenerTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Vicente"))
                .andExpect(jsonPath("$[0].email").value("vicente@gmail.com"))
                .andExpect(jsonPath("$.size()").value(2));
        verify(usuarioService).obtenerTodos();
    }

    @Test
    void testGetUsuarioPorId() throws Exception{
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario1);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.nombre").value("Vicente"))
                .andExpect(jsonPath("$.email").value("vicente@gmail.com"));
        verify(usuarioService).buscarPorId(1L);

        when(usuarioService.buscarPorId(2L)).thenThrow(UsuarioNoEncontradoException.class);

        mockMvc.perform(get("/api/usuarios/2"))
                .andExpect(status().is(404));
        verify(usuarioService).buscarPorId(2L);
    }

    @Test
    void testGetUsuarioPorEmail() throws Exception{
        when(usuarioService.buscarPorEmail("vicente@gmail.com")).thenReturn(usuario1);

        mockMvc.perform(get("/api/usuarios/por-email/vicente@gmail.com"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.nombre").value("Vicente"))
                .andExpect(jsonPath("$.email").value("vicente@gmail.com"));
        verify(usuarioService).buscarPorEmail("vicente@gmail.com");

        when(usuarioService.buscarPorEmail("tadeo@gmail.com")).thenThrow(UsuarioNoEncontradoException.class);

        mockMvc.perform(get("/api/usuarios/por-email/tadeo@gmail.com"))
                .andExpect(status().is(404));
        verify(usuarioService).buscarPorEmail("tadeo@gmail.com");
    }

    @Test
    void testPostUsuario() throws Exception{
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuario1);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.nombre").value("Vicente"))
                .andExpect(jsonPath("$.email").value("vicente@gmail.com"));
        verify(usuarioService).guardar(any(Usuario.class));
    }

    @Test
    void testPutUsuario() throws Exception{
        when(usuarioService.actualizar(anyLong(), any(Usuario.class))).thenReturn(usuario1);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.nombre").value("Vicente"))
                .andExpect(jsonPath("$.email").value("vicente@gmail.com"));
        verify(usuarioService).actualizar(anyLong(), any(Usuario.class));
    }

    @Test
    void testDeleteUsuario() throws Exception{
        doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().is(204));
        verify(usuarioService).eliminar(1L);
    }
}
