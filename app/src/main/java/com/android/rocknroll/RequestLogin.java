package com.android.rocknroll;

public class RequestLogin {

    private String clave;
    private String mail;

    public RequestLogin(String mail, String clave) {
        super();
        this.mail = mail;
        this.clave = clave;
    }

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
}