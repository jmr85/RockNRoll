package com.android.rocknroll;

public class InsertarPublicacionRequest {

    private Publicaciones evento;
    private String idUsuario;
    private int id;
    private int Capacidad;

    public Publicaciones getEvento() {
        return evento;
    }

    public void setEvento(Publicaciones evento) {
        this.evento = evento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publicaciones getPublicaciones() {
        return evento;
    }

    public void setPublicaciones(Publicaciones publicaciones) {
        this.evento = publicaciones;
    }

    public String getIdusuario() {
        return idUsuario;
    }

    public void setIdusuario(String idusuario) {
        this.idUsuario = idusuario;
    }
}


