package com.meeting.u;

public class Mensaje {

    private String hora;
    private String nombre;
    private String mensaje;
    private String urlFoto;

    public Mensaje() {
    }

    public Mensaje(String hora, String nombre, String mensaje) {
        this.hora = hora;
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    public Mensaje(String hora, String nombre, String mensaje, String urlFoto) {
        this.hora = hora;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.urlFoto = urlFoto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
