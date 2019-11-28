package com.meeting.u;

public class activity {

    private String tipo;
    private String name;
    private String descripción;
    private String place;
    private String hora_incio;
    private String hora_fin;
    private int nparticipantes;
    private int disponibles;
    private String[] participantes;

    activity(String tipo, String name, String descripción, String place, String hora_incio, String hora_fin, int participantes, String nombre){

        this.tipo = tipo;
        this.name = name;
        this.descripción = descripción;
        this.place = place;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        nparticipantes = participantes;
        disponibles = participantes - 1;
        this.participantes = new String[participantes];
        this.participantes[0] = nombre;

    }

    void toDB(){}


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHora_incio() {
        return hora_incio;
    }

    public void setHora_incio(String hora_incio) {
        this.hora_incio = hora_incio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getNparticipantes() {
        return nparticipantes;
    }

    public void setNparticipantes(int nparticipantes) {
        this.nparticipantes = nparticipantes;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
}
