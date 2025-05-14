package com.example.SistemaGestionBibliotecaSpring.models;

import com.example.SistemaGestionBibliotecaSpring.enums.EstadoUsuario;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estado;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, EstadoUsuario estado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
}
