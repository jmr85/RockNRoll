package com.android.rocknroll;

public class InsertarPublicacionesResponse {


    private int id;
    private Publicaciones evento;
    private String idUsuario;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

