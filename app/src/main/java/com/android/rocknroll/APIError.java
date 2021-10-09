package com.android.rocknroll;

public class APIError {
    private String nombre;
    private String mail;
    private String clave;
    private String mensaje;

    public String getMail() {
        return mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
