package com.cninfotech.template.model;

import java.util.List;

public class Organizacion {
    private String id;
    private String titulo;
    private List<Usuario> miembros;
    private String ubicacion;

    public Organizacion(){}

    public Organizacion(String id,  List<Usuario> miembros, String titulo, String ubicacion)  {
        this.id = id;
        this.titulo = titulo;
        this.miembros = miembros;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Usuario> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Usuario> miembros) {
        this.miembros = miembros;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}