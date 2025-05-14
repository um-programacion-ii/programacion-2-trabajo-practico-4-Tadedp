package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;
import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.exceptions.PrestamoNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.models.Prestamo;
import com.example.SistemaGestionBibliotecaSpring.models.Usuario;
import com.example.SistemaGestionBibliotecaSpring.services.PrestamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrestamoController.class)
public class PrestamoControllerTest {
    Libro libro1;
    Libro libro2;
    Usuario usuario1;
    Usuario usuario2;
    Prestamo prestamo1;
    Prestamo prestamo2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testGetPrestamos() throws Exception{
        prestamo1.setId(1L);
        prestamo2.setId(2L);
        List<Prestamo> prestamos = List.of(prestamo1, prestamo2);

        when(prestamoService.obtenerTodos()).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$[0].usuario.nombre").value("Vicente"))
                .andExpect(jsonPath("$.size()").value(2));
        verify(prestamoService).obtenerTodos();
    }

    @Test
    void testGetPrestamoPorId() throws Exception{
        when(prestamoService.buscarPorId(1L)).thenReturn(prestamo1);

        mockMvc.perform(get("/api/prestamos/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.usuario.nombre").value("Vicente"));
        verify(prestamoService).buscarPorId(1L);

        when(prestamoService.buscarPorId(2L)).thenThrow(PrestamoNoEncontradoException.class);

        mockMvc.perform(get("/api/prestamos/2"))
                .andExpect(status().is(404));
        verify(prestamoService).buscarPorId(2L);
    }

    @Test
    void testGetPrestamoPorLibro() throws Exception{
        when(prestamoService.buscarPorLibro(any(Libro.class))).thenReturn(prestamo1);

        mockMvc.perform(post("/api/prestamos/por-libro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.usuario.nombre").value("Vicente"));

        when(prestamoService.buscarPorLibro(any(Libro.class))).thenThrow(PrestamoNoEncontradoException.class);

        mockMvc.perform(post("/api/prestamos/por-libro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro2)))
                .andExpect(status().is(404));
        verify(prestamoService, times(2)).buscarPorLibro(any(Libro.class));
    }

    @Test
    void testGetPrestamoPorUsuario() throws Exception {
        when(prestamoService.buscarPorUsuario(any(Usuario.class))).thenReturn(prestamo1);

        mockMvc.perform(post("/api/prestamos/por-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.usuario.nombre").value("Vicente"));

        when(prestamoService.buscarPorUsuario(any(Usuario.class))).thenThrow(PrestamoNoEncontradoException.class);

        mockMvc.perform(post("/api/prestamos/por-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario2)))
                .andExpect(status().is(404));
        verify(prestamoService, times(2)).buscarPorUsuario(any(Usuario.class));
    }

    @Test
    void testPostPrestamo() throws Exception{
        when(prestamoService.guardar(any(Prestamo.class))).thenReturn(prestamo1);

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.usuario.nombre").value("Vicente"));
        verify(prestamoService).guardar(any(Prestamo.class));
    }

    @Test
    void testPutPrestamo() throws Exception{
        when(prestamoService.actualizar(anyLong(), any(Prestamo.class))).thenReturn(prestamo1);

        mockMvc.perform(put("/api/prestamos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.libro.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.usuario.nombre").value("Vicente"));
        verify(prestamoService).actualizar(anyLong(), any(Prestamo.class));
    }

    @Test
    void testDeletePrestamo() throws Exception{
        doNothing().when(prestamoService).eliminar(1L);

        mockMvc.perform(delete("/api/prestamos/1"))
                .andExpect(status().is(204));
        verify(prestamoService).eliminar(1L);
    }
}