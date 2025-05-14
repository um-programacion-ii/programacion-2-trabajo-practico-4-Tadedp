package com.example.SistemaGestionBibliotecaSpring.models;

import com.example.SistemaGestionBibliotecaSpring.enums.LibroEstado;

public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private LibroEstado estado;

    public Libro() {
    }

    public Libro(Long id, String isbn, String titulo, String autor, LibroEstado estado) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LibroEstado getEstado() {
        return estado;
    }

    public void setEstado(LibroEstado estado) {
        this.estado = estado;
    }
}
