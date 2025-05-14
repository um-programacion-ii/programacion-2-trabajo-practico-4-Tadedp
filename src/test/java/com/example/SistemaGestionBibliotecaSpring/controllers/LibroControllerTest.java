package com.example.SistemaGestionBibliotecaSpring.controllers;

import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;
import com.example.SistemaGestionBibliotecaSpring.exceptions.LibroNoEncontradoException;
import com.example.SistemaGestionBibliotecaSpring.models.Libro;
import com.example.SistemaGestionBibliotecaSpring.services.LibroService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {
    Libro libro1;
    Libro libro2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        libro1 = new Libro(null, "9788420471839", "Cien Años De Soledad", "Gabriel García Márquez", LibroEstado.DISPONIBLE);
        libro2 = new Libro(null, "9788494220579", "Drácula", "Bram Stoker", LibroEstado.DISPONIBLE);
    }

    @Test
    void testGetLibros() throws Exception{
        libro1.setId(1L);
        libro2.setId(2L);
        List<Libro> libros = List.of(libro1, libro2);

        when(libroService.obtenerTodos()).thenReturn(libros);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$[0].autor").value("Gabriel García Márquez"))
                .andExpect(jsonPath("$.size()").value(2));
        verify(libroService).obtenerTodos();
    }

    @Test
    void testGetLibroPorId() throws Exception{
        when(libroService.buscarPorId(1L)).thenReturn(libro1);

        mockMvc.perform(get("/api/libros/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.autor").value("Gabriel García Márquez"));
        verify(libroService).buscarPorId(1L);

        when(libroService.buscarPorId(2L)).thenThrow(LibroNoEncontradoException.class);

        mockMvc.perform(get("/api/libros/2"))
                .andExpect(status().is(404));
        verify(libroService).buscarPorId(2L);
    }

    @Test
    void testGetLibroPorIsbn() throws Exception{
        when(libroService.buscarPorIsbn("9788420471839")).thenReturn(libro1);

        mockMvc.perform(get("/api/libros/por-isbn/9788420471839"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.autor").value("Gabriel García Márquez"));
        verify(libroService).buscarPorIsbn("9788420471839");

        when(libroService.buscarPorIsbn("9788494220579")).thenThrow(LibroNoEncontradoException.class);

        mockMvc.perform(get("/api/libros/por-isbn/9788494220579"))
                .andExpect(status().is(404));
        verify(libroService).buscarPorIsbn("9788494220579");
    }

    @Test
    void testPostLibro() throws Exception{
        when(libroService.guardar(any(Libro.class))).thenReturn(libro1);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.autor").value("Gabriel García Márquez"));
        verify(libroService).guardar(any(Libro.class));
    }

    @Test
    void testPutLibro() throws Exception{
        when(libroService.actualizar(anyLong(), any(Libro.class))).thenReturn(libro1);

        mockMvc.perform(put("/api/libros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.titulo").value("Cien Años De Soledad"))
                .andExpect(jsonPath("$.autor").value("Gabriel García Márquez"));
        verify(libroService).actualizar(anyLong(), any(Libro.class));
    }

    @Test
    void testDeleteLibro() throws Exception{
        doNothing().when(libroService).eliminar(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().is(204));
        verify(libroService).eliminar(1L);
    }
}
