package com.android.rocknroll;


public class Publicaciones {

    private String fecha_recital;
    private String lugar;
    private int capacidad;
    private String id;
    private String nombre;

    public String getFecha_recital() {
        return fecha_recital;
    }

    public void setFecha_recital(String fecha_recital) {
        this.fecha_recital = fecha_recital;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
}