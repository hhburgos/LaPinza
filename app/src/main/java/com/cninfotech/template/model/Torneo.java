package com.cninfotech.template.model;

import java.util.Date;
import java.util.List;

public class Torneo {
    private String id;
    private String nombre;
    private int formato;
    private List<Usuario> participantes;
    private List<Usuario> jueces;
    private List<Usuario> speakers;
    private String lugarEvento;
    private String fechaEvento;
    private String idOrganizacion;

    public Torneo(String nombre, int formato, List<Usuario> participantes, List<Usuario> jueces, List<Usuario> speakers, String lugarEvento, String fechaEvento, String idOrganizacion) {
        this.nombre = nombre;
        this.formato = formato;
        this.participantes = participantes;
        this.jueces = jueces;
        this.speakers = speakers;
        this.lugarEvento = lugarEvento;
        this.fechaEvento = fechaEvento;
        this.idOrganizacion = idOrganizacion;
    }

    public Torneo(String id, String nombre, int formato, List<Usuario> participantes, List<Usuario> jueces, List<Usuario> speakers, String lugarEvento, String fechaEvento, String idOrganizacion) {
        this.id = id;
        this.nombre = nombre;
        this.formato = formato;
        this.participantes = participantes;
        this.jueces = jueces;
        this.speakers = speakers;
        this.lugarEvento = lugarEvento;
        this.fechaEvento = fechaEvento;
        this.idOrganizacion = idOrganizacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public List<Usuario> getJueces() {
        return jueces;
    }

    public void setJueces(List<Usuario> jueces) {
        this.jueces = jueces;
    }

    public List<Usuario> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Usuario> speakers) {
        this.speakers = speakers;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(String idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }
}
