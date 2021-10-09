package com.android.rocknroll;

public class VerReservas {
// listado de eventos reservados que se ve en el activity entradasReservadas. A su vez, pueden elimiarse las reservas.

    private Evento Evento;
    private String Fecha;
    private int Capacidad;
    private String Lugar;
    private String publicacion;
    private String idusuario;
    private String id;

    public com.android.rocknroll.Evento getEvento() {
        return Evento;
    }

    public void setEvento(com.android.rocknroll.Evento evento) {
        Evento = evento;
    }


    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


    public int getCapacidad() {        return Capacidad;    }

    public void setCapacidad(int capacidad) {        Capacidad = capacidad;    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }
}

