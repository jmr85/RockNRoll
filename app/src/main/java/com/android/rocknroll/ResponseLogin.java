package com.android.rocknroll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResponseLogin  implements Serializable {


    @SerializedName("nombre")
    @Expose
    private  String nombre;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("idusuario")
    @Expose
    private int idusuario;


    public String getNombre() {    return nombre;    }

    public void setNombre(String nombre) {        this.nombre = nombre;    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdusuario() {  return idusuario;    }

    public void setIdusuario(int idusuario) {   this.idusuario = idusuario;    }


}